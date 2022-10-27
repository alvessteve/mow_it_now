package domain.factory;

import domain.model.mow.Coordinates;
import domain.model.mow.Position;
import domain.model.mow.orientation.NorthOrientation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MowFactoryTest {

    @Test
    public void should_instanciate_mow(){
        var coordinates = new Coordinates(1,2);
        var expectedPosition = new Position(coordinates, new NorthOrientation());

        var mow = MowFactory.build("1 2 N");

        assertThat(mow).isNotNull();
        assertThat(mow.getPosition()).isEqualTo(expectedPosition);
    }

}