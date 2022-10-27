package domain;

import com.google.inject.Inject;
import domain.model.instructions.Instruction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import ports.Client;
import ports.Displayer;
import ports.MowRepository;

import java.util.Collection;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({ @Inject}))
public class ClientUseCase implements Client {

    private final Displayer displayer;
    private final MowRepository mowRepository;

    @Override
    public void execute(Collection<Instruction> instructions) {
        instructions.forEach(Instruction::execute);
        displayer.mowsPosition(mowRepository.all());
    }
}
