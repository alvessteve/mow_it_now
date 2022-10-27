package domain.model.instructions.mow;

import domain.model.instructions.Instruction;
import domain.service.MowService;

public class MowCreationInstruction extends Instruction {

    public final static String CREATION_MOW_INSTRUCTION_FORMAT = "^\\d \\d [N,E,W,S]{1}$";

    private final MowService mowService;

    public MowCreationInstruction(String content, MowService mowService) {
        super(content);
        this.mowService = mowService;
    }

    @Override
    public void execute() {
        this.mowService.create(content);
    }
}
