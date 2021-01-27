package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;
import com.timucinm.synacorchallenge.virtualmachine.memory.VirtualMachineMemory;

public class Rmem implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 15;
    private final int INSTRUCTION_OFFSET = 3;
    private final int INSTRUCTION_POINTER;

    public Rmem(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
	this.INSTRUCTION_POINTER = virtualMachine.getInstructionPointer();
    }

    @Override
    public void execute() {
	List<Integer> parameters = getParameters();
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	int source = memory.getFromRegisterOrTakeImmediateValue(parameters.get(1));
	int sourceData = memory.getFromMainMemory(source);
	int destination = parameters.get(0);
	VIRTUAL_MACHINE.getVirtualMachineMemory().setRegister(destination, sourceData);
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
	return Math.floorMod(INSTRUCTION_POINTER + INSTRUCTION_OFFSET, VIRTUAL_MACHINE.getVirtualMachineMemory().getTotalMemorySize());
    }
}
