package domain.model.mow.orientation;

public class SouthOrientation extends Orientation {
    @Override
    public Orientation clockwise() {
        return new WestOrientation();
    }

    @Override
    public Orientation counterclockwise() {
        return new EastOrientation();
    }
}
