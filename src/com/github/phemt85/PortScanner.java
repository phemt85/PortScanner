package com.github.phemt85;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PortScanner {
	
	public void scan(CommandLineValues command_line_values){
	
		List<ScanResult> resultsList = new ArrayList<ScanResult>();
		
		if(command_line_values.getPorts() != null && command_line_values.getSubnet_ips_list() == null){
			
			String[] ips_array = {command_line_values.getIp_address()};
			
			resultsList = scanPortsList(ips_array , command_line_values.getPorts(), command_line_values.getTimeout());
			
			printResults(resultsList);
			
		}else if(command_line_values.getPorts() != null && command_line_values.getSubnet_ips_list() != null){
			
			resultsList = scanPortsList(command_line_values.getSubnet_ips_list() , command_line_values.getPorts(), command_line_values.getTimeout());
			printResults(resultsList);
			
		}else{
			
			resultsList = scanAllPorts(command_line_values.getIp_address(), command_line_values.getTimeout());
			printResults(resultsList);
		}
	}
	
	private List<ScanResult> scanAllPorts(String ip_address, int timeout){

		List<ScanResult> resultsList = new ArrayList<ScanResult>();
		ScanResult result = new ScanResult();
		CommandLineLoader loader = new CommandLineLoader();
		List<Integer> open_ports = new ArrayList<>();
		
		for (int port = 1; port < 65536; port++) {

			if(isPortOpen(ip_address, port, timeout)){
			
				open_ports.add(port);
			}
			
			loader.update(port, 65535);
		}
		
		result.setIp_address(ip_address);
		result.setOpen_ports(open_ports);
		
		return resultsList;
	}
	
	private List<ScanResult> scanPortsList(String[] ip_addresses, List<Integer> ports, int timeout){
		
		List<ScanResult> resultsList = new ArrayList<ScanResult>();
		CommandLineLoader loader = new CommandLineLoader();
		List<Integer> open_ports = null;
		ScanResult result = null;
		
		int total_ports_to_scan = ip_addresses.length * ports.size();
		int ports_scanned = 0;
		
		for (int i = 0; i < ip_addresses.length; i++) {
			
			open_ports = new ArrayList<>();
			result = new ScanResult();
			
			for (int port = 0; port < ports.size(); port++) {

				if(isPortOpen(ip_addresses[i], ports.get(port), timeout)){
				
					open_ports.add(ports.get(port));
				}
				
				loader.update(ports_scanned, total_ports_to_scan);
				ports_scanned++;
			}
			
			result.setIp_address(ip_addresses[i]);
			result.setOpen_ports(open_ports);
			resultsList.add(result);
			
		}
		
		return resultsList;
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
		command_line_printer.printStr("-Scan port 22 on network 192.168.1.0/24", true);
		command_line_printer.printStr("java -jar PortScanner<version>.jar -t 1000 -p 22 -ss 192.168.1.0/24", true);

	}
	
	private void printResults(List<ScanResult> resultsList){
		
		CommandLinePrinter printer = new CommandLinePrinter();
		
		printer.printStr("Scanned " + resultsList.size() + " ips", true);
		
		for (int i = 0; i < resultsList.size(); i++) {
			
			printer.printStr("List open ports for ip " + resultsList.get(i).getIp_address() + ": ", false);

			if(resultsList.get(i).getOpen_ports().size() != 0){

				for (int j = 0; j < resultsList.get(i).getOpen_ports().size(); j++) {

					printer.printStr(String.valueOf(resultsList.get(i).getOpen_ports().get(j)) + " ", false);
				}

			}else{

				printer.printStr("No open ports found!", false);
			}
			
			
			printer.printStr("", true);
		}

	}
}
