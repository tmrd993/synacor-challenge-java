package com.timucinm.synacorchallenge.virtualmachine;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.timucinm.synacorchallenge.virtualmachine.instructions.Instruction;
import com.timucinm.synacorchallenge.virtualmachine.instructions.InstructionFactory;
import com.timucinm.synacorchallenge.virtualmachine.memory.VirtualMachineMemory;

public class VirtualMachine {
    private final VirtualMachineMemory VIRTUAL_MACHINE_MEMORY;
    private int instructionPointer;
    private boolean isHalted;

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
	isHalted = true;
    }

    public void run() {
	while(!isHalted) {
	    int opCode = VIRTUAL_MACHINE_MEMORY.getFromMainMemory(instructionPointer);
	    Instruction instruction = InstructionFactory.getInstruction(opCode, this);
	    instruction.execute();
	    instructionPointer = instruction.getNextInstructionPointerPosition();
	    
	}
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
