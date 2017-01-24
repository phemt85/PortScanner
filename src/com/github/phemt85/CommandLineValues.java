package com.github.phemt85;

public class CommandLineValues {

	private boolean is_h_passed = false;
	private String ip_address = null;
	private int timeout = 0;
	private boolean onlyopenport = false;
	
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
	public boolean isOnlyopenport() {
		return onlyopenport;
	}
	public void setOnlyopenport(boolean onlyopenport) {
		this.onlyopenport = onlyopenport;
	}
	@Override
	public String toString() {
		return "CommandLineValues [is_h_passed=" + is_h_passed + ", ip_address=" + ip_address + ", timeout=" + timeout
				+ ", onlyopenport=" + onlyopenport + "]";
	}
	
}
