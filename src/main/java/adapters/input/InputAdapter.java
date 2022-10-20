package adapters.input;

import adapters.InstructionsTranslator;
import domain.model.instructions.Instruction;
import domain.ClientUseCase;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public record InputAdapter(InstructionsTranslator inputTranslator, ClientUseCase client) {
    public void call(String[] rawInstructions) {
        List<String> rawInstructionsList = Arrays.stream(rawInstructions).toList();
        Collection<Instruction> instructions = inputTranslator.translateToInstruction(rawInstructionsList);
        client.execute(instructions);
    }
}
