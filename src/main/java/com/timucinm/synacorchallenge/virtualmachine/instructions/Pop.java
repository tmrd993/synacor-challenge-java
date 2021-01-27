package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;
import com.timucinm.synacorchallenge.virtualmachine.memory.VirtualMachineMemory;

public class Pop implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 3;
    private final int INSTRUCTION_OFFSET = 2;
    private final int INSTRUCTION_POINTER;

    public Pop(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
	this.INSTRUCTION_POINTER = virtualMachine.getInstructionPointer();
    }

    @Override
    public void execute() {
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	int destination = getParameters().get(0);
	memory.setRegister(destination, memory.popFromStack());
    }

    @Override
    public int opCodeId() {
	return OP_CODE_ID;
    }
    

    @Override
    public List<Integer> getParameters() {
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	return List.of(memory.getFromMainMemory(INSTRUCTION_POINTER + 1));
    }

    @Override
    public int getNextInstructionPointerPosition() {
	return Math.floorMod((INSTRUCTION_POINTER + INSTRUCTION_OFFSET),
		VIRTUAL_MACHINE.getVirtualMachineMemory().getTotalMemorySize());
    }
}
