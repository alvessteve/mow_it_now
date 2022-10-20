package domain.model;

import domain.model.mow.Coordinates;
import lombok.Data;

@Data
public class Grass {

    private Coordinates dimension;

    public Grass(Coordinates dimension) {
        if (dimension == null) {
            throw new IllegalArgumentException("null sizing to generate a grass...");
        }
        this.dimension = dimension;
    }

    public Coordinates getDimension(){
        return dimension;
    }

    public boolean outOfBounds(Coordinates coordinates) {
        if (coordinates.getX() < 0 || coordinates.getX() > dimension.getX() + 1) {
            return true;
        }
        return coordinates.getY() < 0 || coordinates.getY() > dimension.getY() + 1;
    }

}
