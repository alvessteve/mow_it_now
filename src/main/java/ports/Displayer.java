package ports;

import domain.model.mow.Mow;

import java.util.Collection;

public interface Displayer {
    void mowsPosition(Collection<Mow> mows);
}
