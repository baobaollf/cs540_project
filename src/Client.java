import java.io.*;
import java.net.Socket;

public class Client {

    private Response response;
    private AuthServer defaultServer;
    private String username;
    private String password;
    private String line = "";
    // initialize socket and input output streams
    private Socket socketClient;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    Client(String address, int port) {
        try {
            socketClient = new Socket(address, port);
            in = new ObjectInputStream(socketClient.getInputStream());
            out = new ObjectOutputStream(socketClient.getOutputStream());
            System.out.println("Connected to server");
        }
        catch (Exception e) {
            System.out.println("Error!" + e);
        }
        while(!line.equals("quit")){

            try{
                System.out.println("client waiting!");
                response = (Response)in.readObject();
                System.out.println("received: " + response.key);
            }
            catch(Exception e){
                System.out.println("Error!" + e);
                break;

            }
        }
        try {
            socketClient.close();
            in.close();
            out.close();
        }
        catch (Exception e){
            System.out.println("Error!" + e);
        }


    }

    public void send(Response request){
        try {
            out.writeObject(request);
        }
        catch (Exception e){
            System.out.println("Error!" + e);
        }
    }







//    Client(String key, List<AuthServer> otherClusters) {
//        response = new Response(key, otherClusters);
//    }

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
