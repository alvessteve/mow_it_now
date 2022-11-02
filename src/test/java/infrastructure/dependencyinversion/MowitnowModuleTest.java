package infrastructure.dependencyinversion;

import com.google.inject.Guice;
import com.google.inject.Injector;
import domain.service.GrassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MowitnowModuleTest {

    private Injector injector;

    @BeforeEach
    public void setUp() {
         injector = Guice.createInjector(new MowitnowModule());
    }

    @Test
    public void testCanInjectMyThing() {
        assertThat(injector.getInstance(GrassService.class)).isNotNull();
    }
}