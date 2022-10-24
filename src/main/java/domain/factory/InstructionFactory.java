package domain.factory;

import domain.model.instructions.GrassCreationInstruction;
import domain.model.instructions.Instruction;
import domain.model.instructions.mow.MoveMowInstruction;
import domain.model.instructions.mow.MowCreationInstruction;
import utils.StringValidator;

public class InstructionFactory {

    public static Instruction build(String rawInstructions){
        if(StringValidator.isNullOrEmpty(rawInstructions))
            throw new IllegalArgumentException("A rawInstructions must be provided to generate an instruction");

        if(rawInstructions.matches(GrassCreationInstruction.CREATION_GRASS_INSTRUCTION_FORMAT))
            return new GrassCreationInstruction(rawInstructions);
        if(rawInstructions.matches(MowCreationInstruction.CREATION_MOW_INSTRUCTION_FORMAT))
            return new MowCreationInstruction(rawInstructions);
        if(rawInstructions.matches(MoveMowInstruction.MOVE_MOW_INSTRUCTION))
            return MoveMowInstructionFactory.build(rawInstructions);

        throw new IllegalArgumentException("Instruction not translated " + rawInstructions);
    }
}
