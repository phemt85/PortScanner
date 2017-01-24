package com.github.phemt85;

import java.util.regex.Pattern;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;

public class Cli {

	private Options options;

	public Cli(){

		Option help_option = Option.builder("h")
				.longOpt("help")
				.desc("Show examples")
				.build();

		Option ip_option = Option.builder("i")
				.longOpt("ip")
				.hasArg()
				.argName("ip address")
				.desc("The Ip address to scan")
				.build();

		Option timeout_option = Option.builder("t")
				.longOpt("timeout")
				.hasArg()
				.argName("timeout")
				.desc("The timeout value to be used in milliseconds")
				.build();
		
		options = new Options();
		options.addOption(help_option);
		options.addOption(ip_option);
		options.addOption(timeout_option);
	}

	public void printOptions(){

		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("java -jar PortScanner.jar <option>", options);
	}

	public CommandLine parseCommandLine(String[] args) throws ParseException{

		CommandLineParser parser = new DefaultParser();
		CommandLine cmdLine = parser.parse(options, args);
		return cmdLine;
	}

	public CommandLineValues checkCommandLineArguments(CommandLine cmdLine){

		CommandLineValues command_line_values = new CommandLineValues();
		CommandLinePrinter command_line_printer = new CommandLinePrinter();
		
		if (cmdLine.hasOption("h")) {
			
			command_line_values.setIs_h_passed(true);
		}

		if (cmdLine.hasOption("i")) {

			if(!isValidIpAddress(cmdLine.getOptionValue("i"))){

				command_line_printer.printStr("Invalid ip address!", true);
				System.exit(1);
			}
			
			command_line_values.setIp_address(cmdLine.getOptionValue("i"));
		}

		if (cmdLine.hasOption("t")) {

			if(!StringUtils.isNumeric(cmdLine.getOptionValue("t"))){

				command_line_printer.printStr("Invalid timeout value!", true);
				System.exit(1);
			}

			if(Integer.parseInt(cmdLine.getOptionValue("t")) < 1000){

				command_line_values.setTimeout(1000);

			}else{

				command_line_values.setTimeout(Integer.parseInt(cmdLine.getOptionValue("t")));
			}
		}

		return command_line_values;
	}

	private boolean isValidIpAddress(String ip_address){

		Pattern ip_address_pattern = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

		return ip_address_pattern.matcher(ip_address).matches();
	}

}
