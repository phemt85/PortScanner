package com.github.phemt85;

public class CommandLinePrinter {

	public void printHead(){

		print("/**", true);
		print("* PortScanner", true);
		print("* Version: 0.0.1", true);
		print("*/", true);
		print("", true);

	}

	public void printTail(){

		print("/**", true);
		print("* PortScanner end", true);
		print("*/", true);

	}
	
	public void printStr(String str, boolean with_crlf){
		
		print(str, with_crlf);
	}
	
	private void print(String str, boolean with_crlf){
		
		if(with_crlf){
		
			System.out.println(str);
			
		}else{
			
			System.out.print(str);
		}
	}
}
