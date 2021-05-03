/*
  Created by Linfeng Li on 4/23/2021
  University of Illinois at Chicago
 */

package main;

import java.io.Serializable;

/*
    Time object used to keep track of time
 */
public class Time implements Serializable {
    long timeStamp;

    Time() {
        updateTime();
    }

    // 600,000 is 10 minutes in millisecond
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
