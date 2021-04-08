import java.util.List;

public class SecurityObject {

    private String hashKey;
    private final Time time;
    private String IP;

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public void updateTime() {
        time.updateTime();
    }



    List<ContentServer> contentServers;

    SecurityObject() {
        this.time = new Time();
        hashKey = String.valueOf(time.timeStamp.hashCode() + IP.hashCode());
    }
}
