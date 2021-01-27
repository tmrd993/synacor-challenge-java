package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;
import com.timucinm.synacorchallenge.virtualmachine.memory.VirtualMachineMemory;

public class Jf implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 8;
    private final int INSTRUCTION_OFFSET = 3;
    private int instructionPointer;

    public Jf(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
	this.instructionPointer = virtualMachine.getInstructionPointer();
    }

    @Override
    public void execute() {
	List<Integer> parameters = getParameters();
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	int comparedValue = memory.getFromRegisterOrTakeImmediateValue(parameters.get(0));
	int possibleDestination = memory.getFromRegisterOrTakeImmediateValue(parameters.get(1));
	if (comparedValue == 0) {
	    instructionPointer = possibleDestination;
	} else {
	    instructionPointer += INSTRUCTION_OFFSET;
	}
    }

    @Override
    public int opCodeId() {
	return OP_CODE_ID;
    }

    @Override
    public List<Integer> getParameters() {
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	return List.of(memory.getFromMainMemory(instructionPointer + 1),
		memory.getFromMainMemory(instructionPointer + 2));
    }

    @Override
    public int getNextInstructionPointerPosition() {
	return Math.floorMod(instructionPointer, VIRTUAL_MACHINE.getVirtualMachineMemory().getTotalMemorySize());
    }
}
