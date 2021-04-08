public class Time {
    String timeStamp;

    Time() {
        timeStamp = String.valueOf(System.currentTimeMillis());
    }

    public void updateTime() {
        timeStamp = String.valueOf(System.currentTimeMillis());
    }

}
