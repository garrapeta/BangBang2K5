package game;

import java.util.*;

import sound.audioManagers.AudioManager;
import util.Constants;

/**
 * @author Sergei Towers
 *  
 */
public class SecondsTimer extends Timer {
    public final static int DEFAULT_TURN_TIME = 10;

    private int time;

    private TimerTask turnTimerTask = new TurnTimerTask();

    private boolean running;

    private GameInterface game;

    public SecondsTimer(GameInterface game) {
        this.game = game;
        running = false;
        this.schedule(turnTimerTask, 0, (long) 1000);
    }

    public void reset() {
        reset(DEFAULT_TURN_TIME);
    }

    public void reset(int t) {
        time = t;
        running = true;
    }

    public void pause() {
        running = false;
    }

    public void resume() {
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public int getTime() {
        return time;
    }

    private class TurnTimerTask extends TimerTask {
        public void run() {
            if (running) {
                time--;

                if (time < 0) {
                    running = false;                    
                }

                if (time < 4 && time > -1 && game.getTurnState() == Constants.PLAYING) {
                    AudioManager.getStaticAm().play("timeBeep", false);
                }
            }
        }
    }//class TurnTimerTask
    
}