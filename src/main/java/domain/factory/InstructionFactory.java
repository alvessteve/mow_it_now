package domain.factory;

import domain.model.instructions.GrassCreationInstruction;
import domain.model.instructions.Instruction;
import utils.StringValidator;

public class InstructionFactory {
    private final static String CREATION_MOW_INSTRUCTION = "^\\d \\d [N,E,W,S]{1}$";
    private final static String CREATION_MOVE_INSTRUCTION = "^[G,A,D]+$";

    public static Instruction build(String content){
        if(StringValidator.isNullOrEmpty(content))
            throw new IllegalArgumentException("A content must be provided to generate an instruction");
        if(content.matches(GrassCreationInstruction.CREATION_GRASS_INSTRUCTION_FORMAT))
            return new GrassCreationInstruction(content);
        throw new IllegalArgumentException("Content not fitted");
    }
}
