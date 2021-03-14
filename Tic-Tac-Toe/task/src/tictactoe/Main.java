package tictactoe;
import java.util.Arrays;
import java.util.InputMismatchException;
import  java.util.Scanner;
public class Main {
    public static  String horizontalLine = "---------";
    public static int marks = 0;
    public static int totalX = 0;
    public static int totalO = 0;
    public static int totalEmpty = 9;
    public static void main(String[] args) {
        // write your code here
        char[][] gameBoard = new char[3][3];

        System.out.println(horizontalLine);
        for (int index = 0; index < gameBoard.length; index++) {
            for (int subindex = 0; subindex < gameBoard[index].length; subindex++){
                if(marks == 0
                        || marks == 3
                        || marks == 6) {
                    System.out.print("| ");
                }

                gameBoard[index][subindex] = ' ';

                System.out.print(gameBoard[index][subindex] + " ");
                if (marks == 2 || marks == 5 || marks == 8) {
                    System.out.print("|");
                }
                marks++;
            }
            System.out.println();
        }
        System.out.println(horizontalLine);
        char[] threeX = {'X','X','X'};
        String threeXWin = Arrays.toString(threeX);
        char[] threeO = {'O','O','O'};
        String threeOWin = Arrays.toString(threeO);
        char[] colArray = new char [3];
        char[] diagonalArray1 = new char [3];
        char[] diagonalArray2 = new char [3];
        int counter = 0;
        boolean xWin = false;
        while (totalEmpty != 0){
            printBoard(gameBoard);


            for (int rowindex = 0; rowindex < gameBoard.length; rowindex++){
                //looping to check all the columns
                for(int colIndex = 0; colIndex < gameBoard[rowindex].length; colIndex++) {
                    colArray[colIndex] = gameBoard[colIndex][rowindex];
                }

                if(rowindex == 0){
                    //looping to check the 1st diagonal
                    int i = 0;
                    for (int colIndex = 0; colIndex < gameBoard[rowindex].length; colIndex++) {
                        diagonalArray1[colIndex] = gameBoard[i][colIndex];
                        i++;
                    }

                    //looping to check the 2nd diagonal
                    int j = 0;
                    for (int colIndex = gameBoard[rowindex].length - 1; colIndex >= 0; colIndex--){
                        diagonalArray2[j] = gameBoard[j][colIndex];
                        j++;
                    }
                }

                String rowResult = Arrays.toString(gameBoard[rowindex]);
                String colResult = Arrays.toString(colArray);

                if ((rowResult.equals(threeXWin))
                        || (colResult.equals(threeXWin))) {
                    counter += 1;
                    xWin = true;
                } else if  ((rowResult.equals(threeOWin))
                        || (colResult.equals(threeOWin))) {
                    counter += 1;
                }
            }

            String diagonalResult1 = Arrays.toString(diagonalArray1);
            String diagonalResult2 = Arrays.toString(diagonalArray2);
            //let's check the diagonal result out of the loop;
            if (diagonalResult1.equals(threeXWin) || diagonalResult2.equals(threeXWin)) {
                counter += 1;
                xWin = true;
            } else if (diagonalResult1.equals(threeOWin) || diagonalResult2.equals(threeOWin)) {
                counter += 1;
            }

            if (counter == 1 && xWin) {
                System.out.println("X wins");
                break;
            } else  if (counter == 1 && !xWin) {
                System.out.println("O wins");
                break;
            } else if (totalEmpty == 0 && counter == 0) {
                System.out.println("Draw");
                break;
            }
        }

    }

    public static void printBoard(char[][] gameBoard){
        System.out.print("Enter the coordinates: ");
        Scanner scanner2 = new Scanner(System.in);
        try{
            int coordinate1 = scanner2.nextInt();
            int coordinate2 = scanner2.nextInt();
            if (coordinate1 > 3 || coordinate1 < 1 || coordinate2 > 3 || coordinate2 < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                int boardRow = coordinate1 - 1;
                int boardCol = coordinate2 - 1;

                if( gameBoard[boardRow][boardCol] == ' ') {
                    marks = 0;
                    System.out.println(horizontalLine);
                    for (int index = 0; index < gameBoard.length; index++) {
                        for (int subindex = 0; subindex < gameBoard[index].length; subindex++){
                            if(marks == 0
                                    || marks == 3
                                    || marks == 6) {
                                System.out.print("| ");
                            }
                            if (index == boardRow && subindex == boardCol) {
                                char userMove = totalEmpty % 2 != 0 ? 'X' : 'O';
                                gameBoard[index][subindex] = userMove;
                                totalEmpty -= 1;
                            } else {
                                gameBoard[index][subindex] = gameBoard[index][subindex];
                            }

                            if(gameBoard[boardRow][boardCol] == 'X'){
                                totalX += 1;
                            } else if(gameBoard[boardRow][boardCol] == 'O') {
                                totalO += 1;
                            }

                            System.out.print(gameBoard[index][subindex] + " ");
                            if (marks == 2 || marks == 5 || marks == 8) {
                                System.out.print("|");
                            }
                            marks++;
                        }
                        System.out.println();
                    }
                    System.out.println(horizontalLine);

                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("You should enter numbers!");
        }
    }
}
