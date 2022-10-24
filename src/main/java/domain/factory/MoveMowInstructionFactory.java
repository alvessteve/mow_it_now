package domain.factory;

import domain.model.instructions.Instruction;
import domain.model.instructions.mow.MoveMowInstruction;
import domain.model.instructions.mow.MoveMowInstructionList;
import utils.StringValidator;

import java.nio.CharBuffer;
import java.util.List;
import java.util.stream.Collectors;

public class MoveMowInstructionFactory {
    public static Instruction build(String rawInstructions) {

        if(StringValidator.isNullOrEmpty(rawInstructions))
            throw new IllegalArgumentException("Empty instructions");

        List<Character> instructionsInCharFormat = CharBuffer.wrap(rawInstructions.toCharArray())
                .chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.toList());

        List<MoveMowInstruction> moveMowInstructions = instructionsInCharFormat.stream()
                .map(MoveMowInstruction::fromLibelle)
                .collect(Collectors.toList());

        return new MoveMowInstructionList(moveMowInstructions);
    }
}
