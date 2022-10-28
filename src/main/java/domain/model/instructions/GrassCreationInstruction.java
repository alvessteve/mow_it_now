package domain.model.instructions;

import domain.service.GrassService;

public class GrassCreationInstruction extends Instruction {

    public final static String CREATION_GRASS_INSTRUCTION_FORMAT = "^\\d+ \\d+$";

    private final GrassService grassService;

    public GrassCreationInstruction(String content, GrassService grassService) {
        super(content);
        this.grassService = grassService;
    }

    @Override
    public void execute() {
        this.grassService.create(content);
    }
}
