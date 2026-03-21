package domain.model.mow.orientation;

import domain.model.mow.Coordinates;

class SouthOrientation extends Orientation {
    @Override
    public Orientation clockwise() {
        return WEST;
    }

    @Override
    public Orientation counterclockwise() {
        return EAST;
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
