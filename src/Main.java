import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static final String INPUT_FILENAME = "input.txt";

    public static void main(String[] args) {
        char [][] gameBoard = new char[5][5];
        ReversiGame game = new ReversiGame(gameBoard);
        System.out.println(gameBoard.length);
        game.buildMap(INPUT_FILENAME);
        game.printMap();
        game.playerMove(new Point(0,4),'B');
        System.out.println("______________________________________");
        game.printMap();
        game.playerMove(new Point(2,3),'W');
        System.out.println("______________________________________");
        game.printMap();
        game.playerMove(new Point(4,4),'B');
        System.out.println("______________________________________");
        game.printMap();
    }



}