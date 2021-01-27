package com.timucinm.synacorchallenge.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;

// CLASS FOR TESTING BY HAND, WILL REMOVE LATER
public class App {
    
    public static void main(String[] args) {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	File input = new File("C:\\Users\\Timucin\\Desktop\\synacor-challenge\\challenge.bin");
	VirtualMachine test = new VirtualMachine(input);
	test.run();
	
	while(!test.hasHalted()) {
	    if(test.isOnStandby()) {
		String userInput = getUserInput(br);
		test.readInputln(userInput);
		test.run();
	    }
	}
	
	try {
	    br.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public static String getUserInput(BufferedReader br) {
	String userInput = null;
	try {
	    userInput = readInput(br);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return userInput;
    }
    
    public static String readInput(BufferedReader br) throws IOException {
	System.out.println("Enter input: ");
	String line = br.readLine();
	return line;
    }
}
