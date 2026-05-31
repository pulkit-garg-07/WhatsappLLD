package src;

public class WebsocketMessageService implements MessageService{
    @Override
    public void sendMessage(User user, Message message) {
        System.out.println("[Message via Websocket] to ["+ user.getId()+"] message: " + message.getContent());
    }
    public void sendDeliveredAck(User user, Message message) {
        System.out.println("[Delivered Ack via Websocket] to ["+ user.getId()+"] for message: " + message.getId());

    }
}
