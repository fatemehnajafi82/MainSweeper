package proje;
import java.util.Random;
public class Board {
    private Cell[][] grid;
    private int rows, cols, mines;

    public Board(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        this.grid = new Cell[rows][cols];
        initializeBoard();
        placeMines();
        calculateAdjacentMines();
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    private void placeMines() {
        Random rand = new Random();
        int placedMines = 0;

        while (placedMines < mines) {
            int x = rand.nextInt(rows);
            int y = rand.nextInt(cols);

            if (!grid[x][y].isMine()) {
                grid[x][y].setMine();
                placedMines++;
            }
        }
    }

    private void calculateAdjacentMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!grid[i][j].isMine()) {
                    int count = countMinesAround(i, j);
                    grid[i][j].setAdjacentMines(count);
                }
            }
        }
    }

    private int countMinesAround(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = x + i;
                int newCol = y + j;

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    if (grid[newRow][newCol].isMine()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public void revealCell(int x, int y) {
        if (grid[x][y].isRevealed()) return;

        grid[x][y].reveal();
        if (grid[x][y].getAdjacentMines() == 0 && !grid[x][y].isMine()) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newRow = x + i;
                    int newCol = y + j;

                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                        revealCell(newRow, newCol);
                    }
                }
            }
        }
    }

    public void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].isRevealed()) {
                    if (grid[i][j].isMine()) System.out.print("* ");
                    else System.out.print(grid[i][j].getAdjacentMines() + " ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }

    // متد اصلاح‌شده برای دسترسی به گرید
    public Cell[][] getGrid() {
        return grid;
    }
}
