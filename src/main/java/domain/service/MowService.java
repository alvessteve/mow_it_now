package domain.service;

import domain.factory.MowFactory;
import domain.model.Grass;
import domain.model.mow.Mow;
import ports.MowRepository;

public class MowService {

    private final MowRepository mowRepository;
    private final GrassService grassService;

    public MowService(MowRepository mowRepository, GrassService grassService) {
        this.grassService = grassService;
        this.mowRepository = mowRepository;
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

    public void rotateClockwise() {
        Mow mow = mowRepository.currentMowMoving();
        mow.rotateClockwise();
    }

    public void rotateCounterclockwise() {
        Mow mow = mowRepository.currentMowMoving();
        mow.rotateCounterclockwise();
    }

    public void forward() {
        Grass grass = grassService.retrieve();
        Mow mow = mowRepository.currentMowMoving();
        mow.move(grass);
    }
}
