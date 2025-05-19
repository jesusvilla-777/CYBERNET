package com.example.security.modelo;

public class PortScanRequest {
    private String ip;
    private String portRange;

    // Getters y setters
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
    
    public String getPortRange() { return portRange; }
    public void setPortRange(String portRange) { 
        this.portRange = portRange;
        // Verifica si el rango está en el formato correcto
        System.out.println("PortRange recibido: " + portRange);  // Depuración
    }

    // Método para obtener el rango de puertos como startPort y endPort
    public int[] getPortRangeAsArray() {
        if (portRange != null && portRange.contains("-")) {
            String[] parts = portRange.split("-");
            try {
                int startPort = Integer.parseInt(parts[0]);
                int endPort = Integer.parseInt(parts[1]);
                return new int[] { startPort, endPort };
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir los puertos: " + e.getMessage());  // Depuración
                return null;
            }
        }
        return null;
    }
}
