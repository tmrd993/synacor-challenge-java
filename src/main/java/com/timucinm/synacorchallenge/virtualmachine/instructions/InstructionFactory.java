package com.timucinm.synacorchallenge.virtualmachine.instructions;

import com.timucinm.synacorchallenge.virtualmachine.VirtualMachine;

public class InstructionFactory {

    private InstructionFactory() {
    }

    public static Instruction getInstruction(int opCodeId, VirtualMachine virtualMachine) {
	switch (opCodeId) {
	case 0:
	    return new Halt(virtualMachine);
	case 1:
	    return new Set(virtualMachine);
	case 2:
	    return new Push(virtualMachine);
	case 3:
	    return new Pop(virtualMachine);
	case 4:
	    return new Eq(virtualMachine);
	case 5:
	    return new Gt(virtualMachine);
	case 6:
	    return new Jmp(virtualMachine);
	case 7:
	    return new Jt(virtualMachine);
	case 8:
	    return new Jf(virtualMachine);
	case 9:
	    return new Add(virtualMachine);
	case 10:
	    return new Mult(virtualMachine);
	case 11:
	    return new Mod(virtualMachine);
	case 12:
	    return new And(virtualMachine);
	case 13:
	    return new Or(virtualMachine);
	case 14:
	    return new Not(virtualMachine);
	case 15:
	    return new Rmem(virtualMachine);
	case 16:
	    return new Wmem(virtualMachine);
	case 17:
	    return new Call(virtualMachine);
	case 18:
	    return new Ret(virtualMachine);
	case 19:
	    return new Out(virtualMachine);
	case 20:
	    return new In(virtualMachine);
	case 21:
	    return new NoOp(virtualMachine);
	default:
	    throw new IllegalArgumentException(opCodeId + " is not a recognized OpCode.");
	}
    }
}
