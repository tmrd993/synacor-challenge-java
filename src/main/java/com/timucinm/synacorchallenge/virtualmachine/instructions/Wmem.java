package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;
import com.timucinm.synacorchallenge.virtualmachine.memory.VirtualMachineMemory;

public class Wmem implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 16;
    private final int INSTRUCTION_OFFSET = 3;
    private final int INSTRUCTION_POINTER;

    public Wmem(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
	this.INSTRUCTION_POINTER = virtualMachine.getInstructionPointer();
    }

    @Override
    public void execute() {
	List<Integer> parameters = getParameters();
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	int value = memory.getFromRegisterOrTakeImmediateValue(parameters.get(1));
	int destination = memory.getFromRegisterOrTakeImmediateValue(parameters.get(0));
	VIRTUAL_MACHINE.getVirtualMachineMemory().setMemory(destination, value);
    }

    @Override
    public int opCodeId() {
	return OP_CODE_ID;
    }

    @Override
    public List<Integer> getParameters() {
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	return List.of(memory.getFromMainMemory(INSTRUCTION_POINTER + 1),
		memory.getFromMainMemory(INSTRUCTION_POINTER + 2));
    }

    @Override
    public int getNextInstructionPointerPosition() {
	return Math.floorMod(INSTRUCTION_POINTER + INSTRUCTION_OFFSET,
		VIRTUAL_MACHINE.getVirtualMachineMemory().getTotalMemorySize());
    }
}
