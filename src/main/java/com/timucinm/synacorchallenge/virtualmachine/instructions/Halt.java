package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.Collections;
import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;

public class Halt implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 0;
    
    public Halt(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
    }

    @Override
    public void execute() {
	VIRTUAL_MACHINE.halt();
    }

    @Override
    public int opCodeId() {
	return OP_CODE_ID;
    }

    @Override
    public int getNextInstructionPointerPosition() {
	return VIRTUAL_MACHINE.getInstructionPointer();
    }

    @Override
    public List<Integer> getParameters() {
	return Collections.emptyList();
    }
    
    
}
