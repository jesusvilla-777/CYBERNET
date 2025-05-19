package com.example.security.servicios;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.modelo.Evento;
import com.example.security.modelo.PingRequest;
import com.example.security.modelo.PingResponse;
import com.example.security.modelo.PortScanRequest;
import com.example.security.modelo.PortScanResponse;
import com.example.security.repositorio.EventoRepository;

@Service
public class NetworkService {

    @Autowired
    private EventoRepository networkEventRepository;

    private static final Map<Integer, String> COMMON_PORTS = new HashMap<>();

    static {
        COMMON_PORTS.put(21, "FTP");
        COMMON_PORTS.put(22, "SSH");
        COMMON_PORTS.put(23, "Telnet");
        COMMON_PORTS.put(25, "SMTP");
        COMMON_PORTS.put(53, "DNS");
        COMMON_PORTS.put(80, "HTTP");
        COMMON_PORTS.put(110, "POP3");
        COMMON_PORTS.put(143, "IMAP");
        COMMON_PORTS.put(443, "HTTPS");
        COMMON_PORTS.put(3306, "MySQL");
        COMMON_PORTS.put(8080, "HTTP-Proxy");
    }

    public PortScanResponse scanPorts(PortScanRequest request) {
        if (!isValidIPOrDomain(request.getIp())) {
            throw new IllegalArgumentException("IP o dominio inválido");
        }

        String[] rangeParts = request.getPortRange().split("-");
        if (rangeParts.length != 2) {
            throw new IllegalArgumentException("Rango de puertos inválido. Use formato '1-1000'");
        }

        int minPort, maxPort;
        try {
            minPort = Integer.parseInt(rangeParts[0]);
            maxPort = Integer.parseInt(rangeParts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Rango de puertos inválido");
        }

        if (minPort < 1 || maxPort > 65535 || minPort > maxPort) {
            throw new IllegalArgumentException("Rango de puertos fuera de límites (1-65535)");
        }

        if (maxPort - minPort > 1000) {
            maxPort = minPort + 1000;
        }

        List<Map<String, Object>> openPorts = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(20); 
        List<Future<Map<String, Object>>> futures = new ArrayList<>();

        for (int port = minPort; port <= maxPort; port++) {
            final int currentPort = port;
            futures.add(executor.submit(() -> {
                Map<String, Object> result = new HashMap<>();
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(request.getIp(), currentPort), 2000); 
                    result.put("port", currentPort);
                    result.put("status", "abierto");
                    result.put("service", COMMON_PORTS.getOrDefault(currentPort, "desconocido"));
                    return result;
                } catch (Exception e) {
                    return null;
                }
            }));
        }

        for (Future<Map<String, Object>> future : futures) {
            try {
                Map<String, Object> result = future.get(500, TimeUnit.MILLISECONDS);
                if (result != null) {
                    openPorts.add(result);
                }
            } catch (java.util.concurrent.TimeoutException | InterruptedException
                    | java.util.concurrent.ExecutionException e) {
            }
        }

        executor.shutdown();

        Evento event = new Evento();
        event.setIp(request.getIp());
        event.setMac("-");
        Date now = new Date();
        event.setFecha(now);
     
        event.setDescripcion("Escaneo de puertos a " + request.getIp() + " (" + request.getPortRange() + ")");
        networkEventRepository.save(event);

        PortScanResponse response = new PortScanResponse();
        response.setIp(request.getIp());
        response.setPortRange(request.getPortRange());
        response.setOpenPorts(openPorts);
        response.setTotalScanned(maxPort - minPort + 1);

        return response;
    }

    private boolean isValidIPOrDomain(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        String regex = "^(?=.{7,255}$)(([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

	public PingResponse executePing(PingRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Evento> getAllEvents() {
		// TODO Auto-generated method stub
		return null;
	}
}
