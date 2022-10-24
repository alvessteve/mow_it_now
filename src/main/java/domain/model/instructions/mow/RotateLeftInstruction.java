package domain.model.instructions.mow;

public class RotateLeftInstruction extends MoveMowInstruction {
    @Override
    public void execute() {
        this.mowService.rotateCounterclockwise();
    }
}
