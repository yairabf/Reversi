import java.awt.*;
import java.util.ArrayList;

public class Minimax {

    private ReversiGame game;


    public ArrayList getAllStates(State state){
        ArrayList states = new ArrayList();
        for (int i = 0; i < game.gameBoard.length; i++) {
            for (int j = 0; j < game.gameBoard.length; j++) {
                if(game.gameBoard[i][j] == 'E'){
                    states.add(new State(game,state, new Point(j,i),state.getMinPlayer(),state.getMaxPlayer()));
                }
            }
        }
        return states;
    }
}
