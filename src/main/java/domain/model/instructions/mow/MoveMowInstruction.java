package domain.model.instructions.mow;

import domain.model.instructions.Instruction;
import domain.service.MowService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class MoveMowInstruction extends Instruction {

    public final static String MOVE_MOW_INSTRUCTION = "^[G,A,D]+$";
    public final static char ROTATE_LEFT_INSTRUCTION = 'G';
    public final static char ROTATE_RIGHT_INSTRUCTION = 'D';
    public final static char MOVE_MOW_FORWARD_INSTRUCTION = 'A';

    protected MowService mowService;

}
