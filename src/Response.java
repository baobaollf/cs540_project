import java.util.List;

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
