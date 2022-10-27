package infrastructure.dependencyinversion;

import adapters.InstructionsTranslator;
import adapters.file.FileAdapter;
import adapters.input.InputAdapter;
import adapters.outputs.TerminalOutput;
import adapters.repositories.InMemoryGrassRepository;
import adapters.repositories.InMemoryMowRepository;
import com.google.inject.AbstractModule;
import domain.ClientUseCase;
import domain.factory.InstructionFactory;
import domain.factory.MoveMowInstructionFactory;
import domain.service.GrassService;
import domain.service.MowService;
import ports.Client;
import ports.Displayer;
import ports.GrassRepository;
import ports.MowRepository;

import static com.google.inject.Scopes.SINGLETON;

public class MowitnowModule extends AbstractModule {

    @Override
    protected void configure() {
        binder().requireExplicitBindings();

        bind(MowRepository.class).to(InMemoryMowRepository.class).in(SINGLETON);
        bind(GrassRepository.class).to(InMemoryGrassRepository.class).in(SINGLETON);
        bind(Displayer.class).to(TerminalOutput.class);
        bind(Client.class).to(ClientUseCase.class);

        bind(GrassService.class).in(SINGLETON);
        bind(MowService.class).in(SINGLETON);
        bind(FileAdapter.class).in(SINGLETON);
        bind(InputAdapter.class).in(SINGLETON);
        bind(InstructionsTranslator.class).in(SINGLETON);
        bind(InstructionFactory.class).in(SINGLETON);
        bind(MoveMowInstructionFactory.class).in(SINGLETON);

    }
}
