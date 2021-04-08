import java.util.Set;

public class AuthServer extends Server{

    Set<SecurityObject> authorizedObjects;

    AuthServer(String IP) {
        super(IP);
    }
}
