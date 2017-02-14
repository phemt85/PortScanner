package com.github.phemt85;

import java.util.List;

public class ScanResult {
	
	private String ip_address;
	private List<Integer> open_ports;
	
	public String getIp_address() {
		return ip_address;
	}
	
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	
	public List<Integer> getOpen_ports() {
		return open_ports;
	}
	
	public void setOpen_ports(List<Integer> open_port) {
		this.open_ports = open_port;
	}
}
