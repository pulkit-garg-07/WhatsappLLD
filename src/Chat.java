package src;

import java.util.List;

public class Chat {
    //Chat
    //- id
    //- participants: User[]
    private String id;
    private List<User> participants;
    public Chat(String id, List<User> participants) {
        this.id = id;
        this.participants = participants;
    }

    public String getId() {
        return id;
    }
    public List<User> getParticipants() {
        return participants;
    }
    public void addParticipant(User participant) {
        this.participants.add(participant);
    }
}
