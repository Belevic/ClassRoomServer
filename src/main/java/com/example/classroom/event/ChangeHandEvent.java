package com.example.classroom.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Custom event that will be triggered when someone in classroom raise his hand up/down
 */
@Getter
public class ChangeHandEvent extends ApplicationEvent {

    private final String name;

    public ChangeHandEvent(Object source, String name) {
        super(source);
        this.name = name;
    }
}
