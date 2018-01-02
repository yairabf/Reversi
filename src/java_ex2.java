import java.awt.*;
import java.io.*;

public class java_ex2 {
    private static final String INPUT_FILENAME = "input.txt";
    private static final String OUTPUT_FILENAME = "output.txt";

    public static void main(String[] args) {
        char [][] gameBoard = new char[5][5];
        ReversiGame game = new ReversiGame(gameBoard);
        game.buildMap(INPUT_FILENAME);
        Minimax minimax = new Minimax('B');
        State end = minimax.search(game.getInitialState('B','W'),3,'B');
        while (!end.isEnded()){
            if(end.getMoveColor() == end.getMaxPlayer())
                end = minimax.search(end,3,end.getMinPlayer());
            else
                end = minimax.search(end,3,end.getMaxPlayer());
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILENAME))) {
            if(end.getHeuristics() > Double.NEGATIVE_INFINITY)
                bw.write(end.getMaxPlayer());
            else
                bw.write(end.getMinPlayer());
        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}