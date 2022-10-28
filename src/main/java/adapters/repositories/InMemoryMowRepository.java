package adapters.repositories;


import domain.model.mow.Mow;
import lombok.NoArgsConstructor;
import ports.MowRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@NoArgsConstructor
public class InMemoryMowRepository implements MowRepository {

    private final List<Mow> mowList = new ArrayList<>();
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
    public Optional<Mow> currentMowMoving() {
        return ofNullable(currentMowMoving);
    }

    @Override
    public List<Mow> all() {
        return mowList;
    }

}
