package com.example.classroom.api;

import com.example.classroom.publisher.ChangeHandEventPublisher;
import com.example.classroom.publisher.AfterEnterRoomEventPublisher;
import com.example.classroom.model.message.Message;
import com.example.classroom.model.user.User;
import com.example.classroom.publisher.ErrorExistingUserEventPublisher;
import com.example.classroom.service.user.UserService;
import com.example.classroom.util.Constants;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * Api for receiving request for message sending
 */
@Controller
public class ClassRoomController {

    private final UserService userService;

    /**
     * All event publisher that are used in this class are autowired here
     */
    private final AfterEnterRoomEventPublisher afterEnterRoomEventPublisher;
    private final ChangeHandEventPublisher changeHandEventPublisher;
    private final ErrorExistingUserEventPublisher errorExistingUserEventPublisher;

    public ClassRoomController(UserService userService, AfterEnterRoomEventPublisher afterEnterRoomEventPublisher,
                               ChangeHandEventPublisher changeHandEventPublisher, ErrorExistingUserEventPublisher errorExistingUserEventPublisher) {
        this.userService = userService;
        this.afterEnterRoomEventPublisher = afterEnterRoomEventPublisher;
        this.changeHandEventPublisher = changeHandEventPublisher;
        this.errorExistingUserEventPublisher = errorExistingUserEventPublisher;
    }

    /**
     * Method for receiving messages for raising hand and publishing event for updating user list on front
     * @param message - message send from front with sender name as field
     */
    @MessageMapping("/message")
    public void raiseHand(@Payload Message message){
        changeHandEventPublisher.publishEvent(message.getSenderName());
    }

    /**
     * Method for receiving request for login users in classroom that check if user with such name already in database
     * and if not creating new one and publishing event for send message tht says that new user log in and updating full user list
     * else message with error text will be broadcast to all socket subscriber. Also, session attribute is setting for user
     * tht will be used when user will leave classroom.
     * @param message
     * @param headerAccessor
     */
    @MessageMapping("/enter")
    public void enterClassRoom(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put(Constants.USERNAME, message.getSenderName());
        Optional<User> user = userService.getUser(message.getSenderName());

        if (user.isEmpty()) {
            errorExistingUserEventPublisher.publishEvent(message.getSenderName());
        } else {
            afterEnterRoomEventPublisher.publishEvent(user.get());
        }
    }
}