package domain.model.mow.orientation;

public abstract class Orientation {

    public final static String NORTH_INSTRUCTION = "N";
    public final static String SOUTH_INSTRUCTION = "S";
    public final static String EAST_INSTRUCTION = "E";
    public final static String WEST_INSTRUCTION = "W";

    public static Orientation fromLibelle(String libelle){
        if(SOUTH_INSTRUCTION.equals(libelle))
            return new SouthOrientation();
        if(NORTH_INSTRUCTION.equals(libelle))
            return new NorthOrientation();
        if(EAST_INSTRUCTION.equals(libelle))
            return new EastOrientation();
        if(WEST_INSTRUCTION.equals(libelle))
            return new WestOrientation();
        throw new IllegalArgumentException("incorrect Orientation Label : " + libelle);
    }

    public abstract Orientation clockwise();
    public abstract Orientation counterclockwise();

}
