package domain.factory;

import domain.model.mow.Coordinates;
import domain.model.mow.Mow;
import domain.model.mow.Position;
import domain.model.mow.orientation.Orientation;

import static java.lang.Integer.parseInt;

public class MowFactory {

    private final static int X_COORDINATES_INDEX = 0;
    private final static int Y_COORDINATES_INDEX = 1;
    private final static int ORIENTATION_INDEX = 2;
    private final static String SEPARATOR = " ";

    public static Mow build(String instruction) {
        String[] positionDetails = instruction.split(SEPARATOR);

        Coordinates coordinates = Coordinates.builder().x(parseInt(positionDetails[X_COORDINATES_INDEX])).y(parseInt(positionDetails[Y_COORDINATES_INDEX])).build();

        Orientation orientation = Orientation.fromLibelle(positionDetails[ORIENTATION_INDEX]);

        Position position = Position.builder().coordinates(coordinates).orientation(orientation).build();

        return Mow.builder().position(position).build();
    }
}
