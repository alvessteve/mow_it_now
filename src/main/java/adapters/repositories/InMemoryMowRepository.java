package adapters.repositories;


import domain.model.mow.Mow;
import lombok.Data;
import ports.MowRepository;

import java.util.HashSet;
import java.util.Set;

@Data
public class InMemoryMowRepository implements MowRepository {

    private Set<Mow> mowList = new HashSet<>();
    private Mow currentMowMoving;

    @Override
    public void add(Mow mow) {
        mowList.add(mow);
    }

    @Override
    public void setToCurrentMowMowing(Mow mow) {
        currentMowMoving = mow;
    }

    @Override
    public Mow currentMowMoving() {
        Mow mow = currentMowMoving;
        if(mow == null)
            throw new IllegalStateException("No mow is moving");
        return mow;
    }

}
