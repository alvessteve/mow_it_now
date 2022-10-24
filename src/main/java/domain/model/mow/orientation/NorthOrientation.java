package domain.model.mow.orientation;

public class NorthOrientation extends Orientation {

    @Override
    public Orientation clockwise() {
        return new EastOrientation();
    }

    @Override
    public Orientation counterclockwise() {
        return new WestOrientation();
    }

}
