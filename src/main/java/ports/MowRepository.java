package ports;

import domain.model.mow.Mow;

import java.util.Collection;

public interface MowRepository {
    void add(Mow mow);

    void setToCurrentMowMowing(Mow mow);

    Mow currentMowMoving();
}
