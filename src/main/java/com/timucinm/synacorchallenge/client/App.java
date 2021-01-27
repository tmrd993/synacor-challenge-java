package com.timucinm.synacorchallenge.client;

import java.io.File;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;

// CLASS FOR TESTING BY HAND, WILL REMOVE LATER
public class App {
    
    public static void main(String[] args) {
	
	File input = new File("C:\\Users\\Timucin\\Desktop\\synacor-challenge\\challenge.bin");
	VirtualMachine test = new VirtualMachine(input);
	test.run();
    }
}
