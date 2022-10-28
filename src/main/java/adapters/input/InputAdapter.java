package adapters.input;

import com.google.inject.Inject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import ports.Client;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({ @Inject}))
public class  InputAdapter {

    private final Client client;

    public void call(String[] rawInstructions) {
        if(rawInstructions.length == 0)
            throw new IllegalArgumentException("no instructions have been sent");

        List<String> rawInstructionsList = Arrays.stream(rawInstructions).toList();
        client.execute(rawInstructionsList);
    }
}
