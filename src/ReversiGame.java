import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class that represent the Reversi game
 */
public class ReversiGame {
    //the board of the game
    public char [][] gameBoard;

    /**
     * constructor
     * @param gameBoard
     */
    public ReversiGame(char[][] gameBoard) {
        this.gameBoard = new char[gameBoard.length][gameBoard.length];
        this.copyBoard(gameBoard);
    }

    /**
     * build game board from a file
     * @param path the path to the input file
     */
    public void buildMap(String path) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String currentLine;
            int line = 0;
            while ((currentLine = bufferedReader.readLine())!=null){
                for (int i = 0; i < currentLine.length(); i ++){
                    this.gameBoard[line][i] = currentLine.charAt(i);
                }
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for the initial state of the game.
     * @param maxPlayer
     * @param minPlayer
     * @return initial state of the game.
     */
    public State getInitialState(char maxPlayer, char minPlayer){
        return new State(this, maxPlayer, minPlayer);
    }

    /**
     * this function put a unit in the wanted place
     * @param place the wanted place
     * @param color the unit color
     */
    public void playerMove(Point place, char color){
        this.gameBoard[place.y][place.x] = color;
        //paint up
        int i = place.y - 1;
        int count = 0;
        while (i >= 0 && gameBoard[i][place.x] != color && this.gameBoard[i][place.x] != 'E'){
            i--;
            count++;
        }
        if(i >= 0 && this.gameBoard[i][place.x] == color)
            paintUp(place.y - count, color, place);

        // /paint down
        i = place.y + 1;
        count=0;
        while (i < this.gameBoard.length && this.gameBoard[i][place.x] != color && this.gameBoard[i][place.x] != 'E'){
            i++;
            count++;
        }
        if(i < this.gameBoard.length && this.gameBoard[i][place.x] == color)
            paintDown(place.y + count, color, place);

        //paint left
        i = place.x - 1;
        count=0;
        while (i >= 0 && this.gameBoard[place.y][i] != color && this.gameBoard[place.y][i] != 'E'){
            i--;
            count++;
        }
        if(i >= 0 &&  this.gameBoard[place.y][i] == color)
            paintLeft(place.x - count, color, place);

        //paint right
        i = place.x + 1;
        count=0;
        while (i < this.gameBoard.length && this.gameBoard[place.y][i] != color && this.gameBoard[place.y][i] != 'E'){
            i++;
            count++;
        }
        if(i < this.gameBoard.length &&  this.gameBoard[place.y][i] == color)
            paintRight(place.x + count, color, place);

        //paint Down and right x
        i = place.y + 1;
        int j = place.x + 1;
        count = 0;
        while (j < this.gameBoard.length && i < this.gameBoard.length && this.gameBoard[i][j] != color && this.gameBoard[i][j] != 'E'){
            i++;
            j++;
            count++;
        }
        if(i < this.gameBoard.length && j < this.gameBoard.length && this.gameBoard[i][j] == color)
            paintDownRight(count, color, place);

        //paint Down and left x
        i = place.y + 1;
        j = place.x - 1;
        count = 0;
        while (j >= 0 && i < this.gameBoard.length && this.gameBoard[i][j] != color && this.gameBoard[i][j] != 'E'){
            i++;
            j--;
            count++;
        }
        if(i < this.gameBoard.length && j > 0 && this.gameBoard[i][j] == color)
            paintDownLeft(count, color, place);

        //paint up and left x
        i = place.y - 1;
        j = place.x - 1;
        count = 0;
        while (j >= 0 && i >= 0 && this.gameBoard[i][j] != color && this.gameBoard[i][j] != 'E'){
            i--;
            j--;
            count++;
        }
        if(i >= 0 && j >= 0 && this.gameBoard[i][j] == color)
            paintUpLeft(count, color, place);


        //paint up and right x
        i = place.y - 1;
        j = place.x + 1;
        count = 0;
        while (i >= 0 && j < this.gameBoard.length && this.gameBoard[i][j] != color && this.gameBoard[i][j] != 'E'){
            i--;
            j++;
            count++;
        }
        if(i >= 0 && j < this.gameBoard.length && this.gameBoard[i][j] == color)
            paintUpRight(count, color, place);


    }

    /**
     * paint up and right slant
     * @param count the number of units we paint
     * @param color
     * @param place
     */
    private void paintUpRight(int count, char color, Point place) {
        int row = place.y - 1, col = place.x + 1;
        for (int j = 0; j < count; j++) {
            this.gameBoard[row][col] = color;
            row--; col++;
        }
    }

    private void paintUpLeft(int count, char color, Point place) {
        int row = place.y - 1, col = place.x - 1;
        for (int j = 0; j < count; j++) {
            this.gameBoard[row][col] = color;
            row--; col--;
        }
    }

    private void paintDownLeft(int count, char color, Point place) {
        int row = place.y + 1, col = place.x - 1;
        for (int j = 0; j < count; j++) {
            this.gameBoard[row][col] = color;
            row++; col--;
        }
    }

    private void paintDownRight(int count, char color, Point place) {
        int row = place.y + 1, col = place.x + 1;
        for (int j = 0; j < count; j++) {
            this.gameBoard[row][col] = color;
            row++; col++;
        }
    }

    private void paintDown(int count, char color, Point place) {
        for (int i = place.y + 1; i < count; i++) {
            this.gameBoard[i][place.x] = color;
        }
    }

    private void paintUp(int count, char color, Point place) {
        for (int i = count; i < place.y; i++) {
            this.gameBoard[i][place.x] = color;
        }
    }
    private void paintLeft(int count, char color, Point place) {
        for (int i = count; i < place.x; i++) {
            this.gameBoard[place.y][i] = color;
        }
    }
    private void paintRight(int count, char color, Point place) {
        for (int i = place.y + 1; i < count; i++) {
            this.gameBoard[place.y][i] = color;
        }
    }
    private void copyBoard(char [][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                this.gameBoard[i][j] = board[i][j];
            }
        }
    }

    /**
     * the function checks if this is a vail place to place a unit
     * @param point the location
     * @return true or false
     */
    public boolean isValidPosition(Point point) {
        int row = point.y;
        int column = point.x;
        int size = this.gameBoard.length;
        if (column < size - 1) {
            if (this.gameBoard[row][column + 1] != 'E')
                return true;
        }
        if ((column < size - 1) && (row < size - 1)) {
            if (this.gameBoard[row + 1][column + 1] != 'E')
                return true;
        }
        if (row < size - 1) {
            if (this.gameBoard[row + 1][column] != 'E')
                return true;
        }
        if ((column > 0) && (row < size - 1)) {
            if (this.gameBoard[row + 1][column - 1] != 'E')
                return true;
        }
        if (column > 0) {
            if (this.gameBoard[row][column - 1] != 'E')
                return true;
        }
        if ((column > 0) && (row > 0)) {
            if (this.gameBoard[row - 1][column - 1] != 'E')
                return true;
        }
        if (row > 0) {
            if (this.gameBoard[row - 1][column] != 'E')
                return true;
        }
        if ((column < size - 1) && (row > 0)) {
            if (this.gameBoard[row - 1][column + 1] != 'E')
                return true;
        }
        return false;
    }

}