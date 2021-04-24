package main;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecurityObject implements Serializable {

    private String hashKey;
    private final Time time;
    String clientIP;
    String username;
    String password;
    Body body;
    String serverIP;
    int contentSerialID;
    // port
    List<Integer> availableAuthServers;


    SecurityObject(String username, String password, String address) {
        this.time = new Time();
        hashKey = String.valueOf(time.timeStamp + username.hashCode() + password.hashCode());
        this.username = username;
        this.password = password;
        this.body = new Body();
        this.serverIP = "";
        this.clientIP = address;
        this.availableAuthServers = new ArrayList<>();
        this.contentSerialID = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public void updateTime() {
        time.updateTime();
    }

    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "SecurityObject{" +
                "hashKey='" + hashKey + '\'' +
                ", time=" + time +
                ", clientIP='" + clientIP + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
