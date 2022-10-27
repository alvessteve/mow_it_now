package domain.service;

import com.google.inject.Inject;
import domain.factory.MowFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import ports.MowRepository;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({ @Inject}))
public class MowService {

    private final MowRepository mowRepository;
    private final GrassService grassService;

    public void create(String instruction){

        var mow = MowFactory.build(instruction);
        var grass = grassService.retrieve();

        if(grass.outOfBounds(mow.getPosition().getCoordinates())){
            mowRepository.add(mow);
            mowRepository.setToCurrentMowMowing(mow);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void rotateClockwise() {
        var mow = mowRepository.currentMowMoving();
        mow.rotateClockwise();
    }

    public void rotateCounterclockwise() {
        var mow = mowRepository.currentMowMoving();
        mow.rotateCounterclockwise();
    }

    public void forward() {
        var grass = grassService.retrieve();
        var mow = mowRepository.currentMowMoving();
        mow.move(grass);
    }
}
