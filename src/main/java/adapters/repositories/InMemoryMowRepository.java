package adapters.repositories;


import domain.model.mow.Mow;
import lombok.NoArgsConstructor;
import ports.MowRepository;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class InMemoryMowRepository implements MowRepository {

    private List<Mow> mowList = new ArrayList<>();
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

    @Override
    public List<Mow> all() {
        return mowList;
    }

}
