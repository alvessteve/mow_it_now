import adapters.file.FileAdapter;
import adapters.input.InputAdapter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import domain.model.mow.Coordinates;
import domain.model.mow.Mow;
import domain.model.mow.orientation.EastOrientation;
import domain.model.mow.orientation.NorthOrientation;
import domain.model.mow.orientation.SouthOrientation;
import infrastructure.dependencyinversion.MowitnowModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ports.MowRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationTest {
    private final static String FILE_PATH = "./src/test/resources/";

    private Injector injector;
    private MowRepository mowRepository;
    private FileAdapter fileAdapter;
    private InputAdapter inputAdapter;

    @BeforeEach
    void setUp() {
        injector = Guice.createInjector(new MowitnowModule());
        mowRepository = injector.getInstance(MowRepository.class);
        fileAdapter = injector.getInstance(FileAdapter.class);
        inputAdapter = injector.getInstance(InputAdapter.class);
    }

    @AfterEach
    void tearDown() {
        injector = null;
    }

    @Test
    public void should_retrieve_correct_mow_informations(){
        Coordinates expectedCoordinates = new Coordinates(1,2);

        fileAdapter.call(FILE_PATH + "mows_only_mow.txt");

        Mow mow = mowRepository.currentMowMoving();
        assertThat(mow.getPosition().getCoordinates()).isEqualTo(expectedCoordinates);
        assertThat(mow.getPosition().getOrientation()).isInstanceOf(NorthOrientation.class);
    }

    @Test
    public void should_retrieve_correct_mow_position(){
        Coordinates expectedCoordinatesForFirstMow = new Coordinates(1,3);
        Coordinates expectedCoordinatesForSecondMow = new Coordinates(5,1);

        fileAdapter.call(FILE_PATH + "mows.txt");

        List<Mow> mows = mowRepository.all();
        assertThat(mows).isNotEmpty();
        assertThat(mows).size().isEqualTo(2);
        assertThat(mows.get(0).getPosition().getCoordinates()).isEqualTo(expectedCoordinatesForFirstMow);
        assertThat(mows.get(0).getPosition().getOrientation()).isInstanceOf(NorthOrientation.class);
        assertThat(mows.get(1).getPosition().getCoordinates()).isEqualTo(expectedCoordinatesForSecondMow);
        assertThat(mows.get(1).getPosition().getOrientation()).isInstanceOf(EastOrientation.class);
    }

    @Test
    public void should_retrieve_correct_mow_position_with_3_mows(){
        Coordinates expectedCoordinatesForFirstMow = new Coordinates(1,3);
        Coordinates expectedCoordinatesForSecondMow = new Coordinates(5,1);
        Coordinates expectedCoordinatesForThirdMow = new Coordinates(3,3);

        fileAdapter.call(FILE_PATH + "mows_3.txt");

        List<Mow> mows = mowRepository.all();
        assertThat(mows).isNotEmpty();
        assertThat(mows).size().isEqualTo(3);
        assertThat(mows.get(0).getPosition().getCoordinates()).isEqualTo(expectedCoordinatesForFirstMow);
        assertThat(mows.get(0).getPosition().getOrientation()).isInstanceOf(NorthOrientation.class);
        assertThat(mows.get(1).getPosition().getCoordinates()).isEqualTo(expectedCoordinatesForSecondMow);
        assertThat(mows.get(1).getPosition().getOrientation()).isInstanceOf(EastOrientation.class);
        assertThat(mows.get(2).getPosition().getCoordinates()).isEqualTo(expectedCoordinatesForThirdMow);
        assertThat(mows.get(2).getPosition().getOrientation()).isInstanceOf(SouthOrientation.class);
    }

    @Test
    void should_throw_exception_because_of_no_grass() {
        String[] inputs = new String[]{
                "10 10 E"
        };

        assertThrows(IllegalArgumentException.class, () -> {
            inputAdapter.call(inputs);
        });
    }

    @Test
    void should_throw_exception_because_of_no_mow() {
        String[] inputs = new String[]{
                "10 10", "GAGADDDDDGAA"
        };

        assertThrows(IllegalArgumentException.class, () -> {
            inputAdapter.call(inputs);
        });
    }

    @Test
    void should_throw_exception_because_of_bad_move_instruction() {
        String[] inputs = new String[]{
                "10 10", "7 8 N", "GAGATTTDDDDDGAA"
        };

        assertThrows(IllegalArgumentException.class, () -> {
            inputAdapter.call(inputs);
        });
    }
}