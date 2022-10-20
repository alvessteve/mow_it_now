package domain.model.instructions.mow;

import domain.model.instructions.Instruction;
import domain.service.MowService;

public class MowCreationInstruction extends Instruction {

    private MowService mowService;

    public MowCreationInstruction(String content) {
        super(content);
    }

    public MowCreationInstruction(MowService mowService) {
        this.mowService = mowService;
    }

    @Override
    public void execute() {
        this.mowService.create();
    }
}
