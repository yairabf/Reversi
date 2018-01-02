import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Minimax {
    private char maxPlayer;

    public Minimax(char maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public State search(State current, int depth, char maxPlayer){
        if(current.isEnded() || depth == 0){
            return current;
        }
        else if (this.maxPlayer == maxPlayer){
            List<State> nextStates = new ArrayList<>();
            for (State s: this.getAllStates(current,current.getMaxPlayer())) {
                if(this.search(s, depth - 1, s.getMinPlayer()) != null)
                    nextStates.add(this.search(s, depth - 1, s.getMinPlayer()));
            }
            return this.getMaxHuristic(nextStates);
        }
        else {
            List<State> nextStates = new ArrayList<>();
            for (State s: this.getAllStates(current,current.getMinPlayer())) {
                if(this.search(s, depth - 1, s.getMaxPlayer()) != null)
                    nextStates.add(this.search(s, depth - 1, s.getMaxPlayer()));
            }
            return this.getMaxHuristic(nextStates);
        }
    }

    public ArrayList<State> getAllStates(State state, char colorMove){
        ArrayList states = new ArrayList();
        for (int i = 0; i < state.getState().gameBoard.length; i++) {
            for (int j = 0; j < state.getState().gameBoard.length; j++) {
                if(state.getState().gameBoard[i][j] == 'E'){
                    states.add(new State(new ReversiGame(state.getState().gameBoard), state, new Point(j,i), state.getMaxPlayer(), state.getMinPlayer(),colorMove));
                }
            }
        }
        return states;
    }

    private State getMaxHuristic(List<State> states){
        double max = Double.NEGATIVE_INFINITY;
        State ret = null;
        for (State s: states) {
            if(s.getHeuristics() > max){
                max = s.getHeuristics();
                ret = s;
            }
        }
        return ret;
    }
}
