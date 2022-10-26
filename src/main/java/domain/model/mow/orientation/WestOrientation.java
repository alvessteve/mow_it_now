package domain.model.mow.orientation;

import domain.model.mow.Coordinates;

public class WestOrientation extends Orientation {
    @Override
    public Orientation clockwise() {
        return new NorthOrientation();
    }

    @Override
    public Orientation counterclockwise() {
        return new SouthOrientation();
    }

    @Override
    public Coordinates forward(Coordinates coordinates) {
        return coordinates.left();
    }

}
