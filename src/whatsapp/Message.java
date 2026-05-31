package whatsapp;
import java.time.LocalDateTime;

public class Message {

    //Message
    //- id
    //- content: String
    //- chat: Chat
    //- timestamp: LocalDateTime
    //- sender: User
    private String id;
    private String content;
    private Chat chat;
    private LocalDateTime createdAt;
    private User sender;

    public Message(String id, String content, Chat chat, LocalDateTime createdAt, User sender) {
        this.id = id;
        this.content = content;
        this.chat = chat;
        this.createdAt = createdAt;
        this.sender = sender;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Chat getChat() {
        return chat;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getSender() {
        return sender;
    }
}
