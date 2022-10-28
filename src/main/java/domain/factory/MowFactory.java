package domain.factory;

import domain.exceptions.MalformedCoordinatesInstruction;
import domain.exceptions.MalformedInstructionException;
import domain.model.instructions.mow.MowCreationInstruction;
import domain.model.mow.Coordinates;
import domain.model.mow.Mow;
import domain.model.mow.Position;
import domain.model.mow.orientation.Orientation;
import utils.StringValidator;

import static java.lang.Integer.parseInt;

public class MowFactory {

    private final static int X_COORDINATES_INDEX = 0;
    private final static int Y_COORDINATES_INDEX = 1;
    private final static int ORIENTATION_INDEX = 2;
    private final static String SEPARATOR = " ";

    public static Mow build(String instruction) {
        if(StringValidator.isNullOrEmpty(instruction))
            throw new IllegalArgumentException("A rawInstructions must be provided to generate an instruction");
        if(!instruction.matches(MowCreationInstruction.CREATION_MOW_INSTRUCTION_FORMAT))
            throw new MalformedInstructionException("Wrong mow creation instruction format : " + instruction);

        String[] positionDetails = instruction.split(SEPARATOR);

        Coordinates coordinates;
        try {
            var x = parseInt(positionDetails[X_COORDINATES_INDEX]);
            var y = parseInt(positionDetails[Y_COORDINATES_INDEX]);
            coordinates = new Coordinates(x, y);
        } catch (RuntimeException e) {
            throw new MalformedCoordinatesInstruction("Incorrect coordinates format", e);
        }

        var orientation = Orientation.fromLibelle(positionDetails[ORIENTATION_INDEX]);

        var position = new Position(coordinates, orientation);

        return new Mow(position);
    }
}
