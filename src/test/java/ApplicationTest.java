import adapters.repositories.InMemoryGrassRepository;
import domain.model.Grass;
import domain.model.mow.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ports.GrassRepository;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {
    private final static String FILE_PATH = "./src/test/resources/";

    private GrassRepository grassRepository;

    @BeforeEach
    void setup(){
        grassRepository = new InMemoryGrassRepository();
    }

    @Test
    public void should_retrieve_correct_grass_dimensions(){
        Coordinates expectedCoordinates = new Coordinates(5,5);
        Application.main(FILE_PATH + "mows_only_grass.txt");

        Grass grass = grassRepository.fetch();
        assertThat(grass.getDimension()).isEqualTo(expectedCoordinates);
    }
}