package domain.model.instructions;

import adapters.repositories.InMemoryGrassRepository;
import domain.service.GrassService;

public class GrassCreationInstruction extends Instruction {

    public final static String CREATION_GRASS_INSTRUCTION_FORMAT = "^\\d \\d$";

    private GrassService grassService;

    public GrassCreationInstruction(String content) {
        super(content);
        this.grassService = new GrassService(new InMemoryGrassRepository());
    }

    public GrassCreationInstruction(GrassService grassService) {
        this.grassService = grassService;
    }

    @Override
    public void execute() {
        this.grassService.create(content);
    }
}
