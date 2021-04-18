import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

public class Client {

    private Response response;
    private AuthServer defaultServer;
    private String username;
    private String password;
    private String line = "";
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to server");
        }
        catch (Exception e) {
            System.out.println("Error!" + e);
        }
        while(!line.equals("quit")){

        }



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

    public void sendRequest(String request){
        System.out.println("sending request for " + request);
    }
}
