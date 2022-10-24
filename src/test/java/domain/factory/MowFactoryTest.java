package domain.factory;

import domain.model.Grass;
import domain.model.mow.Coordinates;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MowFactoryTest {

    @Test
    public void should_instanciate_mow(){
        Coordinates expectedCoordinates = new Coordinates(1,2);

        Grass grass = GrassFactory.build("1 2 N");

        assertThat(grass).isNotNull();
        assertThat(grass.getDimension()).isEqualTo(expectedCoordinates);
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    @ValueSource(strings = {"", " ", "1", "a b"})
    public void should_throw_error(String value){
        assertThrows(IllegalArgumentException.class, () -> {
            MowFactory.build(value);
        });
    }

}