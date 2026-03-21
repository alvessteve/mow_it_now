package domain.model.mow.orientation;

import domain.model.mow.Coordinates;

class NorthOrientation extends Orientation {

    @Override
    public Orientation clockwise() {
        return EAST;
    }

    @Override
    public Orientation counterclockwise() {
        return WEST;
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
