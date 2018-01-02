import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Minimax {

    public State search(State current, int depth, char maxPlayer){
        if(current.isEnded() || depth == 0){
            return current;
        }
        else {
            List<State> nextStates = new ArrayList<>();
            State next_state = nextStates.get(0);
            for (State s: this.getAllStates(current)) {
                nextStates.add(this.search(s, depth - 1, s.getMaxPlayer()));
            }
            return this.getMaxHuristic(nextStates);
        }
    }

    public ArrayList<State> getAllStates(State state){
        ArrayList states = new ArrayList();
        for (int i = 0; i < state.getState().gameBoard.length; i++) {
            for (int j = 0; j < state.getState().gameBoard.length; j++) {
                if(state.getState().gameBoard[i][j] == 'E'){
                    states.add(new State(state.getState(), state, new Point(j,i), state.getMinPlayer(), state.getMaxPlayer()));
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
