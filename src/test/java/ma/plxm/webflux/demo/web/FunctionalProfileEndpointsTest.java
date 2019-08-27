package ma.plxm.webflux.demo.web;

import lombok.extern.log4j.Log4j2;
import ma.plxm.webflux.demo.services.ProfileService;
import ma.plxm.webflux.demo.web.config.ProfileEndpointConfiguration;
import ma.plxm.webflux.demo.web.handlers.ProfileHandler;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@Log4j2
@ActiveProfiles("default")
@Import( { ProfileEndpointConfiguration.class, ProfileHandler.class,
             ProfileService.class })
public class FunctionalProfileEndpointsTest
    extends AbstractBaseProfileEndpoints {

    @BeforeAll
    static void before() {
        log.info("running non-classic tests");
    }

    FunctionalProfileEndpointsTest(
        @Autowired
            WebTestClient client) {
        super(client);
    }
}
