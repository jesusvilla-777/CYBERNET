package com.example.cybernet.util;

import java.io.IOException;
import java.net.InetAddress;

public class NetworkUtils {

    // Método para hacer ping
    public static boolean isReachable(String ip) throws IOException {
        InetAddress address = InetAddress.getByName(ip);
        return address.isReachable(5000);  // Timeout de 5 segundos
    }

    // Método para escanear puertos (opcional, si decides usar una librería como nmap)
    public static String scanPorts(String ip) {
        // Implementar escaneo de puertos aquí
        return "Escaneo de puertos para " + ip + " completado";  // Simulación
    }
}
