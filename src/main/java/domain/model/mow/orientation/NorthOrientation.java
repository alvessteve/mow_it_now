package domain.model.mow.orientation;

import domain.model.mow.Coordinates;

public class NorthOrientation extends Orientation {

    @Override
    public Orientation clockwise() {
        return new EastOrientation();
    }

    @Override
    public Orientation counterclockwise() {
        return new WestOrientation();
    }

    @Override
    public Coordinates forward(Coordinates coordinates) {
        return coordinates.up();
    }

    @Override
    public String toString() {
        return "N";
    }
}
