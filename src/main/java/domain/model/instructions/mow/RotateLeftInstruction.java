package domain.model.instructions.mow;

import domain.service.MowService;

public class RotateLeftInstruction extends MoveMowInstruction {
    public RotateLeftInstruction(MowService mowService) {
        this.mowService = mowService;
    }

    @Override
    public void execute() {
        this.mowService.rotateCounterclockwise();
    }
}
