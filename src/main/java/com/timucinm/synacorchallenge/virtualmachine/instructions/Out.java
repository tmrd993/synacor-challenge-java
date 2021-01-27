package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;
import com.timucinm.synacorchallenge.virtualmachine.memory.VirtualMachineMemory;

public class Out implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 19;
    private final int INSTRUCTION_OFFSET = 2;
    private final int INSTRUCTION_POINTER;

    public Out(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
	this.INSTRUCTION_POINTER = virtualMachine.getInstructionPointer();
    }

    @Override
    public void execute() {
	int parameter = getParameters().get(0);
	char outputCharacter = (char) VIRTUAL_MACHINE.getVirtualMachineMemory()
		.getFromRegisterOrTakeImmediateValue(parameter);
	System.out.print(outputCharacter);
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
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	return List.of(memory.getFromMainMemory(INSTRUCTION_POINTER + 1));
    }
}
