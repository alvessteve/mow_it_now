package domain.model.mow.orientation;

import domain.model.mow.Coordinates;

class EastOrientation extends Orientation {
    @Override
    public Orientation clockwise() {
        return SOUTH;
    }

    @Override
    public Orientation counterclockwise() {
        return NORTH;
    }

    @Override
    public Coordinates forward(Coordinates coordinates) {
        return coordinates.right();
    }

    @Override
    public String toString() {
        return "E";
    }
}
