package domain.model.mow.orientation;

import domain.model.mow.Coordinates;

public class EastOrientation extends Orientation {
    @Override
    public Orientation clockwise() {
        return new SouthOrientation();
    }

    @Override
    public Orientation counterclockwise() {
        return new NorthOrientation();
    }

    @Override
    public Coordinates forward(Coordinates coordinates) {
        return coordinates.right();
    }
}
