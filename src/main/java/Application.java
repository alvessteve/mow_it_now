import adapters.InstructionsTranslator;
import adapters.file.FileAdapter;
import adapters.input.InputAdapter;
import adapters.outputs.TerminalOutput;
import adapters.repositories.InMemoryGrassRepository;
import domain.ClientUseCase;
import domain.service.GrassService;

public class Application {

    public static void main(String... args){
        if(args.length == 1){
            initializeFromFile(args[0]);
        } else {
            initializeFromInput(args);
        }
    }

    private static void initializeFromInput(String[] args) {
        //FIXME
        InputAdapter inputAdapter = new InputAdapter(new InstructionsTranslator(), new ClientUseCase(new TerminalOutput(), new GrassService(new InMemoryGrassRepository())));
        inputAdapter.call(args);
    }

    private static void initializeFromFile(String filePath) {
        //FIXME
        FileAdapter fileAdapter = new FileAdapter(new ClientUseCase(new TerminalOutput(), new GrassService(new InMemoryGrassRepository())), new InstructionsTranslator());
        fileAdapter.call(filePath);
    }

}
