package com.example.classroom.publisher;

import com.example.classroom.event.AfterEnterRoomEvent;
import com.example.classroom.model.user.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Custom event publisher for generating events after user successfully enter classroom
 */
@Component
public class AfterEnterRoomEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public AfterEnterRoomEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(final User user) {
        AfterEnterRoomEvent afterEnterRoomEvent = new AfterEnterRoomEvent(this, user);
        applicationEventPublisher.publishEvent(afterEnterRoomEvent);
    }
}
