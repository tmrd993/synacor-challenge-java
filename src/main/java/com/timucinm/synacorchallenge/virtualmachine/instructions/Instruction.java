package com.timucinm.synacorchallenge.virtualmachine.instructions;

import java.util.List;

public interface Instruction {
    void execute();
    int opCodeId();
    List<Integer> getParameters(); 
    int getNextInstructionPointerPosition();
}
