package adapters.repositories;

import domain.model.Grass;
import lombok.Getter;
import ports.GrassRepository;

/**
 * TODO: explain why use a singleton
 */
@Getter
public class InMemoryGrassRepository implements GrassRepository {

    private Grass grass;

    private static InMemoryGrassRepository instance;

    public static InMemoryGrassRepository getInstance(){
        if(instance == null){
            synchronized (InMemoryGrassRepository.class){
                instance = new InMemoryGrassRepository();
            }
        }
        return instance;
    }

    @Override
    public Grass fetch(){
        return getInstance().getGrass();
    }

    @Override
    public void save(Grass grass) {
        getInstance().grass = grass;
    }
}
