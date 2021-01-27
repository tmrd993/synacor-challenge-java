package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;
import com.timucinm.synacorchallenge.virtualmachine.memory.VirtualMachineMemory;

public class Jmp implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 6;
    private int instructionPointer;

    public Jmp(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
	this.instructionPointer = virtualMachine.getInstructionPointer();
    }

    @Override
    public void execute() {
	int targetIndex = getParameters().get(0);
	instructionPointer = VIRTUAL_MACHINE.getVirtualMachineMemory().getFromRegisterOrTakeImmediateValue(targetIndex);
    }

    @Override
    public int opCodeId() {
	return OP_CODE_ID;
    }

    @Override
    public List<Integer> getParameters() {
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	return List.of(memory.getFromMainMemory(instructionPointer + 1));
    }

    @Override
    public int getNextInstructionPointerPosition() {
	return Math.floorMod(instructionPointer, VIRTUAL_MACHINE.getVirtualMachineMemory().getTotalMemorySize());
    }

}
