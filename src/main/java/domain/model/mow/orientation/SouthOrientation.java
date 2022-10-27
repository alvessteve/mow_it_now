package domain.model.mow.orientation;

import domain.model.mow.Coordinates;

public class SouthOrientation extends Orientation {
    @Override
    public Orientation clockwise() {
        return new WestOrientation();
    }

    @Override
    public Orientation counterclockwise() {
        return new EastOrientation();
    }

    @Override
    public Coordinates forward(Coordinates coordinates) {
        return coordinates.down();
    }

    @Override
    public String toString() {
        return "S";
    }
}
