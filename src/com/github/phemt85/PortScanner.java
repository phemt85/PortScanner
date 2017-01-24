package com.github.phemt85;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class PortScanner {

	public void scanAll(String ip_address, int timeout){

		for (int port = 1; port <= 65535; port++) {

			scanPort(ip_address, port, timeout);
		}
	}

	public void scanList(String ip_address, List<Integer> port_list, int timeout){

		for (int i = 0; i <= port_list.size(); i++) {

			scanPort(ip_address, port_list.get(i), timeout);
		}
	}

	private void scanPort(String ip_address, int port, int timeout){

		CommandLinePrinter command_line_printer = new CommandLinePrinter();
		
		try {

			command_line_printer.printStr("Scanning port " + port, false);

			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(ip_address, port), timeout);
			socket.close();

			command_line_printer.printStr(" ----> open", true);

		} catch (Exception ex) {

			command_line_printer.printStr(" ----> close", true);
		}
	}

	public void printHelp(){
		
		CommandLinePrinter command_line_printer = new CommandLinePrinter();
		command_line_printer.printStr("java -jar PortScanner<version>.jar -i 127.0.0.1 -t 1000", true);
	}
}
