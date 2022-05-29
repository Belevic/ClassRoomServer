package com.example.classroom.publisher;

import com.example.classroom.event.ChangeHandEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Custom spring publisher for generating events when user raise hand up or down
 */
@Component
public class ChangeHandEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ChangeHandEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(final String name) {
        ChangeHandEvent changeHandEvent = new ChangeHandEvent(this, name);
        applicationEventPublisher.publishEvent(changeHandEvent);
    }
}
