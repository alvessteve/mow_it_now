package domain.service;

import adapters.repositories.InMemoryGrassRepository;
import domain.factory.GrassFactory;
import domain.model.Grass;
import ports.GrassRepository;

public class GrassService {

    private final GrassRepository grassRepository;

    public GrassService(GrassRepository grassRepository) {
        this.grassRepository = new InMemoryGrassRepository();
    }

    public void create(String coordinates) {
        Grass grass = GrassFactory.build(coordinates);
        grassRepository.save(grass);
    }

    public Grass retrieve(){
        return grassRepository.fetch();
    }
}
