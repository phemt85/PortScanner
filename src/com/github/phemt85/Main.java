package com.github.phemt85;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

public class Main {

	public static void main(String []args) {
		
		Cli cli = new Cli();
		CommandLinePrinter printer = new CommandLinePrinter();
		
		printer.printHead();
		
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
			
			printer.printStr("Scanning...", true);
			
			port_scanner.scan(command_line_values);
			
		} catch (ParseException e) {
			
			System.err.println(e.getMessage());
		}

	}
}
