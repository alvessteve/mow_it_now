package ports;

import domain.model.Grass;
import domain.model.mow.Mow;

public interface Displayer {
    void mowPosition(Mow now);
    void grassDimension(Grass grass);
}
