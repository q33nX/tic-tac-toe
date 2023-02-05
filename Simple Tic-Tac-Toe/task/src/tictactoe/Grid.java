package tictactoe;
public class Grid {
    private final int size = 3;
    private boolean playerIndex = true; // true -> X player, false -> O player
    private final char[][] grid;

    public Grid() {
        this.grid = new char[this.size][this.size];
        draw();
    }

    protected void draw() {
        System.out.print("-".repeat(9));
        for (int i = 0; i < this.size; i++) {
            System.out.print("\n| ");
            for (int j = 0; j < this.size; j++) {
                System.out.print((this.grid[i][j] == 0 ? ' ' : this.grid[i][j]) + " ");
            }
            System.out.print("|");
        }
        System.out.println("\n" + "-".repeat(9));
    }

    protected void move(int row, int cell) {
        if (!isSuitable(row, cell)) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (!isAvailable(row - 1, cell - 1)) {
            throw new IllegalArgumentException();
        }

        this.grid[row - 1][cell - 1] = this.playerIndex ? 'X' : 'O';
        this.playerIndex = !this.playerIndex;
    }

    protected boolean gameStatus() {
        int rowLine = 0;
        int cellLine = 0;
        int mainDiagonal = 0;
        int secondDiagonal = 0;
        int emptyFields = 0;

        boolean xPlayer;
        boolean oPlayer;

        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                emptyFields += this.grid[i][j] == 0 ? 1 : 0;
                rowLine += this.grid[i][j];
                cellLine += this.grid[j][i];
                if (i == j) {
                    mainDiagonal += this.grid[i][j];
                }
                if (i + j == 2) {
                    secondDiagonal += this.grid[i][j];
                }
            }
            xPlayer = rowLine == 264 || cellLine == 264 || mainDiagonal == 264 || secondDiagonal == 264;
            oPlayer = rowLine == 237 || cellLine == 237 || mainDiagonal == 237 || secondDiagonal == 237;

            if (xPlayer || oPlayer) {
                System.out.println(xPlayer ? "X wins" : "O wins");
                return true;
            }

            rowLine = cellLine = 0;
        }

        if (emptyFields == 0) {
            System.out.println("Draw");
            return true;
        }

        return false;
    }

    private boolean isSuitable(int row, int cell) {
        return (row >= 0 && row <= this.size) && (cell >= 0 && cell <= this.size);
    }

    private boolean isAvailable(int row, int cell) {
        return this.grid[row][cell] == 0;
    }
}
