package ninegroup.ninegame;

/**
 * Created by guilherme on 02/12/15.
 */
public class GameScores {
    private float score;
    private GameTimer t;
    private float normalScoreMultiplier, ethicScoreMultiplier, nonEthicScoreMultiplier;

    public GameScores() {
        score = 0;
        t = new GameTimer();
        normalScoreMultiplier = 1;
        ethicScoreMultiplier = (float) 0.75;
        nonEthicScoreMultiplier = (float) 1.25;
    }
    public float calculateNormalFinalScore(){
        score = t.getElapsedTimeInSeconds() * normalScoreMultiplier;
        return score;
    }
    public float calculateEthicFinalScore(){
        score = t.getElapsedTimeInSeconds() * ethicScoreMultiplier;
        return score;
    }
    public float calculateNonEthicFinalScore(){
        score = t.getElapsedTimeInSeconds() * nonEthicScoreMultiplier;
        return score;
    }
}
