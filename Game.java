package proje;
import java.util.Scanner;

public class Game {
    private Board board;
    private boolean isGameOver;

    public Game(int rows, int cols, int mines) {
        board = new Board(rows, cols, mines);
        isGameOver = false;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver) {
            board.printBoard();
            System.out.print("Enter row and column (e.g., 1 1): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            board.revealCell(x, y);
            if (board.getGrid()[x][y].isMine()) {
                isGameOver = true;
                System.out.println("Game Over! You hit a mine!");
                board.printBoard();
            }
        }
        scanner.close();
    }
}
