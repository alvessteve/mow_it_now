package domain.model.instructions.mow;

public class RotateRightInstruction extends MoveMowInstruction {
    @Override
    public void execute() {
        this.mowService.rotateClockwise();
    }
}
