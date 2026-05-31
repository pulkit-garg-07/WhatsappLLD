package src;

public class PushNotificationMessageService implements MessageService {
    @Override
    public void sendMessage(User user, Message message) {
        System.out.println("[Push Notification] to [" + user.getId() + "] message: " + message.getContent());
    }
}
