package com.example.classroom.model.message;

import com.example.classroom.model.status.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO for message
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String senderName;
    private String message;
    private Status status;
}
