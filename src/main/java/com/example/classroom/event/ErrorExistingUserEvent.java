package com.example.classroom.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Custom event that will be triggered when we try to enter but user with such nme is already in classroom
 */
@Getter
public class ErrorExistingUserEvent extends ApplicationEvent {

    private final String name;

    public ErrorExistingUserEvent(Object source, String name) {
        super(source);
        this.name = name;
    }
}
