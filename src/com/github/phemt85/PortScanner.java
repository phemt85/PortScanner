package com.github.phemt85;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PortScanner {
	
	public void scan(CommandLineValues command_line_values){
	
		List<Integer> open_ports = null;
		
		if(command_line_values.getPorts() != null){
			
			open_ports = scanPortsList(command_line_values.getIp_address(), command_line_values.getPorts(), command_line_values.getTimeout());
			
		}else{
			
			open_ports = scanAllPorts(command_line_values.getIp_address(), command_line_values.getTimeout());
		}
		
		printResults(open_ports);
		
	}
	
	private List<Integer> scanAllPorts(String ip_address, int timeout){

		CommandLineLoader loader = new CommandLineLoader();
		List<Integer> open_ports = new ArrayList<>();
		
		for (int port = 1; port < 65536; port++) {

			if(isPortOpen(ip_address, port, timeout)){
			
				open_ports.add(port);
			}
			
			loader.update(port, 65535);
		}
		
		return open_ports;
	}
	
	private List<Integer> scanPortsList(String ip_address, List<Integer> ports, int timeout){
		
		CommandLineLoader loader = new CommandLineLoader();
		List<Integer> open_ports = new ArrayList<>();
		
		for (int port = 0; port < ports.size(); port++) {

			if(isPortOpen(ip_address, ports.get(port), timeout)){
			
				open_ports.add(ports.get(port));
			}
			
			loader.update(port, ports.size());
		}
		
		return open_ports;
	}

	private boolean isPortOpen(String ip_address, int port, int timeout){
		
		boolean res = false;
		
		try {

			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(ip_address, port), timeout);
			socket.close();
			
			res = true;

		} catch (Exception ex) {
			
		}
		
		return res;
	}

	public void printHelp(){
		
		CommandLinePrinter command_line_printer = new CommandLinePrinter();
		command_line_printer.printStr("-Scan all the port on 127.0.0.1", true);
		command_line_printer.printStr("java -jar PortScanner<version>.jar -i 127.0.0.1 -t 1000", true);
		command_line_printer.printStr("-Scan port 22 on 127.0.0.1", true);
		command_line_printer.printStr("java -jar PortScanner<version>.jar -i 127.0.0.1 -t 1000 -p 22", true);
	}
	
	private void printResults(List<Integer> ports){
		
		CommandLinePrinter printer = new CommandLinePrinter();
		
		printer.printStr("__________", true);
		
		if(ports.size() > 0){
		
			printer.printStr("Printing open ports:", true);
			
			for (int i = 0; i < ports.size(); i++) {
				
				printer.printStr(String.valueOf(ports.get(i)), true);
			}
			
		}else{
			
			printer.printStr("No open ports found!", true);
		}
	}
}
