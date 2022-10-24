package domain.factory;

import domain.model.instructions.Instruction;
import domain.model.instructions.mow.MoveMowInstruction;
import domain.model.instructions.mow.MoveMowInstructionList;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public class MoveMowInstructionFactory {
    public static Instruction build(String rawInstructions) {

        return CharBuffer.wrap(rawInstructions.toCharArray())
                .chars()
                .mapToObj(ch -> (char) ch)
                .map(MoveMowInstructionFactory::createInstruction)
                .findAny()
                .get();
    }

    private static Instruction createInstruction(Character rawInstruction){
        List<MoveMowInstruction> moveMowInstructions = new ArrayList<>();
        MoveMowInstruction instruction = MoveMowInstruction.fromLibelle(rawInstruction.toString());
        moveMowInstructions.add(instruction);
        return new MoveMowInstructionList(moveMowInstructions);
    }
}
