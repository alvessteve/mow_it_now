package domain.model.mow.orientation;

import domain.model.mow.Coordinates;

class WestOrientation extends Orientation {
    @Override
    public Orientation clockwise() {
        return NORTH;
    }

    @Override
    public Orientation counterclockwise() {
        return SOUTH;
    }

    @Override
    public Coordinates forward(Coordinates coordinates) {
        return coordinates.left();
    }

    @Override
    public String toString() {
        return "W";
    }
}
