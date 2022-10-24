package domain.model.instructions.mow;

import domain.model.instructions.Instruction;

public abstract class MoveMowInstruction extends Instruction {

    public final static String MOVE_MOW_INSTRUCTION = "^[G,A,D]+$";
    public final static String MOVE_MOW_LEFT_INSTRUCTION = "G";
    public final static String MOVE_MOW_RIGHT_INSTRUCTION = "D";
    public final static String MOVE_MOW_FORWARD_INSTRUCTION = "A";


    public static MoveMowInstruction fromLibelle(String libelle) {
        if(MOVE_MOW_FORWARD_INSTRUCTION.equals(libelle))
            return new MoveMowForwardInstruction();
        if(MOVE_MOW_LEFT_INSTRUCTION.equals(libelle))
            return new MoveMowOnTheLeftInstruction();
        if(MOVE_MOW_RIGHT_INSTRUCTION.equals(libelle))
            return new MoveMowOnTheRightInstruction();
        throw new IllegalArgumentException("Incorrect move mow instruction " + libelle);
    }

}
