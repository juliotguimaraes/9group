package ninegroup.ninegame;

public class Model {

    private int currentPosition;
    private int boardSize;

    public Model(int boardSize) {
        this.boardSize = boardSize;
        currentPosition = 0;
    }

    public void advance() {
        if(currentPosition < boardSize - 1) {
            currentPosition++;
        }
    }

    public void retrocede() {
        if(currentPosition > 0) {
            currentPosition--;
        }
    }

    public void restart() {
        currentPosition = 0;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
