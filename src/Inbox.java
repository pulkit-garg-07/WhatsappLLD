package src;

import java.util.ArrayList;
import java.util.List;

public class Inbox {
    //Inbox
    //- User
    //- messages: Message[]

    private final User user;
    private List<Message> pendingMessages;

    public Inbox(User user) {
        this.user = user;
        this.pendingMessages = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public List<Message> getPendingMessages() {
        return pendingMessages;
    }
    public void addPendingMessage(Message message) {
        this.pendingMessages.add(message);
    }
    public List<Message> flushPendingMessages() {
        List<Message> messages = new ArrayList<>(this.pendingMessages);
        this.pendingMessages = new ArrayList<>();
        return messages;
    }
    public void removePendingMessage(Message message){ this.pendingMessages.remove(message);}
}
