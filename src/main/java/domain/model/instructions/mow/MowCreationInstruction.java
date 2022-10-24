package domain.model.instructions.mow;

import adapters.repositories.InMemoryGrassRepository;
import adapters.repositories.InMemoryMowRepository;
import domain.model.instructions.Instruction;
import domain.service.GrassService;
import domain.service.MowService;

public class MowCreationInstruction extends Instruction {

    public final static String CREATION_MOW_INSTRUCTION_FORMAT = "^\\d \\d [N,E,W,S]{1}$";

    private final MowService mowService;

    public MowCreationInstruction(String content) {
        super(content);
        this.mowService = new MowService(new InMemoryMowRepository(), new GrassService(new InMemoryGrassRepository()));
    }

    public MowCreationInstruction(MowService mowService) {
        this.mowService = mowService;
    }

    @Override
    public void execute() {
        this.mowService.create(content);
    }
}
