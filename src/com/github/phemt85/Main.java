package com.github.phemt85;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

public class Main {

	public static void main(String []args) {
		
		Cli cli = new Cli();
		CommandLinePrinter command_line_banner = new CommandLinePrinter();
		
		command_line_banner.printHead();
		
		try {
			
			if(args.length == 0){
				
				cli.printOptions();
				System.exit(0);
			}
			
			CommandLine cmdLine = cli.parseCommandLine(args);
			CommandLineValues command_line_values = cli.checkCommandLineArguments(cmdLine);
			
			PortScanner port_scanner = new PortScanner();
			
			if(command_line_values.getIs_h_passed()){
				
				port_scanner.printHelp();
				System.exit(0);
			}
			
			port_scanner.scanAll(command_line_values.getIp_address(), command_line_values.getTimeout());
			
		} catch (ParseException e) {
			
			System.err.println(e.getMessage());
		}
		
		command_line_banner.printTail();
	}
}
