package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.Collections;
import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;

public class Ret implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 18;
    private int instructionPointer;

    public Ret(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
	this.instructionPointer = VIRTUAL_MACHINE.getInstructionPointer();
    }

    @Override
    public void execute() {
	if(VIRTUAL_MACHINE.getVirtualMachineMemory().stackIsEmpty()) {
	    VIRTUAL_MACHINE.halt();
	} else {
	    int jumpValue = VIRTUAL_MACHINE.getVirtualMachineMemory().popFromStack();
	    instructionPointer = jumpValue;
	}
    }

    @Override
    public int opCodeId() {
	return OP_CODE_ID;
    }

    @Override
    public int getNextInstructionPointerPosition() {
	return Math.floorMod(instructionPointer, VIRTUAL_MACHINE.getVirtualMachineMemory().getTotalMemorySize());
    }

    @Override
    public List<Integer> getParameters() {
	return Collections.emptyList();
    }

}
