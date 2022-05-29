package com.example.classroom.model.message;

import com.example.classroom.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * POJO for message with user list as field
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMessage extends Message {

    private List<User> users;
}
