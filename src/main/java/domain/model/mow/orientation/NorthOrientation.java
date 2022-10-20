package domain.model.mow.orientation;

public class NorthOrientation extends Orientation {

    @Override
    public Orientation spinRight() {
        return new EastOrientation();
    }

    @Override
    public Orientation spinLeft() {
        return new WestOrientation();
    }

}
