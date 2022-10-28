package domain.factory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.inject.util.Modules;
import domain.exceptions.MalformedInstructionException;
import domain.model.instructions.Instruction;
import domain.model.instructions.mow.MoveMowForwardInstruction;
import domain.model.instructions.mow.MoveMowInstructionList;
import domain.model.instructions.mow.RotateLeftInstruction;
import domain.model.instructions.mow.RotateRightInstruction;
import infrastructure.dependencyinversion.MowitnowModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ports.GrassRepository;
import ports.MowRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoveMowInstructionFactoryTest {

    private Injector injector;
    private MoveMowInstructionFactory moveMowInstructionFactory;

    @Bind
    @Mock
    private MowRepository mowRepository;

    @Bind
    @Mock
    private GrassRepository grassRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        injector = Guice.createInjector(Modules.override(new MowitnowModule()).with(BoundFieldModule.of(this)));
        moveMowInstructionFactory = injector.getInstance(MoveMowInstructionFactory.class);
    }

    @AfterEach
    void tearDown() {
        injector = null;
    }

    @Test
    void should_return_left_right_forward_instructions() {
        String rawInstructions = "GDA";

        Instruction instruction = moveMowInstructionFactory.build(rawInstructions);

        assertThat(instruction).isInstanceOf(MoveMowInstructionList.class);

        MoveMowInstructionList mowInstructionList = (MoveMowInstructionList) instruction;
        assertThat(mowInstructionList.getMoveMowInstructions()).hasSize(3);
        assertThat(mowInstructionList.getMoveMowInstructions().get(0)).isInstanceOf(RotateLeftInstruction.class);
        assertThat(mowInstructionList.getMoveMowInstructions().get(1)).isInstanceOf(RotateRightInstruction.class);
        assertThat(mowInstructionList.getMoveMowInstructions().get(2)).isInstanceOf(MoveMowForwardInstruction.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "1", "agd"})
    public void should_throw_malformed_exception(String value){
        assertThrows(MalformedInstructionException.class, () -> {
            moveMowInstructionFactory.build(value);
        });
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    @ValueSource(strings = {""})
    public void should_throw_illegal_argument_exception(String value){
        assertThrows(IllegalArgumentException.class, () -> {
            moveMowInstructionFactory.build(value);
        });
    }
}