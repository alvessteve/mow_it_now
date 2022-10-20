package adapters.file;

import adapters.InstructionsTranslator;
import domain.model.instructions.Instruction;
import domain.ClientUseCase;
import utils.StringValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public record FileAdapter(ClientUseCase client, InstructionsTranslator inputTranslator) {
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
