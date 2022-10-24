package domain.service;

import adapters.repositories.InMemoryGrassRepository;
import adapters.repositories.InMemoryMowRepository;
import domain.factory.MowFactory;
import domain.model.Grass;
import domain.model.mow.Mow;
import ports.MowRepository;

public class MowService {

    private final MowRepository mowRepository;
    private final GrassService grassService;

    public MowService(MowRepository mowRepository, GrassService grassService) {
        this.grassService = new GrassService(new InMemoryGrassRepository());
        this.mowRepository = new InMemoryMowRepository();
    }

    public void create(String instruction){

        Mow mow = MowFactory.build(instruction);

        Grass grass = grassService.retrieve();

        if(!grass.outOfBounds(mow.getPosition().getCoordinates())){
            mowRepository.add(mow);
            mowRepository.setToCurrentMowMowing(mow);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}
