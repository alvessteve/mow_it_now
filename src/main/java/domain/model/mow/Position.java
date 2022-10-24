package domain.model.mow;

import domain.model.mow.orientation.Orientation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Position {

    private Coordinates coordinates;
    private Orientation orientation;

}
