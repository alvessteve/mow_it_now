package domain.factory;

import domain.exceptions.MalformedInstructionException;
import domain.model.mow.Coordinates;
import domain.model.mow.Position;
import domain.model.mow.orientation.NorthOrientation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MowFactoryTest {

    @Test
    public void should_instanciate_mow(){
        var coordinates = new Coordinates(1,2);
        var expectedPosition = new Position(coordinates, new NorthOrientation());

        var mow = MowFactory.build("1 2 N");

        assertThat(mow).isNotNull();
        assertThat(mow.getPosition()).isEqualTo(expectedPosition);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "1", "agd", "A B S", "1 B E", "A 2 N"})
    public void should_throw_malformed_exception(String value){
        assertThrows(MalformedInstructionException.class, () -> {
            MowFactory.build(value);
        });
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    @ValueSource(strings = {""})
    public void should_throw_illegal_argument_exception(String value){
        assertThrows(IllegalArgumentException.class, () -> {
            MowFactory.build(value);
        });
    }

}