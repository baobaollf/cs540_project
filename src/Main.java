import com.sun.javaws.IconUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {
    AuthServer authServer1;
    AuthServer authServer2;

    Client client;

    public static void main(String[] args) {
        Main main = new Main();

        main.authServer1 = new AuthServer("0.0.0.0");
        main.authServer2 = new AuthServer("0.0.0.1");

        List<ContentServer> contentServerList = new ArrayList<>();
        contentServerList.add(new ContentServer("0.0.0.2"));

        List<ContentServer> contentServerList2 = new ArrayList<>();
        contentServerList.add(new ContentServer("0.0.0.3"));

        main.authServer1.setContentServers(contentServerList);
        main.authServer2.setContentServers(contentServerList2);

        main.authServer1.setUsers("user1", "password");



        main.client = new Client();

        if (main.client.login("user1", "password", main.authServer1)) {
            System.out.println( main.client.getUsername() + " login success");
        }


    }

}
