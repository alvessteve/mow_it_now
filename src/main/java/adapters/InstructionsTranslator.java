package adapters;

import domain.factory.InstructionFactory;
import domain.model.instructions.Instruction;

import java.util.Collection;

public class InstructionsTranslator {
    public Collection<Instruction> translateToInstruction(Collection<String> rawInstructions) {
        return rawInstructions.stream().map(InstructionFactory::build).toList();
    }
}
