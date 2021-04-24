public class Time {
    long timeStamp;

    Time() {
        timeStamp = System.currentTimeMillis();
    }

    public void updateTime() {
        timeStamp = System.currentTimeMillis();
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
