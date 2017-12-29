import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReversiGame {
    public char [][] gameBoard;

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public ReversiGame(char[][] gameBoard) {

        this.gameBoard = gameBoard;
    }

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

    public void printMap() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print("| ");
                System.out.print(this.gameBoard[i][j] + " |");
            }
            System.out.println("");
        }
    }

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

    public void paintUpRight(int count, char color, Point place) {
        int row = place.y - 1, col = place.x + 1;
        for (int j = 0; j < count; j++) {
            this.gameBoard[row][col] = color;
            row--; col++;
        }
    }

    public void paintUpLeft(int count, char color, Point place) {
        int row = place.y - 1, col = place.x - 1;
        for (int j = 0; j < count; j++) {
            this.gameBoard[row][col] = color;
            row--; col--;
        }
    }

    public void paintDownLeft(int count, char color, Point place) {
        int row = place.y + 1, col = place.x - 1;
        for (int j = 0; j < count; j++) {
            this.gameBoard[row][col] = color;
            row++; col--;
        }
    }

    public void paintDownRight(int count, char color, Point place) {
        int row = place.y + 1, col = place.x + 1;
        for (int j = 0; j < count; j++) {
            this.gameBoard[row][col] = color;
            row++; col++;
        }
    }

    public void paintDown(int count, char color, Point place) {
        for (int i = place.y + 1; i < count; i++) {
            this.gameBoard[i][place.x] = color;
        }
    }

    public void paintUp(int count, char color, Point place) {
        for (int i = count; i < place.y; i++) {
            this.gameBoard[i][place.x] = color;
        }
    }
    public void paintLeft(int count, char color, Point place) {
        for (int i = count; i < place.x; i++) {
            this.gameBoard[place.y][i] = color;
        }
    }
    public void paintRight(int count, char color, Point place) {
        for (int i = place.y + 1; i < count; i++) {
            this.gameBoard[place.y][i] = color;
        }
    }
}
