package domain.service;

import com.google.inject.Inject;
import domain.factory.GrassFactory;
import domain.model.Grass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import ports.GrassRepository;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__({ @Inject}))
public class GrassService {

    private final GrassRepository grassRepository;

    public void create(String coordinates) {
        var grass = GrassFactory.build(coordinates);
        grassRepository.save(grass);
    }

    public Grass retrieve(){
        return grassRepository.fetch();
    }
}
