package com.example.classroom.publisher;

import com.example.classroom.event.ErrorExistingUserEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Custom event publisher for generating events when try to enter user that already exists in classroom
 */
@Component
public class ErrorExistingUserEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ErrorExistingUserEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(final String name) {
        ErrorExistingUserEvent errorExistingUserEvent = new ErrorExistingUserEvent(this, name);
        applicationEventPublisher.publishEvent(errorExistingUserEvent);
    }
}
