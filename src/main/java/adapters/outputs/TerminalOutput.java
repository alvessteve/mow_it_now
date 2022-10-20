package adapters.outputs;

import domain.model.Grass;
import domain.model.mow.Mow;
import ports.Displayer;

public class TerminalOutput implements Displayer {
    @Override
    public void mowPosition(Mow mow) {

    }

    @Override
    public void grassDimension(Grass grass) {
        System.out.println(grass.getDimension().toString() + "\n");
    }
}
