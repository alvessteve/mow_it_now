package domain.model.mow.orientation;

public class EastOrientation extends Orientation {
    @Override
    public Orientation clockwise() {
        return new SouthOrientation();
    }

    @Override
    public Orientation counterclockwise() {
        return new NorthOrientation();
    }

}
