package adapters.file;

import adapters.InstructionsTranslator;
import com.google.inject.Inject;
import domain.model.instructions.Instruction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import ports.Client;
import utils.StringValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PUBLIC, onConstructor = @__({ @Inject}))
public class FileAdapter {

    private final Client client;
    private final InstructionsTranslator inputTranslator;

    public void call(String filePath) {
        if(StringValidator.isNullOrEmpty(filePath))
            throw new IllegalArgumentException("A file path must be provided");
        List<String> rawInstructionsList = extractInstructions(filePath);
        Collection<Instruction> instructions = inputTranslator.translateToInstruction(rawInstructionsList);
        client.execute(instructions);
    }

    private List<String> extractInstructions(String filePath) {
        Stream<String> linesStream = null;
        try{
            Path path = Paths.get(filePath);
            linesStream = Files.lines(path);
            return linesStream.toList();
        } catch(IOException e){
            throw new IllegalStateException("An error occured when parsing the file", e);
        } finally {
            if(linesStream != null)
                linesStream.close();
        }
    }
}
