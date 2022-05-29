package com.example.classroom.service.socket;

import com.example.classroom.model.message.Message;
import com.example.classroom.model.status.Status;
import com.example.classroom.model.user.User;
import com.example.classroom.model.message.UpdateMessage;
import com.example.classroom.service.user.UserService;
import com.example.classroom.util.Constants;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for sending messages to subscribe users after some events triggering
 */
@Service
public class WebSocketService {

    /**
     * Message sender
     */
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserService userService;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate, UserService userService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.userService = userService;
    }

    /**
     * Method for sending message that will notify all subscribers that new user enter classroom
     * @param userName
     */
    public void sendEnterMessage(String userName) {
        Message message = new Message();
        message.setStatus(Status.ENTER);
        message.setMessage(userName.concat(Constants.ENTER_CLASSROOM_MSG_TEXT));
        message.setSenderName(userName);

        simpMessagingTemplate.convertAndSend(Constants.CLASSROOM_DESTINATION, message);
    }

    /**
     * Method for sending message that will notify all subscribers that user leave classroom
     * @param userName
     */
    public void sendLeaveMessage(String userName) {
        userService.deleteUserByName(userName);

        Message message = new Message();
        message.setStatus(Status.LEAVE);
        message.setMessage(userName.concat(Constants.LEAVE_CLASSROOM_MSG_TEXT));
        message.setSenderName(userName);

        simpMessagingTemplate.convertAndSend(Constants.CLASSROOM_DESTINATION, message);
    }

    /**
     * Method for sending message that will notify all subscribers that user raise his hand up or down
     * @param user
     */
    public void sendRaiseHandMessage(User user) {
        Message message = new Message();
        message.setStatus(Status.MESSAGE);
        message.setMessage(user.isHandUp() ? user.getName().concat(Constants.RAISE_HAND_UP_MSG_TEXT) :
                user.getName().concat(Constants.RAISE_HAND_DOWN_MSG_TEXT) );
        message.setSenderName(user.getName());

        simpMessagingTemplate.convertAndSend(Constants.CLASSROOM_DESTINATION, message);
    }

    /**
     * Method for sending message that will send error notification
     * @param userName
     */
    public void sendErrorMessage(String userName) {
        Message message = new Message();
        message.setStatus(Status.ERROR);
        message.setMessage(Constants.USER.concat(userName).concat(Constants.ERROR_MSG_TEXT));

        simpMessagingTemplate.convertAndSend(Constants.CLASSROOM_DESTINATION, message);
    }

    /**
     * Method for sending message that will update currently participating in classroom users
     * @param subscribedUsers
     */
    public void sendUpdateUsersMessage(List<User> subscribedUsers) {
        UpdateMessage updateMessage = new UpdateMessage();
        updateMessage.setStatus(Status.UPDATE);
        updateMessage.setUsers(subscribedUsers);

        simpMessagingTemplate.convertAndSend(Constants.CLASSROOM_DESTINATION, updateMessage);
    }
}
