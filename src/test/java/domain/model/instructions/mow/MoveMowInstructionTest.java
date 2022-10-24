package domain.model.instructions.mow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoveMowInstructionTest {
    @Test
    void should_return_left_instruction() {
        MoveMowInstruction mowInstruction = MoveMowInstruction.fromLibelle('G');

        assertThat(mowInstruction).isInstanceOf(MoveMowOnTheLeftInstruction.class);
    }

    @Test
    void should_return_right_instruction() {
        MoveMowInstruction mowInstruction = MoveMowInstruction.fromLibelle('D');

        assertThat(mowInstruction).isInstanceOf(MoveMowOnTheRightInstruction.class);
    }

    @Test
    void should_return_forward_instruction() {
        MoveMowInstruction mowInstruction = MoveMowInstruction.fromLibelle('A');

        assertThat(mowInstruction).isInstanceOf(MoveMowForwardInstruction.class);
    }

    @Test
    void should_throw_error() {
        MoveMowInstruction mowInstruction = MoveMowInstruction.fromLibelle('A');

        assertThat(mowInstruction).isInstanceOf(MoveMowForwardInstruction.class);
    }

    @ParameterizedTest
    @ValueSource(chars = {'f', 'g'})
    public void should_throw_error(char value){
        assertThrows(IllegalArgumentException.class, () -> {
            MoveMowInstruction.fromLibelle(value);
        });
    }
}