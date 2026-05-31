package src;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatServer {
    //ChatService
    //- chats: Chat[]
    //- users: User[]
    //- inboxes: Inbox[]
    //- discoveryService: DiscoveryService
    //- messageServices: MessageService[]
    //+ createChat(participants: User[]) -> Chat
    //+ sendMessage(chatId, content: String, sender: User) -> Message
    //+ getPendingMessages(user: User) -> Message[]

    int chatIndex=0;
    int messageIndex=0;
    private final List<Inbox> inboxes;
    private final DiscoveryService discoveryService;
    private final WebsocketMessageService websocketMessageService = new WebsocketMessageService();
    private final PushNotificationMessageService pushNotificationMessageService = new PushNotificationMessageService();

    public ChatServer() {
        this.inboxes = new ArrayList<>();
        this.discoveryService = new DiscoveryService();
    }


    public Chat createChat(List<User> participants) {
        return new Chat(String.valueOf(++chatIndex),participants);

    }
    public void sendMessage(Chat chat, String content, User sender) {
        // get the chat
        // get the participants to snd the message to
        // for each user:
        // add the message to each of the participants inbox
        // check with discovery service if user is online
        // if user is connected send message, remove from the inbox
        // if user is disconnected, send push notification

        Message newMessage = new Message(String.valueOf(++messageIndex), content, chat, LocalDateTime.now(), sender);
        List<User>participants = chat.getParticipants();
        for(User participant: participants){
            if(participant.equals(sender))
                continue;
            handleMessageSendForParticipant(participant, newMessage);
        }

    }
    private void handleMessageSendForParticipant(User participant, Message message) {
        Inbox inbox = getOrCreateInbox(participant);
        inbox.addPendingMessage(message);
        if(discoveryService.isUserConnected(participant)){
            // send message
            websocketMessageService.sendMessage(participant, message);
            // remove message from inbox
            inbox.removePendingMessage(message);
            // send delivered ack to sender
            websocketMessageService.sendDeliveredAck(message.getSender(), message);
        }
        else {
            pushNotificationMessageService.sendMessage(participant, message);
            // send push notification
        }

    }
    public List<Message> getPendingMessages(User user){
        // find the inbox for the user
        // flush the inbox
        // return the pending messages
        Inbox inbox = this.getOrCreateInbox(user);
        List<Message> pendingMessages = new ArrayList<>(inbox.getPendingMessages());
        inbox.flushPendingMessages();
        return pendingMessages;

    }
    private Inbox getOrCreateInbox(User user){
        return this.inboxes.stream().filter(i -> i.getUser().equals(user)).findFirst().orElseGet(()->{
            Inbox newInbox = new Inbox(user);
            this.inboxes.add(newInbox);
            return newInbox;
        });
    }

    public WebsocketMessageService getWebsocketMessageService() {
        return websocketMessageService;
    }
    public DiscoveryService getDiscoveryService() {
        return discoveryService;
    }
    public PushNotificationMessageService getPushNotificationMessageService() {
        return pushNotificationMessageService;
    }


}
