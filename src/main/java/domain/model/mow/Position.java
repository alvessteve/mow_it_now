package domain.model.mow;

import domain.model.mow.orientation.Orientation;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Position {

    private Coordinates coordinates;
    private Orientation orientation;

    public void rotateClockwise() {
        this.orientation = orientation.clockwise();
    }

    public void rotateCounterclockwise() {
        this.orientation = orientation.counterclockwise();
    }

    public Position move() {
        Coordinates newCoordinates = orientation.forward(this.coordinates);
        return new Position(newCoordinates, orientation);
    }

    @Override
    public String toString() {
        return coordinates.toString() + " " + orientation.toString();
    }
}
