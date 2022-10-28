package adapters.repositories;

import domain.model.Grass;
import lombok.NoArgsConstructor;
import ports.GrassRepository;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@NoArgsConstructor
public class InMemoryGrassRepository implements GrassRepository {

    private Grass grass;

    @Override
    public Optional<Grass> fetch(){
        return ofNullable(grass);
    }

    @Override
    public void save(Grass grass) {
        this.grass = grass;
    }
}
