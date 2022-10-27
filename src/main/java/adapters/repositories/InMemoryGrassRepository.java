package adapters.repositories;

import domain.model.Grass;
import lombok.NoArgsConstructor;
import ports.GrassRepository;

@NoArgsConstructor
public class InMemoryGrassRepository implements GrassRepository {

    private Grass grass;

    @Override
    public Grass fetch(){
        return grass;
    }

    @Override
    public void save(Grass grass) {
        this.grass = grass;
    }
}
