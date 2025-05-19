package com.example.security.modelo;

import java.util.List;
import java.util.Map;

public class PortScanResponse {
    private String ip;
    private String portRange;
    private List<Map<String, Object>> openPorts;
    private int totalScanned;

    // Getters y setters
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    public String getPortRange() { return portRange; }
    public void setPortRange(String portRange) { this.portRange = portRange; }

    public List<Map<String, Object>> getOpenPorts() { return openPorts; }
    public void setOpenPorts(List<Map<String, Object>> openPorts) { this.openPorts = openPorts; }

    public int getTotalScanned() { return totalScanned; }
    public void setTotalScanned(int totalScanned) { this.totalScanned = totalScanned; }
}
