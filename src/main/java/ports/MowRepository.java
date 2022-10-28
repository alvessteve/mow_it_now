package ports;

import domain.model.mow.Mow;

import java.util.List;
import java.util.Optional;

public interface MowRepository {
    void add(Mow mow);

    void setToCurrentMowMowing(Mow mow);

    Optional<Mow> currentMowMoving();

    List<Mow> all();
}
