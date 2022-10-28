package ports;

import domain.model.Grass;

import java.util.Optional;

public interface GrassRepository {
    Optional<Grass> fetch();

    void save(Grass grass);
}
