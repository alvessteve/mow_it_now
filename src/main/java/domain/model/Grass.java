package domain.model;

import domain.model.mow.Coordinates;
import lombok.Getter;

@Getter
public class Grass {

    private final Coordinates dimension;

    public Grass(Coordinates dimension) {
        if (dimension == null)
            throw new IllegalArgumentException("null sizing to generate a grass");
        this.dimension = dimension;
    }

    public boolean outOfBounds(Coordinates coordinates) {
        if (coordinates.getX() < 0 || coordinates.getX() > dimension.getX() + 1) {
            return false;
        }
        return coordinates.getY() >= 0 && coordinates.getY() <= dimension.getY() + 1;
    }

    @Override
    public String toString() {
        return dimension.toString();
    }
}
