package whatsapp;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryService {
    //DiscoveryService
    //- connectedUsers: User[]
    //+ connectUser(user: User) -> void
    //+ disconnectUser(user: User) -> void

    private List<User> connectedUsers;

    public DiscoveryService() {
        this.connectedUsers = new ArrayList<>();
    }

    public void connectUser(User user) {
        connectedUsers.add(user);
    }
    public void disconnectUser(User user){
        connectedUsers.remove(user);
    }
    public boolean isUserConnected(User user){
        return connectedUsers.contains(user);
    }
}
