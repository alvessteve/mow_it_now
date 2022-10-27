package domain.factory;

import com.google.inject.Inject;
import domain.model.instructions.Instruction;
import domain.model.instructions.mow.*;
import domain.service.MowService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import utils.StringValidator;

import java.nio.CharBuffer;
import java.util.List;
import java.util.stream.Collectors;

import static domain.model.instructions.mow.MoveMowInstruction.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({ @Inject}))
public class MoveMowInstructionFactory {

    private MowService mowService;

    public Instruction build(String rawInstructions) {

        if(StringValidator.isNullOrEmpty(rawInstructions))
            throw new IllegalArgumentException("Empty instructions");

        List<Character> instructionsInCharFormat = CharBuffer.wrap(rawInstructions.toCharArray())
                .chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.toList());

        List<MoveMowInstruction> moveMowInstructions = instructionsInCharFormat.stream()
                .map(this::fromLibelle)
                .collect(Collectors.toList());

        return new MoveMowInstructionList(moveMowInstructions);
    }

    private MoveMowInstruction fromLibelle(char libelle) {
        if(MOVE_MOW_FORWARD_INSTRUCTION == libelle)
            return new MoveMowForwardInstruction(mowService);
        if(ROTATE_LEFT_INSTRUCTION == libelle)
            return new RotateLeftInstruction(mowService);
        if(ROTATE_RIGHT_INSTRUCTION == libelle)
            return new RotateRightInstruction(mowService);
        throw new IllegalArgumentException("Incorrect move mow instruction " + libelle);
    }
}
