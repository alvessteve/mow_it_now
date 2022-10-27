package ports;

import domain.model.mow.Mow;

import java.util.List;

public interface MowRepository {
    void add(Mow mow);

    void setToCurrentMowMowing(Mow mow);

    Mow currentMowMoving();

    List<Mow> all();
}
