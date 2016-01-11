package de.dieckie.raetsel;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Timer {

    private long start, duration;
    private boolean running = false, paused = false;;

    public Timer() {

    }

    public void start() {
        if(!running) {
            start = System.currentTimeMillis();
            duration = 0;
            running = true;
        } else {
            throw new RuntimeException("Timer is already started");
        }
    }

    public void pause() {
        if(running) {
            if(!paused) {
                duration += System.currentTimeMillis() - start;
                paused = true;
            } else {
                throw new RuntimeException("Timer is already paused");
            }
        } else {
            throw new RuntimeException("Timer is not running");
        }
    }

    public void resume() {
        if(running) {
            if(paused) {
                start = System.currentTimeMillis();
                paused = false;
            } else {
                throw new RuntimeException("Timer is not paused");
            }
        } else {
            throw new RuntimeException("Timer is not running");
        }
    }

    public void stop() {
        if(running) {
            running = false;
            duration += System.currentTimeMillis() - start;
        } else {
            throw new RuntimeException("Timer is already stopped");
        }
    }

    public void reset() {
        duration = 0;
        running = false;
        paused = false;
    }

    public void restart() {
        reset();
        start();
    }

    public long getTime() {
        duration += System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        return duration;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }

    public String formatTime() {
        long h, min, sek, ms;
        Duration d = Duration.of(getTime(), ChronoUnit.MILLIS);
        h = d.toHours();
        d = d.minusHours(h);
        min = d.toMinutes();
        d = d.minusMinutes(min);
        sek = d.getSeconds();
        d = d.minusSeconds(sek);
        ms = d.toMillis();
        return String.format("%02d:%02d:%02d.%03d", h, min, sek, ms);
    }

}
