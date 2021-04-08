import java.util.List;

public class Response {

    // other available clusters
    List<AuthServer> otherClusters;


    public Response(List<AuthServer> otherClusters) {
        this.otherClusters = otherClusters;
    }
}
