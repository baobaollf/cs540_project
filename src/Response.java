import java.util.List;

/**
 * Response object used to transmit information between Auth server and client
 */

public class Response {

    // other available clusters
    List<AuthServer> otherClusters;
    String key;
    String content;
    boolean request;

    public Response(String key, List<AuthServer> otherClusters) {
        this.key = key;
        this.otherClusters = otherClusters;
    }
}
