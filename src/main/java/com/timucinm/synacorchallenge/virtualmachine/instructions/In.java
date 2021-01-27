package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;
import com.timucinm.synacorchallenge.virtualmachine.memory.VirtualMachineMemory;

public class In implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 20;
    private final int INSTRUCTION_OFFSET = 2;
    private int instructionPointer;

    public In(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
	this.instructionPointer = virtualMachine.getInstructionPointer();
	
    }

    @Override
    public void execute() {
	int destination = getParameters().get(0);
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	if(!memory.inputQueueIsEmpty()) {
	    memory.setRegister(destination, (char) memory.pollInput());
	} else {
	    VIRTUAL_MACHINE.setStandby();
	    System.out.println("Machine is on standby. Waiting for input.");
	    instructionPointer -= INSTRUCTION_OFFSET;
	}
    }

    @Override
    public int opCodeId() {
	return OP_CODE_ID;
    }

    @Override
    public int getNextInstructionPointerPosition() {
	return Math.floorMod((instructionPointer + INSTRUCTION_OFFSET),
		VIRTUAL_MACHINE.getVirtualMachineMemory().getTotalMemorySize());
    }

    @Override
    public List<Integer> getParameters() {
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	return List.of(memory.getFromMainMemory(instructionPointer + 1));
    }
}
