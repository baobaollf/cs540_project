package main;
import java.io.Serializable;

public class Time implements Serializable {
    long timeStamp;

    Time() {
        updateTime();
    }

    public void updateTime() {
        timeStamp = System.currentTimeMillis() + 600000;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "Time{" +
                "timeStamp=" + timeStamp +
                '}';
    }
}
