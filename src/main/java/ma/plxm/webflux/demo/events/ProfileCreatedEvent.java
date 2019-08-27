package ma.plxm.webflux.demo.events;

import ma.plxm.webflux.demo.entities.Profile;
import org.springframework.context.ApplicationEvent;

public class ProfileCreatedEvent extends ApplicationEvent {
    public ProfileCreatedEvent(Profile source) {
        super(source);
    }
}
