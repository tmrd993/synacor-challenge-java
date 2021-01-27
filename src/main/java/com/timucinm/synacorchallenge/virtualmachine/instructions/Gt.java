package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.List;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;
import com.timucinm.synacorchallenge.virtualmachine.memory.VirtualMachineMemory;

public class Gt implements Instruction {
    private final VirtualMachine VIRTUAL_MACHINE;
    private final int OP_CODE_ID = 5;
    private final int INSTRUCTION_OFFSET = 4;
    private final int INSTRUCTION_POINTER;

    public Gt(VirtualMachine virtualMachine) {
	this.VIRTUAL_MACHINE = virtualMachine;
	this.INSTRUCTION_POINTER = virtualMachine.getInstructionPointer();
    }

    @Override
    public void execute() {
	List<Integer> parameters = getParameters();
	int destination = parameters.get(0);
	int paramB = VIRTUAL_MACHINE.getVirtualMachineMemory().getFromRegisterOrTakeImmediateValue(parameters.get(1));
	int paramC = VIRTUAL_MACHINE.getVirtualMachineMemory().getFromRegisterOrTakeImmediateValue(parameters.get(2));

	if (paramB > paramC) {
	    VIRTUAL_MACHINE.getVirtualMachineMemory().setRegister(destination, 1);
	} else {
	    VIRTUAL_MACHINE.getVirtualMachineMemory().setRegister(destination, 0);
	}
    }

    @Override
    public int opCodeId() {
	return OP_CODE_ID;
    }

    @Override
    public List<Integer> getParameters() {
	VirtualMachineMemory memory = VIRTUAL_MACHINE.getVirtualMachineMemory();
	return List.of(memory.getFromMainMemory(INSTRUCTION_POINTER + 1),
		memory.getFromMainMemory(INSTRUCTION_POINTER + 2), memory.getFromMainMemory(INSTRUCTION_POINTER + 3));
    }

    @Override
    public int getNextInstructionPointerPosition() {
	return Math.floorMod(INSTRUCTION_POINTER + INSTRUCTION_OFFSET,
		VIRTUAL_MACHINE.getVirtualMachineMemory().getTotalMemorySize());
    }

}
