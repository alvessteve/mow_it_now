package ports;

import domain.model.Grass;

public interface GrassRepository {
    Grass fetch();

    void save(Grass grass);
}
