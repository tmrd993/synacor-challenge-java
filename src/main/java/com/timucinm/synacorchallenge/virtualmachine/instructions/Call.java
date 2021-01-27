package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;
import com.timucinm.synacorchallenge.virtualmachine.memory.VirtualMachineMemory;

public class Call implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 17;
    private final int INSTRUCTION_OFFSET = 2;
    private int instructionPointer;
    
    public Call(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
	this.instructionPointer = virtualMachine.getInstructionPointer();
    }

    @Override
    public void execute() {
	int parameter = getParameters().get(0);
	int jumpValue = VIRTUAL_MACHINE.getVirtualMachineMemory()
		.getFromRegisterOrTakeImmediateValue(parameter);
	VIRTUAL_MACHINE.getVirtualMachineMemory().pushOnStack(instructionPointer + INSTRUCTION_OFFSET);
	instructionPointer = jumpValue;
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
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	return List.of(memory.getFromMainMemory(instructionPointer + 1));
    }
}
