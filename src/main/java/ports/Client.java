package ports;

import java.util.Collection;

public interface Client {
    void execute(Collection<String> instructions);
}
