package domain.model.mow.orientation;

public class EastOrientation extends Orientation {
    @Override
    public Orientation spinRight() {
        return new SouthOrientation();
    }

    @Override
    public Orientation spinLeft() {
        return new NorthOrientation();
    }

}
