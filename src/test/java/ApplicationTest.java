import adapters.repositories.InMemoryGrassRepository;
import adapters.repositories.InMemoryMowRepository;
import domain.model.Grass;
import domain.model.mow.Coordinates;
import domain.model.mow.Mow;
import domain.model.mow.orientation.NorthOrientation;
import domain.model.mow.orientation.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ports.GrassRepository;
import ports.MowRepository;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {
    private final static String FILE_PATH = "./src/test/resources/";

    private GrassRepository grassRepository;
    private MowRepository mowRepository;

    @BeforeEach
    void setup(){
        grassRepository = new InMemoryGrassRepository();
        mowRepository = new InMemoryMowRepository();
    }

    @Test
    public void should_retrieve_correct_grass_dimensions(){
        Coordinates expectedCoordinates = new Coordinates(5,5);
        Application.main(FILE_PATH + "mows_only_grass.txt");

        Grass grass = grassRepository.fetch();
        assertThat(grass.getDimension()).isEqualTo(expectedCoordinates);
    }

    @Test
    public void should_retrieve_correct_mow_informations(){
        Coordinates expectedCoordinates = new Coordinates(1,2);

        Application.main(FILE_PATH + "mows_only_mow.txt");

        Mow mow = mowRepository.currentMowMoving();
        assertThat(mow.getPosition().getCoordinates()).isEqualTo(expectedCoordinates);
        assertThat(mow.getPosition().getOrientation()).isInstanceOf(NorthOrientation.class);
    }
}