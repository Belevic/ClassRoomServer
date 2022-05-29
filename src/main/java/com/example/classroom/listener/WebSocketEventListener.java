package com.example.classroom.listener;


import com.example.classroom.event.ChangeHandEvent;
import com.example.classroom.event.AfterEnterRoomEvent;
import com.example.classroom.event.ErrorExistingUserEvent;
import com.example.classroom.model.user.User;
import com.example.classroom.service.socket.WebSocketService;
import com.example.classroom.util.Constants;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Component that have several event listener methods for listening some application event and trigger actions
 */
@Component
public class WebSocketEventListener {

    private final WebSocketService webSocketService;

    /**
     * List of subscribed users
     */
    private final List<User> subscribedUsers = new CopyOnWriteArrayList<>();

    public WebSocketEventListener(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    /**
     * Method for listening event of after entering room that will add user into list and call methods for sending enter message
     * and update list message
     * @param event
     */
    @EventListener
    public void handleAfterEnterRoomListener(AfterEnterRoomEvent event) {
        subscribedUsers.add(event.getUser());

        webSocketService.sendEnterMessage(event.getUser().getName());
        webSocketService.sendUpdateUsersMessage(subscribedUsers);
    }

    /**
     * Method for listening event of raising hand that will change hand position of user and call methods for sending raise hand message
     * and update list message
     * @param event
     */
    @EventListener
    public void handleChangeHandListener(ChangeHandEvent event) {
        User user = subscribedUsers.stream().filter(u -> u.getName().equals(event.getName())).findFirst().get();
        user.setHandUp(!user.isHandUp());

        webSocketService.sendRaiseHandMessage(user);
        webSocketService.sendUpdateUsersMessage(subscribedUsers);
    }

    /**
     * Method for listening event of try enter in room existing user that will trigger error and call method for sending error message
     * @param event
     */
    @EventListener
    public void handleErrorExistingUserListener(ErrorExistingUserEvent event) {
        webSocketService.sendErrorMessage(event.getName());
    }

    /**
     * Method for listening event of disconnecting user from websocket that will remove user from list storage and send
     * leave and update list messages
     * @param event
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userName = (String) headerAccessor.getSessionAttributes().get(Constants.USERNAME);

        if(Objects.nonNull(userName)) {
            subscribedUsers.removeIf(user -> user.getName().equals(userName));

            webSocketService.sendLeaveMessage(userName);
            webSocketService.sendUpdateUsersMessage(subscribedUsers);
        }
    }

}
