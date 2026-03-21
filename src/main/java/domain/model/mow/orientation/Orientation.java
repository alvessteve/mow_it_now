package domain.model.mow.orientation;

import domain.model.mow.Coordinates;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class Orientation {

    public final static String NORTH_INSTRUCTION = "N";
    public final static String SOUTH_INSTRUCTION = "S";
    public final static String EAST_INSTRUCTION = "E";
    public final static String WEST_INSTRUCTION = "W";

    // Singleton instances (Flyweight pattern)
    public static final Orientation NORTH = new NorthOrientation();
    public static final Orientation SOUTH = new SouthOrientation();
    public static final Orientation EAST = new EastOrientation();
    public static final Orientation WEST = new WestOrientation();

    public static Orientation fromLibelle(String libelle){
        if(SOUTH_INSTRUCTION.equals(libelle))
            return SOUTH;
        if(NORTH_INSTRUCTION.equals(libelle))
            return NORTH;
        if(EAST_INSTRUCTION.equals(libelle))
            return EAST;
        if(WEST_INSTRUCTION.equals(libelle))
            return WEST;
        throw new IllegalArgumentException("incorrect Orientation Label : " + libelle);
    }

    public abstract Orientation clockwise();
    public abstract Orientation counterclockwise();
    public abstract Coordinates forward(Coordinates coordinates);

}
