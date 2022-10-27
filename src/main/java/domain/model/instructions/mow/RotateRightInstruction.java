package domain.model.instructions.mow;

import domain.service.MowService;

public class RotateRightInstruction extends MoveMowInstruction {
    public RotateRightInstruction(MowService mowService) {
        this.mowService = mowService;
    }

    @Override
    public void execute() {
        this.mowService.rotateClockwise();
    }
}
