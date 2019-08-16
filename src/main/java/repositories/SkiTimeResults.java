package repositories;

import java.util.Objects;

public class SkiTimeResults {
    int minutes;
    int seconds;

    public SkiTimeResults() {

    }


    public SkiTimeResults(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void addTime(long secondsToAdd) {

        for (int i = 0; i < (int) secondsToAdd; i++) {
            seconds++;
            if (seconds >= 60) {
                minutes = minutes + 1;
                seconds = 0;
            }
        }
    }

    public int totalTime() {
        int totalTime = (minutes * 60) + seconds;
        return totalTime;
    }

    public String getTotalTime() {
        return minutes + ":" + seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkiTimeResults that = (SkiTimeResults) o;
        return minutes == that.minutes &&
                seconds == that.seconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minutes, seconds);
    }
}
