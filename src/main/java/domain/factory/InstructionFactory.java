package domain.factory;

import com.google.inject.Inject;
import domain.exceptions.MalformedInstructionException;
import domain.model.instructions.GrassCreationInstruction;
import domain.model.instructions.Instruction;
import domain.model.instructions.mow.MoveMowInstruction;
import domain.model.instructions.mow.MowCreationInstruction;
import domain.service.GrassService;
import domain.service.MowService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import utils.StringValidator;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({ @Inject}))
public class InstructionFactory {

    private final GrassService grassService;
    private final MowService mowService;
    private final MoveMowInstructionFactory moveMowInstructionFactory;

    public Instruction build(String rawInstructions){
        if(StringValidator.isNullOrEmpty(rawInstructions))
            throw new IllegalArgumentException("A rawInstructions must be provided to generate an instruction");

        if(rawInstructions.matches(GrassCreationInstruction.CREATION_GRASS_INSTRUCTION_FORMAT))
            return new GrassCreationInstruction(rawInstructions, grassService);
        if(rawInstructions.matches(MowCreationInstruction.CREATION_MOW_INSTRUCTION_FORMAT))
            return new MowCreationInstruction(rawInstructions, mowService);
        if(rawInstructions.matches(MoveMowInstruction.MOVE_MOW_INSTRUCTION))
            return moveMowInstructionFactory.build(rawInstructions);

        throw new MalformedInstructionException("Incorrect format for instructions : " + rawInstructions);
    }
}
