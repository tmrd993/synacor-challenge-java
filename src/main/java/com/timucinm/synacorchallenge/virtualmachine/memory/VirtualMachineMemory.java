package com.timucinm.synacorchallenge.virtualmachine.memory;

import java.util.Stack;

public class VirtualMachineMemory {
    private final int MEM_SIZE = 32768;
    private final int NUM_OF_REGISTERS = 8;
    private final int[] REGISTERS;
    private final int[] MEMORY;
    private final Stack<Integer> STACK;

    public VirtualMachineMemory() {
	REGISTERS = new int[NUM_OF_REGISTERS];
	MEMORY = new int[MEM_SIZE];
	STACK = new Stack<>();
    }

    public int getTotalMemorySize() {
	return MEM_SIZE;
    }

    public int getNumberOfRegisters() {
	return NUM_OF_REGISTERS;
    }

    public int getFromRegisterOrTakeImmediateValue(int index) {
	if (isRegisterIndex(index)) {
	    int registerIndex = index - MEM_SIZE;
	    return REGISTERS[registerIndex];
	} else {
	    return index;
	}
    }

    public void setRegister(int index, int value) {
	if (isRegisterIndex(index)) {
	    int registerIndex = index - MEM_SIZE;
	    REGISTERS[registerIndex] = value;
	}
    }
    
    public void setMemory(int index, int value) {
	MEMORY[index] = value;
    }
   
    private boolean isRegisterIndex(int index) {
	return index < MEM_SIZE + NUM_OF_REGISTERS && index >= MEM_SIZE;
    }

    public int getFromMainMemory(int index) {
	return MEMORY[index];
    }
    
    public void pushOnStack(int value) {
	STACK.push(value);
    }
    
    public int popFromStack() {
	return STACK.pop();
    }
    
    public boolean stackIsEmpty() {
	return STACK.isEmpty();
    }
}
