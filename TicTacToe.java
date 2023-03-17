import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static ArrayList<Integer> userPositions = new ArrayList<>();
    static ArrayList<Integer> computerPositions = new ArrayList<>();

    public static void main(String[] args) {
        // create a board
        char[][] myGameBoard = {

                { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ' },
                { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ' },
                { '_', '_', '_', '|', '_', '_', '_', '|', '_', '_', '_' },

                { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ' },
                { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ' },
                { '_', '_', '_', '|', '_', '_', '_', '|', '_', '_', '_' },

                { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ' },
                { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ' },
        };

        displayGameBoard(myGameBoard);
        boolean isGameOver = false;
        while (!isGameOver) {
            // asking user for position
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the position you want to place 'X': ");
            int userPositon = sc.nextInt();
            while (userPositions.contains(userPositon) || computerPositions.contains(userPositon)) {
                System.out.print("Position entered is already taken, please enter another position: ");
                userPositon = sc.nextInt();
            }
            userPositions.add(userPositon);
            placeCharacter(myGameBoard, userPositon, "user");
            displayGameBoard(myGameBoard);
            String winner = checkWinner();
            System.out.println(winner);
            if (winner.length() > 0) {
                isGameOver = true;
                break;
            }
            // taking random postion for computer
            Random rand = new Random();
            int computerPosition = rand.nextInt(9) + 1;
            while (computerPositions.contains(computerPosition) || userPositions.contains(computerPosition)) {
                computerPosition = rand.nextInt(9) + 1;
            }
            computerPositions.add(computerPosition);
            placeCharacter(myGameBoard, computerPosition, "computer");
            displayGameBoard(myGameBoard);
            winner = checkWinner();
            System.out.println(winner);
            if (winner.length() > 0) {
                isGameOver = true;
                break;
            }

        }

    }

    public static void displayGameBoard(char[][] board) {
        for (char[] row : board) {
            for (char characters : row) {
                System.out.print(characters);
            }
            System.out.println();
        }
    }

    public static void placeCharacter(char[][] gameBoard, int postion, String user) {
        char userChoice = ' ';
        if (user.equals("computer")) {
            userChoice = 'O';
        } else if (user.equals("user")) {
            userChoice = 'X';
        }

        switch (postion) {
            case 1:
                gameBoard[1][1] = userChoice;
                break;
            case 2:
                gameBoard[1][5] = userChoice;
                break;
            case 3:
                gameBoard[1][9] = userChoice;
                break;
            case 4:
                gameBoard[4][1] = userChoice;
                break;
            case 5:
                gameBoard[4][5] = userChoice;
                break;
            case 6:
                gameBoard[4][9] = userChoice;
                break;
            case 7:
                gameBoard[7][1] = userChoice;
                break;
            case 8:
                gameBoard[7][5] = userChoice;
                break;
            case 9:
                gameBoard[7][9] = userChoice;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List firstRowCond = Arrays.asList(1, 2, 3);
        List secondRowCond = Arrays.asList(4, 5, 6);
        List thirdRowCond = Arrays.asList(7, 8, 9);

        List firstColCond = Arrays.asList(1, 4, 7);
        List secondColCond = Arrays.asList(2, 5, 8);
        List thirdColCond = Arrays.asList(3, 6, 9);

        List leftToRightCrossCond = Arrays.asList(1, 5, 9);
        List rightToLeftCrossCond = Arrays.asList(3, 5, 7);

        ArrayList<List> winningConditions = new ArrayList<>();
        winningConditions.add(firstRowCond);
        winningConditions.add(secondRowCond);
        winningConditions.add(thirdRowCond);
        winningConditions.add(firstColCond);
        winningConditions.add(secondColCond);
        winningConditions.add(thirdColCond);
        winningConditions.add(leftToRightCrossCond);
        winningConditions.add(rightToLeftCrossCond);

        String winner = "";

        for (List condition : winningConditions) {
            if (userPositions.containsAll(condition)) {
                winner = "Congrats you won!!!";
            } else if (computerPositions.containsAll(condition)) {
                winner = "Computer won! better luck next time";
            } else if (!userPositions.containsAll(condition) && !computerPositions.containsAll(condition) &&
                    userPositions.size() + computerPositions.size() == 9) {
                winner = "Its a tie!!";
            }
        }
        return winner;
    }
}