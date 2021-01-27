package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.Collections;
import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;

public class NoOp implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 21;
    private final int INSTRUCTION_OFFSET = 1;
    private final int INSTRUCTION_POINTER;

    public NoOp(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
	this.INSTRUCTION_POINTER = virtualMachine.getInstructionPointer();
    }

    @Override
    public void execute() {
    }

    @Override
    public int opCodeId() {
	return OP_CODE_ID;
    }

    @Override
    public int getNextInstructionPointerPosition() {
	return Math.floorMod((INSTRUCTION_POINTER + INSTRUCTION_OFFSET),
		VIRTUAL_MACHINE.getVirtualMachineMemory().getTotalMemorySize());
    }

    @Override
    public List<Integer> getParameters() {
	return Collections.emptyList();
    }

}
