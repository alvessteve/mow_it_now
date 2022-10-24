package domain.factory;

import domain.model.instructions.Instruction;
import domain.model.instructions.mow.MoveMowForwardInstruction;
import domain.model.instructions.mow.MoveMowInstructionList;
import domain.model.instructions.mow.RotateLeftInstruction;
import domain.model.instructions.mow.RotateRightInstruction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoveMowInstructionFactoryTest {

    @Test
    void should_return_left_right_forward_instructions() {
        String rawInstructions = "GDA";

        Instruction instruction = MoveMowInstructionFactory.build(rawInstructions);

        assertThat(instruction).isInstanceOf(MoveMowInstructionList.class);

        MoveMowInstructionList mowInstructionList = (MoveMowInstructionList) instruction;
        assertThat(mowInstructionList.getMoveMowInstructions()).hasSize(3);
        assertThat(mowInstructionList.getMoveMowInstructions().get(0)).isInstanceOf(RotateLeftInstruction.class);
        assertThat(mowInstructionList.getMoveMowInstructions().get(1)).isInstanceOf(RotateRightInstruction.class);
        assertThat(mowInstructionList.getMoveMowInstructions().get(2)).isInstanceOf(MoveMowForwardInstruction.class);
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    @ValueSource(strings = {"", " ", "1", "agd"})
    public void should_throw_error(String value){
        assertThrows(IllegalArgumentException.class, () -> {
            MoveMowInstructionFactory.build(value);
        });
    }
}