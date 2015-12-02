package ninegroup.ninegame;

/**
 * Created by guilherme on 02/12/15.
 */
public class GameTimer {
    private final long startNanoTime = System.nanoTime();

    public long getElapsedTimeInNano(){
        return startNanoTime - System.nanoTime();
    }
    public long getElapsedTimeInMilli(){
        long tenRaisedToSix = (long) Math.pow(10,6);
        long startTimeInMilli = startNanoTime / tenRaisedToSix;
        long endTimeInMilli = System.nanoTime() / tenRaisedToSix;
        return startNanoTime  - System.nanoTime();
    }
    public float getElapsedTimeInSeconds(){
        float tenRaisedToNine = (float) Math.pow(10,9);
        float startTimeInMilli = startNanoTime / tenRaisedToNine;
        float endTimeInMilli = System.nanoTime() / tenRaisedToNine;
        return startNanoTime  - System.nanoTime();
    }
}
