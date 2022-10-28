package adapters.file;

import com.google.inject.Inject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import ports.Client;
import utils.StringValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({ @Inject}))
public class FileAdapter {

    private final Client client;

    public void call(String filePath) {
        if(StringValidator.isNullOrEmpty(filePath))
            throw new IllegalArgumentException("A file path must be provided");
        List<String> rawInstructionsList = extractInstructions(filePath);
        client.execute(rawInstructionsList);
    }

    private List<String> extractInstructions(String filePath) {
        Stream<String> linesStream = null;
        try{
            Path path = Paths.get(filePath);
            linesStream = Files.lines(path);
            return linesStream.toList();
        } catch(IOException e){
            throw new MalformedFileException("An error occured when parsing the file :", e);
        } finally {
            if(linesStream != null)
                linesStream.close();
        }
    }
}
