import adapters.file.FileAdapter;
import adapters.input.InputAdapter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import infrastructure.dependencyinversion.MowitnowModule;

public class Application {

    public static void main(String... args){
        if(args.length == 1){
            initializeFromFile(args[0]);
        } else {
            initializeFromInput(args);
        }
    }

    private static void initializeFromInput(String[] args) {
        Injector injector = Guice.createInjector(new MowitnowModule());
        InputAdapter inputAdapter = injector.getInstance(InputAdapter.class);
        inputAdapter.call(args);
    }

    private static void initializeFromFile(String filePath) {
        Injector injector = Guice.createInjector(new MowitnowModule());
        FileAdapter fileAdapter = injector.getInstance(FileAdapter.class);
        fileAdapter.call(filePath);
    }

}
