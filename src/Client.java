import java.util.List;

public class Client {

    Response response;


    Client() {

    }

    Client(String key, List<AuthServer> otherClusters) {
        response = new Response(key, otherClusters);

    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }




}
