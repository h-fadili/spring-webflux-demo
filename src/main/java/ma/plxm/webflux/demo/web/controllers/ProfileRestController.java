package ma.plxm.webflux.demo.web.controllers;

import lombok.extern.log4j.Log4j2;
import ma.plxm.webflux.demo.entities.Profile;
import ma.plxm.webflux.demo.services.ProfileService;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

// a spring MVC alike approach to reactive endpoints
@RestController
@RequestMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@org.springframework.context.annotation.Profile("classic")
@Log4j2
public class ProfileRestController {
    private final MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
    private final ProfileService profileRepository;

    ProfileRestController(ProfileService profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping
    Publisher<Profile> getAll() {
        log.info("in get all classic controller");
        return this.profileRepository.all();
    }

    @GetMapping("/{id}")
    Publisher<Profile> getById(
        @PathVariable("id")
            String id) {
        return this.profileRepository.get(id);
    }

    @PostMapping
    Publisher<ResponseEntity<Profile>> create(
        @RequestBody
            Profile profile) {
        return this.profileRepository.create(profile.getEmail()).map(
            p -> ResponseEntity.created(URI.create("/profiles/" + p.getId()))
                .contentType(mediaType).build());
    }

    @DeleteMapping("/{id}")
    Publisher<Profile> deleteById(
        @PathVariable
            String id) {
        return this.profileRepository.delete(id);
    }

    @PutMapping("/{id}")
    Publisher<ResponseEntity<Profile>> updateById(
        @PathVariable
            String id,
        @RequestBody
            Profile profile) {
        return Mono.just(profile)
            .flatMap(p -> this.profileRepository.update(id, p.getEmail()))
            .map(p -> ResponseEntity.ok().contentType(this.mediaType).build());
    }
}