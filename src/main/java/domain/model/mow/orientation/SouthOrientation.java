package domain.model.mow.orientation;

public class SouthOrientation extends Orientation {
    @Override
    public Orientation spinRight() {
        return new WestOrientation();
    }

    @Override
    public Orientation spinLeft() {
        return new EastOrientation();
    }
}
