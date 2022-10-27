package domain.factory;

import domain.model.Grass;
import domain.model.instructions.GrassCreationInstruction;
import domain.model.mow.Coordinates;
import utils.StringValidator;

import static java.lang.Integer.parseInt;

public class GrassFactory {
    private final static int X_COORDINATES_INDEX = 0;
    private final static int Y_COORDINATES_INDEX = 1;
    private final static String SEPARATOR = " ";

    public static Grass build(String content){

        if(StringValidator.isNullOrEmpty(content))
            throw new IllegalArgumentException("An instruction content must be provided to build a valid grass");
        if(!content.matches(GrassCreationInstruction.CREATION_GRASS_INSTRUCTION_FORMAT))
            throw new IllegalArgumentException("wrong grass creation instruction format : " + content);

        String[] positionDetails = content.split(SEPARATOR);
        var coordinates = new Coordinates(parseInt(positionDetails[X_COORDINATES_INDEX]), parseInt(positionDetails[Y_COORDINATES_INDEX]));
        return new Grass(coordinates);
    }
}
