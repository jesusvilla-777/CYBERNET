// src/main/java/com/cybernet/service/PingService.java
package com.example.security.modelo;

import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
public class PingService {

    public String hacerPing(String ip) {
        try {
            InetAddress inet = InetAddress.getByName(ip);
            String direccionIP = inet.getHostAddress();
            boolean reachable = inet.isReachable(3000); // 3 segundos

            String resultado = "Ping a " + ip + " [" + direccionIP + "]:<br>";
            resultado += reachable ? "✅ Host alcanzado." : "❌ No se pudo alcanzar el host.";
            return resultado;

        } catch (Exception e) {
            return "❌ Error al hacer ping: " + e.getMessage();
        }
    }

	
}
