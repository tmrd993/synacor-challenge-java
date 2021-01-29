package com.timucinm.synacorchallenge.virtualmachine;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.timucinm.synacorchallenge.virtualmachine.instructions.Instruction;
import com.timucinm.synacorchallenge.virtualmachine.instructions.InstructionFactory;
import com.timucinm.synacorchallenge.virtualmachine.memory.VirtualMachineMemory;

public class VirtualMachine implements Serializable {
    private static final long serialVersionUID = -8124619722148427716L;
    private final VirtualMachineMemory VIRTUAL_MACHINE_MEMORY;
    private int instructionPointer;
    private boolean hasHalted;
    private boolean isOnStandby;

    public VirtualMachine(File inputBinary) {
	instructionPointer = 0;
	VIRTUAL_MACHINE_MEMORY = new VirtualMachineMemory();
	initializeMemoryFromBinaryFile(inputBinary);
    }

    public int getInstructionPointer() {
	return instructionPointer;
    }

    public VirtualMachineMemory getVirtualMachineMemory() {
	return VIRTUAL_MACHINE_MEMORY;
    }

    public void halt() {
	hasHalted = true;
    }
    
    public boolean hasHalted() {
	return hasHalted;
    }
    
    public boolean isOnStandby() {
	return isOnStandby;
    }

    public void run() {
	isOnStandby = false;
	while(!hasHalted && !isOnStandby) {
	    int opCode = VIRTUAL_MACHINE_MEMORY.getFromMainMemory(instructionPointer);
	    Instruction instruction = InstructionFactory.getInstruction(opCode, this);
	    instruction.execute();
	    instructionPointer = instruction.getNextInstructionPointerPosition();
	}
    }
    
    // reads the argument as input for the virtual machine
    public void readInputln(String input) {
	input = input + "\n";
	input.chars().forEach(character -> VIRTUAL_MACHINE_MEMORY.setInput(character));
    }
    
    public void setStandby() {
	isOnStandby = true;
    }

    private void initializeMemoryFromBinaryFile(File inputBinary) {
	try {
	    readBinaryFileIntoMemory(inputBinary);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void readBinaryFileIntoMemory(File inputBinary) throws IOException {
	DataInputStream dataIn = new DataInputStream(new FileInputStream(inputBinary));
	byte[] intData = new byte[2];

	int currentIndex = 0;
	while (dataIn.available() > 0) {
	    dataIn.readFully(intData);
	    // turn the 16-bit little-endian pair into an integer
	    int value = ByteBuffer.wrap(intData).order(ByteOrder.LITTLE_ENDIAN).getChar();
	    VIRTUAL_MACHINE_MEMORY.setMemory(currentIndex++, value);
	}
	dataIn.close();
    }

}
