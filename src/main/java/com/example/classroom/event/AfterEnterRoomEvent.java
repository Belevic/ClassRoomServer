package com.example.classroom.event;

import com.example.classroom.model.user.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Custom event that will be triggered after user enter classroom
 */
@Getter
public class AfterEnterRoomEvent extends ApplicationEvent {

    private final User user;

    public AfterEnterRoomEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
