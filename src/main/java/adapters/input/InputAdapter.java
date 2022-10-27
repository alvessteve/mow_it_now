package adapters.input;

import adapters.InstructionsTranslator;
import com.google.inject.Inject;
import domain.model.instructions.Instruction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import ports.Client;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC, onConstructor = @__({ @Inject}))
public class  InputAdapter {

    private final Client client;
    private final InstructionsTranslator inputTranslator;

    public void call(String[] rawInstructions) {
        List<String> rawInstructionsList = Arrays.stream(rawInstructions).toList();
        Collection<Instruction> instructions = inputTranslator.translateToInstruction(rawInstructionsList);
        client.execute(instructions);
    }
}
