package domain.model.mow.orientation;

public class WestOrientation extends Orientation {
    @Override
    public Orientation clockwise() {
        return new NorthOrientation();
    }

    @Override
    public Orientation counterclockwise() {
        return new SouthOrientation();
    }

}
