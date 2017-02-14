# PortScanner
A simple java port scanner.

This is a simple java application to scan a host's ports.

Requirements

    commons-cli-1.3.1.jar

    commons-lang3-3.5.jar

    commons-net-3.5.jar

Usage

    To compile the source run the command "ant compile".

    To create the jar run the command "ant jar".

Examples

    java -jar PortScanner<version>.jar -i 127.0.0.1 -t 1000
	
	java -jar PortScanner<version>.jar -i 127.0.0.1 -t 1000 -p 22

	java -jar PortScanner<version>.jar -i 127.0.0.1 -t 1000 -p 22 -ss 192.168.1.0/24
