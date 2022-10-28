package domain.model.instructions.mow;

import domain.model.instructions.Instruction;
import lombok.Getter;

import java.util.List;

@Getter
public class MoveMowInstructionList extends Instruction {

    private final List<MoveMowInstruction> moveMowInstructions;

    public MoveMowInstructionList(List<MoveMowInstruction> moveMowInstructions) {
        if(moveMowInstructions.isEmpty())
            throw new IllegalArgumentException("No move mow instructions have been passed");
        this.moveMowInstructions = moveMowInstructions;
    }

    @Override
    public void execute() {
        moveMowInstructions.forEach(Instruction::execute);
    }
}
