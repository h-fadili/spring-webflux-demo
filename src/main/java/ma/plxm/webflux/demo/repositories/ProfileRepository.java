package ma.plxm.webflux.demo.repositories;

import ma.plxm.webflux.demo.entities.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProfileRepository
    extends ReactiveMongoRepository<Profile, String> {

}
