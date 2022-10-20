package domain.model.instructions;

import lombok.NoArgsConstructor;
import utils.StringValidator;

@NoArgsConstructor
public abstract class Instruction {

    protected String content;

    public Instruction(String content) {
        if(StringValidator.isNullOrEmpty(content))
            throw new IllegalArgumentException("A content must be provided to initialize correctly an instruction");
        this.content = content;
    }

    public abstract void execute();
}
