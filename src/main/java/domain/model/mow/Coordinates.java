package domain.model.mow;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Coordinates {

    int x;
    int y;

    public Coordinates up() {
        return new Coordinates(x, y + 1);
    }

    public  Coordinates down(){
        return new Coordinates(x, y - 1);
    }

    public Coordinates right(){
        return new Coordinates(x+ 1, y);
    }

    public Coordinates left(){
        return new Coordinates(x - 1, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
