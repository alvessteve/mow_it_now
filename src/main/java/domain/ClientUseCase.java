package domain;

import domain.model.instructions.Instruction;
import domain.service.GrassService;
import ports.Client;
import ports.Displayer;

import java.util.Collection;

public class ClientUseCase implements Client {

    private final Displayer displayer;
    private final GrassService grassService;

    public ClientUseCase(Displayer displayer, GrassService grassService) {
        this.displayer = displayer;
        this.grassService = grassService;
    }

    @Override
    public void execute(Collection<Instruction> instructions) {
        instructions.forEach(Instruction::execute);
        displayer.grassDimension(grassService.retrieve());
    }
}
