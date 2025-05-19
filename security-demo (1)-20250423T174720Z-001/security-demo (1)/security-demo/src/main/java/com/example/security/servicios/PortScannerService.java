package com.example.security.servicios;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PortScannerService {

    @Async
    public CompletableFuture<List<Integer>> scanPorts(String ip) {
        List<Integer> openPorts = new ArrayList<>();
        int[] ports = {22, 80, 443}; // Puedes agregar más puertos aquí

        for (int port : ports) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(ip, port), 3000); // 3 segundos de timeout
                openPorts.add(port);
            } catch (Exception ignored) {
                // Puerto cerrado o no accesible
            }
        }

        return CompletableFuture.completedFuture(openPorts);
    }
}
