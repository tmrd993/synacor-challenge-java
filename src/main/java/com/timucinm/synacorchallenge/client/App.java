package com.timucinm.synacorchallenge.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;

// CLASS FOR TESTING BY HAND, WILL REMOVE LATER
public class App {
    private static final String SAVE_DATA_FILE_PATH = "C:\\Users\\Timucin\\Desktop\\synacor-challenge\\savedata";

    public static void main(String[] args) {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	File input = new File("C:\\Users\\Timucin\\Desktop\\synacor-challenge\\challenge.bin");
	VirtualMachine test = new VirtualMachine(input);
	test.run();

	while (!test.hasHalted()) {
	    if (test.isOnStandby()) {
		String userInput = getUserInput(br);
		test.readInputln(userInput);

		if (userInput.equals("save")) {
		    save(test);
		    System.out.println("SAVED STATE. EXITING...");
		    test.halt();
		} else if (userInput.equals("load")) {
		    System.out.println("LOADED LAST SAVE");
		    test = load();
		}

		test.run();
	    }
	}

	try {
	    br.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private static String getUserInput(BufferedReader br) {
	String userInput = null;
	try {
	    userInput = readInput(br);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return userInput;
    }

    private static String readInput(BufferedReader br) throws IOException {
	System.out.println("Enter input: ");
	String line = br.readLine();
	return line;
    }
    
    private static void save(VirtualMachine virtualMachine) {
	try {
	    saveMachineState(virtualMachine);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private static void saveMachineState(VirtualMachine virtualMachine) throws IOException {
	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(SAVE_DATA_FILE_PATH + "\\machineSaveData.data")))) {
	    oos.writeObject(virtualMachine);
	}
    }
    
    private static VirtualMachine load() {
	try {
	    return loadMachineState();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	throw new NoSuchElementException("Save data could not be loaded.");
    }
    
    private static VirtualMachine loadMachineState() throws Exception {
	VirtualMachine machine = null;
	try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_DATA_FILE_PATH + "\\machineSaveData.data"))){
	    machine = (VirtualMachine) in.readObject();
	}
	if(machine == null) {
	    throw new Exception("Machine state could not be loaded.");
	}
	
	return machine;
    }
}
