import java.awt.*;

/**
 * class the represent a state in search algorithm
 */
public class State {

    //the string that represent the state
    private ReversiGame state;
    private int blackCount , whiteCount, empty;
    // the father that create this state
    private State _cameFrom;

    public ReversiGame getState() {
        return state;
    }

    public char getMaxPlayer() {
        return maxPlayer;
    }

    public char getMinPlayer() {
        return minPlayer;
    }

    private char maxPlayer, minPlayer;

    public State(ReversiGame state, char maxPlayer, char minPlayer) {
        this.state = state;
        this.maxPlayer = maxPlayer;
        this.minPlayer = minPlayer;
    }

    public State(ReversiGame state, State _cameFrom, Point place, char maxPlayer, char minPlayer) {
        this.state = state;
        this._cameFrom = _cameFrom;
        this.maxPlayer = maxPlayer;
        this.minPlayer = minPlayer;
        this.state.playerMove(place, maxPlayer);
        this.countColors();
    }

    /*
     * setter
     * @param s the father of the state
     */
    public void setCameFrom(State s)
    {
        this._cameFrom = s;
    }
    /**
     * getter
     * @return the heuristics
     */
    public double getHeuristics() {
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
        else {
            if(this.maxPlayer == 'W'){
                return (whiteCount - blackCount) + this.countEdges(this.maxPlayer);
            }
            else return (this.blackCount - this.whiteCount) + this.countEdges(this.maxPlayer);
        }
    }

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
     * getter
     * @return cameFrom
     */
    public State getCameFrom()
    {
        return this._cameFrom;
    }

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
