package domain.model.mow;

import domain.model.Grass;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mow {

    private Position position;

    public void rotateClockwise(){
        position.rotateClockwise();
    }

    public void rotateCounterclockwise(){
        position.rotateCounterclockwise();
    }

    public void move(Grass grass) {
        Position newPosition = position.move();
        if(!grass.outOfBounds(newPosition.getCoordinates())) {
            position = newPosition;
        } else {
            throw new IllegalArgumentException("out of bound move");
        }
    }
}
