package domain.model.mow;

import domain.exceptions.OutsideGrassMoveException;
import domain.model.Grass;
import lombok.Getter;

@Getter
public class Mow {

    private Position position;

    public Mow(Position position) {
        if(position == null)
            throw new IllegalArgumentException("No position to generate a mow");
        this.position = position;
    }

    public void rotateClockwise(){
        position.rotateClockwise();
    }

    public void rotateCounterclockwise(){
        position.rotateCounterclockwise();
    }

    public void move(Grass grass) {
        Position newPosition = position.move();
        if(grass.outOfBounds(newPosition.getCoordinates())) {
            position = newPosition;
        } else {
            throw new OutsideGrassMoveException("Move out of bound for mow located at " + this.position + " and new position " + newPosition + " and grass dimension " + grass);
        }
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
