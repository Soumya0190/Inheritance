import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private char[][] board = new char[ROWS][COLS];
    private char currentPlayer = 'X';

    public ConnectFour() {
        // Initialize the board
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    public boolean dropPiece(int column) {
        if (column < 0 || column >= COLS || board[0][column] != ' ') {
            return false; // Invalid move
        }

        // Drop piece in the first available row
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    public boolean checkWin() {
        // Check horizontal, vertical, and diagonal wins
        return checkHorizontal() || checkVertical() || checkDiagonals();
    }

    private boolean checkHorizontal() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j] == currentPlayer &&
                    board[i][j + 1] == currentPlayer &&
                    board[i][j + 2] == currentPlayer &&
                    board[i][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical() {
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == currentPlayer &&
                    board[i + 1][j] == currentPlayer &&
                    board[i + 2][j] == currentPlayer &&
                    board[i + 3][j] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        // Check for diagonal win in both directions
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j] == currentPlayer &&
                    board[i + 1][j + 1] == currentPlayer &&
                    board[i + 2][j + 2] == currentPlayer &&
                    board[i + 3][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j] == currentPlayer &&
                    board[i - 1][j + 1] == currentPlayer &&
                    board[i - 2][j + 2] == currentPlayer &&
                    board[i - 3][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        return false;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        Scanner scanner = new Scanner(System.in);

        game.printBoard();

        while (true) {
            System.out.println("Player " + game.currentPlayer + "'s turn.");
            System.out.print("Enter column (0-6): ");
            int column = scanner.nextInt();

            if (game.dropPiece(column)) {
                game.printBoard();
                if (game.checkWin()) {
                    System.out.println("Player " + game.currentPlayer + " wins!");
                    break;
                }
                game.switchPlayer();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }
}
