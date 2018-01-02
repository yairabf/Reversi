import java.awt.*;

/**
 * class the represent a state in search algorithm
 */
public class State {

    //the string that represent the state
    private ReversiGame state;
    //counters
    private int blackCount , whiteCount, empty;
    //the players
    private char maxPlayer, minPlayer, moveColor;

    /**
     * constructor
     * @param state
     * @param maxPlayer
     * @param minPlayer
     */
    public State(ReversiGame state, char maxPlayer, char minPlayer) {
        this.state = state;
        this.maxPlayer = maxPlayer;
        this.minPlayer = minPlayer;
        this.countColors();
    }

    public State(ReversiGame state, Point place, char maxPlayer, char minPlayer, char colorMove) {
        this.state = state;
        this.maxPlayer = maxPlayer;
        this.minPlayer = minPlayer;
        this.moveColor = colorMove;
        this.state.playerMove(place, colorMove);
        this.countColors();
    }

    /**
     * getter
     * @return the state
     */
    public ReversiGame getState() {
        return state;
    }

    /**
     * getter
     * @return max player
     */
    public char getMaxPlayer() {
        return maxPlayer;
    }

    /**
     * getter
     * @return minPlayer
     */
    public char getMinPlayer() {
        return minPlayer;
    }


    /**
     * getter
     * @return color that just played
     */
    public char getMoveColor() {
        return moveColor;
    }

    /**
     * getter
     * @return the heuristics
     */
    public double getHeuristics() {
        /*if the board is full */
        if(isEnded()){
            if(this.whiteCount > this.blackCount) {
                if(this.maxPlayer == 'W')
                    return Double.POSITIVE_INFINITY;
                else return Double.NEGATIVE_INFINITY;
            }
            else if(this.whiteCount < this.blackCount){
                if(this.maxPlayer == 'B')
                    return Double.POSITIVE_INFINITY;
                else return Double.NEGATIVE_INFINITY;
            }
            else return 0;
        }
        /*counts the number of units of the maxPlayer*/
        else {
            if(this.maxPlayer == 'W'){
                return (whiteCount - blackCount) + this.countEdges(this.maxPlayer);
            }
            else return (this.blackCount - this.whiteCount) + this.countEdges(this.maxPlayer);
        }
    }

    /**
     * counts the units on the edges
     * @param color
     * @return
     */
    private int countEdges(char color) {
        int count = 0;
        for (int i = 0; i < this.state.gameBoard.length; i++) {
            if(this.state.gameBoard[0][i] == color)
                count++;
            if(this.state.gameBoard[this.state.gameBoard.length - 1][i] == color)
                count++;
        }
        for (int i = 1; i < this.state.gameBoard.length - 1; i++) {
            if(this.state.gameBoard[i][0] == color)
                count++;
            if(this.state.gameBoard[0][this.state.gameBoard.length - 1] == color)
                count++;
        }
        return count;
    }

    /**
     * counts the units colors
     */
    private void countColors(){
        for (int i = 0; i < state.gameBoard.length; i++) {
            for (int j = 0; j < state.gameBoard.length; j++) {
                if(state.gameBoard[i][j] == 'B')
                    this.blackCount++;
                else if(state.gameBoard[i][j] == 'W')
                    this.whiteCount++;
                else this.empty++;
            }
        }
    }
    public boolean isEnded(){
        return empty == 0;
    }

}
