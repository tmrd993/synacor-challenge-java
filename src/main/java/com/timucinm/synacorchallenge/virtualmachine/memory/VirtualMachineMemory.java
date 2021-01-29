package com.timucinm.synacorchallenge.virtualmachine.memory;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class VirtualMachineMemory implements Serializable {
    private static final long serialVersionUID = -4938299303662314684L;
    private final int MEM_SIZE = 32768;
    private final int NUM_OF_REGISTERS = 8;
    private final int[] REGISTERS;
    private final int[] MEMORY;
    private final Stack<Integer> STACK;
    private Queue<Integer> inputValues;

    public VirtualMachineMemory() {
	REGISTERS = new int[NUM_OF_REGISTERS];
	MEMORY = new int[MEM_SIZE];
	STACK = new Stack<>();
	inputValues = new LinkedList<>();
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
    
    public void setInput(int in) {
	inputValues.add(in);
    }
    
    public int pollInput() {
	return inputValues.poll();
    }
    
    public boolean inputQueueIsEmpty() {
	return inputValues.isEmpty();
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
