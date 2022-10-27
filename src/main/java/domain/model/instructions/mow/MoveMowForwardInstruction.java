package domain.model.instructions.mow;

import domain.service.MowService;

public class MoveMowForwardInstruction extends MoveMowInstruction {
    public MoveMowForwardInstruction(MowService mowService) {
        this.mowService = mowService;
    }

    @Override
    public void execute() {
        this.mowService.forward();
    }
}
