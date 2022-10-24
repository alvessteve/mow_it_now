package domain.model.instructions.mow;

import domain.model.instructions.Instruction;
import domain.service.MowService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class MoveMowInstruction extends Instruction {

    public final static String MOVE_MOW_INSTRUCTION = "^[G,A,D]+$";
    public final static char ROTATE_LEFT_INSTRUCTION = 'G';
    public final static char ROTATE_RIGHT_INSTRUCTION = 'D';
    public final static char MOVE_MOW_FORWARD_INSTRUCTION = 'A';

    protected MowService mowService;

    public static MoveMowInstruction fromLibelle(char libelle) {
        if(MOVE_MOW_FORWARD_INSTRUCTION == libelle)
            return new MoveMowForwardInstruction();
        if(ROTATE_LEFT_INSTRUCTION == libelle)
            return new RotateLeftInstruction();
        if(ROTATE_RIGHT_INSTRUCTION == libelle)
            return new RotateRightInstruction();
        throw new IllegalArgumentException("Incorrect move mow instruction " + libelle);
    }

}
