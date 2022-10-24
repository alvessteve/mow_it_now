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

    public void forward(Grass grass) {

    }
}
