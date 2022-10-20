package ports;

import domain.model.instructions.Instruction;

import java.util.Collection;

public interface Client {
    void execute(Collection<Instruction> instructions);
}
