import java.util.List;

public class Client {

    Response response;
    AuthServer defaultServer;
    String username;
    String password;
    Client() {

    }

    Client(String key, List<AuthServer> otherClusters) {
        response = new Response(key, otherClusters);
    }

    public boolean login(String username, String password, AuthServer defaultServer){
        this.defaultServer = defaultServer;
        this.username = username;
        this.password = password;
        return defaultServer.login(username, password);
    }

    public boolean logout() {
        return false;
    }



//    public Response requestContent() {
////        Response request = new Response()
////        return null;
//    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


    public String getUsername() {
        return this.username;
    }
}
