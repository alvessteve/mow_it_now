package domain.model.mow.orientation;

public class WestOrientation extends Orientation {
    @Override
    public Orientation spinRight() {
        return new NorthOrientation();
    }

    @Override
    public Orientation spinLeft() {
        return new SouthOrientation();
    }

}
