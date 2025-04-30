# Inheritance
https://canvas.eee.uci.edu/courses/21534/assignments/401808


---

Certainly! The STAR method is a great way to structure your explanation of a project or experience. STAR stands for:

- **S**ituation: Describe the context or challenge.
- **T**ask: Explain what you were responsible for.
- **A**ction: Detail what actions you took to address the task.
- **R**esult: Share the outcome of your actions.

Let me break down your ConnectFour and TicTacToe project using the STAR method:

---

### **Situation**:
You were working on a software development project that involved creating two different game classes: **TicTacToe** and **ConnectFour**. The goal was to implement the core mechanics of both games in a way that they could be played interactively. This required you to design classes that manage the game state, handle player turns, and determine winners. Additionally, the game was built with an object-oriented approach, focusing on inheritance where **ConnectFour** inherited from **TicTacToe**.

### **Task**:
Your main responsibility was to develop the logic behind these games. Specifically, you had to:
- Design a **TicTacToe** class that represents the board, tracks the current player, and checks for win conditions.
- Implement a **ConnectFour** class that builds upon **TicTacToe**, adding functionality specific to the Connect Four game, such as managing column-based piece placement and checking for a Connect Four win condition in horizontal, vertical, and diagonal directions.

### **Action**:
- **TicTacToe Class**: You designed the board as a 2D array and implemented a turn-tracking system where each player alternates turns. You also built a method to check if there's a winner by analyzing rows, columns, and diagonals.
  
- **ConnectFour Class**: You extended the **TicTacToe** class to build **ConnectFour**, customizing it with a specific column-based piece dropping mechanism. You added an array (`lst`) to track the available row for each column. Additionally, you wrote logic to check for a winner by examining multiple directions (horizontal, vertical, diagonal).

- **Game Mechanics**: You ensured that after every move, the game correctly switches turns between the two players. If a player made a valid move, the board updated, and after every turn, the game checked if there was a winner by analyzing all possible win conditions (four consecutive pieces).

### **Result**:
The outcome was the successful creation of two game classes:
1. **TicTacToe**: A basic board game with turn-taking and win-checking functionality.
2. **ConnectFour**: An extension of TicTacToe with a more complex piece placement system, ensuring the game followed the rules of Connect Four (pieces drop into columns and the game checks for a Connect Four in multiple directions).

Both classes demonstrated the ability to handle interactive gameplay with proper turn management and win detection. By extending **TicTacToe** into **ConnectFour**, you applied object-oriented principles like inheritance, which made the code more modular and reusable. The project was a good practice in implementing game logic, understanding user interaction, and applying fundamental programming concepts.

---

This breakdown in the STAR method clearly outlines the context, your role, actions, and the result. It highlights how you applied software development principles and how your work delivered functional games!

---

Sure! Let's break down and explain the solution for both **TicTacToe** and **ConnectFour** classes.

### **TicTacToe Class** Explanation:

The **TicTacToe** class is the base class that implements the logic for a standard Tic-Tac-Toe game. The game is played on a 3x3 grid, and players take turns placing their symbols (usually X and O) on the grid. The goal is to get three of your symbols in a row, column, or diagonal.

Here's a detailed breakdown of the **TicTacToe** class:

```python
class TicTacToe:
    def __init__(self):
        self.board = [[' ' for _ in range(3)] for _ in range(3)]  # 3x3 grid initialized with empty spaces
        self.current_player = 'X'  # Player X always goes first
        self.winner = None  # There is no winner at the start

    def print_board(self):
        """Prints the current game board."""
        for row in self.board:
            print("|".join(row))
            print("-" * 5)  # Print horizontal separator for rows

    def check_win(self):
        """Checks for a winner."""
        # Check rows
        for row in self.board:
            if row[0] == row[1] == row[2] != ' ':
                return row[0]

        # Check columns
        for col in range(3):
            if self.board[0][col] == self.board[1][col] == self.board[2][col] != ' ':
                return self.board[0][col]

        # Check diagonals
        if self.board[0][0] == self.board[1][1] == self.board[2][2] != ' ':
            return self.board[0][0]
        if self.board[0][2] == self.board[1][1] == self.board[2][0] != ' ':
            return self.board[0][2]

        return None  # No winner yet

    def make_move(self, row, col):
        """Makes a move for the current player."""
        if self.board[row][col] == ' ':
            self.board[row][col] = self.current_player
            self.winner = self.check_win()
            self.current_player = 'O' if self.current_player == 'X' else 'X'  # Switch player after each turn
            return True
        return False

    def is_full(self):
        """Returns True if the board is full, otherwise False."""
        for row in self.board:
            for cell in row:
                if cell == ' ':
                    return False
        return True
```

#### **Explanation of Key Parts**:

- **Initialization (`__init__`)**: This sets up a 3x3 board with empty spaces represented by `' '`, initializes the current player as 'X', and sets the winner to `None`.

- **`print_board()`**: This function prints the current board to the console. It loops through each row and prints each cell, separating them with `|`. After each row, it prints a line of dashes to separate the rows visually.

- **`check_win()`**: This function checks for a win condition:
  - **Rows**: It checks each row to see if all three cells are the same (either 'X' or 'O').
  - **Columns**: It checks each column to see if all three cells are the same.
  - **Diagonals**: It checks the two diagonals to see if all three cells are the same.
  If any of these conditions are met, it returns the winner ('X' or 'O'). If no winner is found, it returns `None`.

- **`make_move()`**: This method allows the current player to place their symbol on the board at a given row and column. If the move is valid (i.e., the cell is empty), it updates the board, checks for a winner, switches the current player, and returns `True`. If the move is invalid, it returns `False`.

- **`is_full()`**: This function checks if the board is completely filled with no empty spaces left. It returns `True` if the board is full, otherwise, `False`.

---

### **ConnectFour Class** Explanation:

The **ConnectFour** class extends the **TicTacToe** class, but instead of a 3x3 grid, the game is played on a 6x7 grid. Players take turns dropping pieces into columns, and the goal is to get four of their pieces in a row, column, or diagonal.

Here's a breakdown of the **ConnectFour** class:

```python
class ConnectFour(TicTacToe):
    def __init__(self):
        super().__init__()  # Call the TicTacToe constructor to initialize the board and current player
        self.board = [[' ' for _ in range(7)] for _ in range(6)]  # 6x7 grid for Connect Four
        self.lst = [5] * 7  # List to track the available row for each column (starting from the bottom)

    def print_board(self):
        """Prints the Connect Four board."""
        for row in self.board:
            print("|".join(row))
            print("-" * 7)  # Print separator for the Connect Four grid

    def make_move(self, col):
        """Makes a move by placing a piece in the given column."""
        row = self.lst[col]  # Get the available row in the selected column
        if row >= 0:
            self.board[row][col] = self.current_player
            self.lst[col] -= 1  # Decrease the available row for the next piece in this column
            self.winner = self.check_win()  # Check for a winner after the move
            self.current_player = 'O' if self.current_player == 'X' else 'X'  # Switch player
            return True
        return False

    def check_win(self):
        """Checks for a winner in Connect Four."""
        # Check for horizontal, vertical, and diagonal lines of four pieces
        for row in range(6):
            for col in range(7):
                if self.board[row][col] != ' ':
                    # Check horizontal
                    if col + 3 < 7 and all(self.board[row][col + i] == self.board[row][col] for i in range(4)):
                        return self.board[row][col]
                    # Check vertical
                    if row + 3 < 6 and all(self.board[row + i][col] == self.board[row][col] for i in range(4)):
                        return self.board[row][col]
                    # Check diagonal (top-left to bottom-right)
                    if row + 3 < 6 and col + 3 < 7 and all(self.board[row + i][col + i] == self.board[row][col] for i in range(4)):
                        return self.board[row][col]
                    # Check diagonal (bottom-left to top-right)
                    if row - 3 >= 0 and col + 3 < 7 and all(self.board[row - i][col + i] == self.board[row][col] for i in range(4)):
                        return self.board[row][col]
        return None
```

#### **Explanation of Key Parts**:

- **Initialization (`__init__`)**: The **ConnectFour** constructor first calls the **TicTacToe** constructor with `super().__init__()`, which sets up the basic board. It then creates a 6x7 board for Connect Four and initializes a `lst` list, which keeps track of the lowest available row for each column (starting at the bottom).

- **`print_board()`**: This function prints the 6x7 grid for Connect Four, similar to **TicTacToe** but with a larger grid. It prints a horizontal separator (`-` * 7) to clearly distinguish the rows.

- **`make_move()`**: In Connect Four, players place their pieces into columns. The **make_move** method updates the board by placing the current player's piece in the lowest available row of the selected column (tracked using `lst`). It decreases the available row for the column and switches the current player.

- **`check_win()`**: This function checks for a win in Connect Four by looking for a line of four consecutive pieces in:
  - **Horizontal direction**: Checks four consecutive cells in the same row.
  - **Vertical direction**: Checks four consecutive cells in the same column.
  - **Diagonal (top-left to bottom-right)**: Checks for four consecutive pieces in a diagonal from top-left to bottom-right.
  - **Diagonal (bottom-left to top-right)**: Checks for four consecutive pieces in a diagonal from bottom-left to top-right.
  
  If any of these conditions are met, the winner ('X' or 'O') is returned. If no winner is found, `None` is returned.

---

### **Overall Summary**:

- **TicTacToe** handles a 3x3 grid with simple row, column, and diagonal checks for a winner.
- **ConnectFour** extends **TicTacToe**, uses a 6x7 grid, and adds logic for dropping pieces into columns. The win condition checks for four consecutive pieces in horizontal, vertical, and diagonal directions.
  
Both classes follow object-oriented principles, and **ConnectFour** inherits from **TicTacToe**, allowing for code reuse and cleaner structure. The program handles player turns, updates the board after each move, and checks for winners in both games.

---

Here's the corrected solution in **Java** for both the **Connect Four** and **Tic Tac Toe** games.

### **ConnectFour.java**

```java
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
```

### **TicTacToe.java**

```java
import java.util.Scanner;

public class TicTacToe {
    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';

    public TicTacToe() {
        // Initialize the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("-----");
        }
    }

    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            return false; // Invalid move
        }

        board[row][col] = currentPlayer;
        return true;
    }

    public boolean checkWin() {
        // Check rows, columns, and diagonals
        return checkRows() || checkCols() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCols() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        game.printBoard();

        while (true) {
            System.out.println("Player " + game.currentPlayer + "'s turn.");
            System.out.print("Enter row and column (0-2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (game.makeMove(row, col)) {
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
```

### **Explanation of Code**:

1. **Initialization**:
    - The board is initialized as a 2D array of characters (`' '` for empty slots).
    - The current player starts as `'X'`.

2. **Gameplay**:
    - Players take turns to enter their moves (for TicTacToe: row and column; for Connect Four: column).
    - The board is displayed after each move.
    - The game checks for a winner after every move.
    - Players are switched after each move.

3. **Win Check**:
    - **TicTacToe** checks for a win by examining rows, columns, and diagonals.
    - **ConnectFour** checks for a win horizontally, vertically, and diagonally.

4. **Switch Players**:
    - After each valid move, the player is switched between `'X'` and `'O'`.

### **To Run the Code**:

1. **Save the files**:
   - Save the TicTacToe code in `TicTacToe.java` and ConnectFour code in `ConnectFour.java`.

2. **Compile and Run**:
   - In the terminal or command prompt, navigate to the directory where the `.java` files are saved.
   - Compile the Java files:
     ```bash
     javac TicTacToe.java
     javac ConnectFour.java
     ```
   - Run the desired game:
     ```bash
     java TicTacToe
     ```
     or
     ```bash
     java ConnectFour
     ```

3. **Gameplay**:
   - Follow the prompts in the terminal to play the game.

This should launch the game and allow you to play it directly in the terminal!