package domain.model.mow;

import domain.model.mow.orientation.Orientation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
}
