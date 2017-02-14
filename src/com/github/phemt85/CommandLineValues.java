package com.github.phemt85;

import java.util.List;

public class CommandLineValues {

	private boolean is_h_passed = false;
	private String ip_address = null;
	private int timeout = 0;
	private List<Integer> ports = null;
	private String[] subnet_ips_list = null;
	
	public boolean getIs_h_passed() {
		return is_h_passed;
	}
	public void setIs_h_passed(boolean is_h_passed) {
		this.is_h_passed = is_h_passed;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public List<Integer> getPorts() {
		return ports;
	}
	public void setPorts(List<Integer> ports) {
		this.ports = ports;
	}
	public String[] getSubnet_ips_list() {
		return subnet_ips_list;
	}
	public void setSubnet_ips_list(String[] subnet_ips_list) {
		this.subnet_ips_list = subnet_ips_list;
	}

}
