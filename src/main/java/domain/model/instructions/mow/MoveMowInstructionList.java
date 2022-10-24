package domain.model.instructions.mow;

import domain.model.instructions.Instruction;
import lombok.Data;

import java.util.List;

@Data
public class MoveMowInstructionList extends Instruction {

    private final List<MoveMowInstruction> moveMowInstructions;

    public MoveMowInstructionList(List<MoveMowInstruction> moveMowInstructions) {
        if(moveMowInstructions.isEmpty())
            throw new IllegalArgumentException("No instructions have been passed");
        this.moveMowInstructions = moveMowInstructions;
    }

    @Override
    public void execute() {
        moveMowInstructions.forEach(Instruction::execute);
    }
}
