public class SecurityObject {

    private String hashKey;
    private final Time time;
    private String clientIP;

    SecurityObject(String username, String password) {
        this.time = new Time();
        hashKey = String.valueOf(time.timeStamp + clientIP.hashCode() + username.hashCode() + password.hashCode());
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

    public Time getTime(){
        return time;
    }
}
