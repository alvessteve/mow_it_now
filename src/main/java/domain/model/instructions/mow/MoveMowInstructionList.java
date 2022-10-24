package domain.model.instructions.mow;

import domain.model.instructions.Instruction;
import lombok.Data;

import java.util.Collection;

@Data
public class MoveMowInstructionList extends Instruction {

    private final Collection<MoveMowInstruction> moveMowInstructions;

    @Override
    public void execute() {
        moveMowInstructions.forEach(Instruction::execute);
    }
}
