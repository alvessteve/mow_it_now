package domain.model;

import domain.model.mow.Coordinates;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Grass {

    private final Coordinates dimension;

    public Grass(Coordinates dimension) {
        if (dimension == null)
            throw new IllegalArgumentException("null sizing to generate a grass");
        this.dimension = dimension;
    }

    public boolean isWithinBounds(Coordinates coordinates) {
        Objects.requireNonNull(coordinates, "coordinates cannot be null");

        boolean xWithinBounds = coordinates.getX() >= 0 && coordinates.getX() <= dimension.getX();
        boolean yWithinBounds = coordinates.getY() >= 0 && coordinates.getY() <= dimension.getY();

        return xWithinBounds && yWithinBounds;
    }

    @Override
    public String toString() {
        return dimension.toString();
    }
}
