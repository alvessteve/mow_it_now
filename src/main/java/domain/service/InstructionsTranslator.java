package domain.service;

import com.google.inject.Inject;
import domain.factory.InstructionFactory;
import domain.model.instructions.Instruction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({ @Inject}))
public class InstructionsTranslator {

    private final InstructionFactory instructionFactory;

    public Collection<Instruction> translateToInstruction(Collection<String> rawInstructions) {
        return rawInstructions.stream().map(instructionFactory::build).toList();
    }
}
