package ma.plxm.webflux.demo;

import lombok.extern.log4j.Log4j2;
import ma.plxm.webflux.demo.entities.Profile;
import ma.plxm.webflux.demo.repositories.ProfileRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Log4j2
@Component
@org.springframework.context.annotation.Profile("demo")
public class SampleDataInitializer
    implements ApplicationListener<ApplicationReadyEvent> {

    private final ProfileRepository repository;

    public SampleDataInitializer(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        repository.deleteAll().thenMany(Flux.just("A", "B", "C", "D").map(
            prefix -> new Profile(UUID.randomUUID().toString(),
                prefix + "@email.com")).flatMap(repository::save))
            .thenMany(repository.findAll())
            .subscribe(p -> log.info("Saving {}", p));
    }
}
