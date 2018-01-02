import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static final String INPUT_FILENAME = "input.txt";

    public static void main(String[] args) {
        char [][] gameBoard = new char[5][5];
        ReversiGame game = new ReversiGame(gameBoard);
        game.buildMap(INPUT_FILENAME);
        Minimax minimax = new Minimax('W');
        State end = minimax.search(game.getInitialState('W','B'),3,'W');
        while (!end.isEnded()){
            end = minimax.search(end,3,end.getMaxPlayer());
            end.printState();
            System.out.println("______________________________________");
        }

//        System.out.println(gameBoard.length);
//
//        game.printMap();
//        game.playerMove(new Point(0,4),'B');
//        System.out.println("______________________________________");
//        game.printMap();
//        game.playerMove(new Point(2,3),'W');
//        System.out.println("______________________________________");
//        game.printMap();
//        game.playerMove(new Point(4,4),'B');
//        System.out.println("______________________________________");
//        game.printMap();
        if(end.getHeuristics() > Double.NEGATIVE_INFINITY)
            System.out.println(end.getMaxPlayer());
        else
            System.out.println(end.getMinPlayer());
    }



}