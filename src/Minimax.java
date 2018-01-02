import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Minimax {
    private char maxPlayer;

    public Minimax(char maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    /**
     * the search function uses the "minimax" algorithm to find the best next state to move to.
     * @param current the current state of the game
     * @param depth the depth of states we allow to evaluated
     * @param maxPlayer the current player
     * @return the next best state to move to.
     */
    public State search(State current, int depth, char maxPlayer){
        //if the board is full or we evaluated enough states.
        if(current.isEnded() || depth == 0){
            return current;
        }
        /*if the current state played by the maxPlayer*/
        else if (this.maxPlayer == maxPlayer){
            List<State> nextStates = new ArrayList<>();
            for (State s: this.getAllStates(current,current.getMaxPlayer())) {
                if(this.search(s, depth - 1, s.getMinPlayer()) != null)
                    nextStates.add(this.search(s, depth - 1, s.getMinPlayer()));
            }
            return this.getMaxHeuristic(nextStates);
        }
        /*if the current state played by the minPlayer*/
        else {
            List<State> nextStates = new ArrayList<>();
            for (State s: this.getAllStates(current,current.getMinPlayer())) {
                if(this.search(s, depth - 1, s.getMaxPlayer()) != null)
                    nextStates.add(this.search(s, depth - 1, s.getMaxPlayer()));
            }
            return this.getMaxHeuristic(nextStates);
        }
    }

    /**
     * this function returns all the possible next states we can move to.
     * @param state the current state
     * @param colorMove the color that will do the next move/
     * @return list of the next step
     */
    private ArrayList<State> getAllStates(State state, char colorMove){
        ArrayList<State> states = new ArrayList<>();
        for (int i = 0; i < state.getState().gameBoard.length; i++) {
            for (int j = 0; j < state.getState().gameBoard.length; j++) {
                if(state.getState().gameBoard[i][j] == 'E' && state.getState().isValidPosition(new Point(j,i))){
                    states.add(new State(new ReversiGame(state.getState().gameBoard), new Point(j,i),
                            state.getMaxPlayer(), state.getMinPlayer(),colorMove));
                }
            }
        }
        return states;
    }

    /**
     * return the state with the highest Heuristic
     * @param states list of states.
     * @return the state with the highest Heuristic.
     */
    private State getMaxHeuristic(List<State> states){
        double max = Double.NEGATIVE_INFINITY;
        State ret = states.get(0);
        for (State s: states) {
            if(s.getHeuristics() > max){
                max = s.getHeuristics();
                ret = s;
            }
        }
        return ret;
    }
}
