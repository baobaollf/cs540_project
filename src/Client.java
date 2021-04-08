import java.util.List;

public class Client {
    SecurityObject securityObject;

    Response response;
    String key;

    Client(String key, List<AuthServer> otherClusters) {
        response = new Response(otherClusters);
        this.key = key;

    }
}
