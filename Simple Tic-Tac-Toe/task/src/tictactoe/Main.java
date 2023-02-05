package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grid grid = new Grid();
        while (!grid.gameStatus()) {
            try {
                int row = Integer.parseInt(scanner.next());
                int cell = Integer.parseInt(scanner.next());
                grid.move(row, cell);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            grid.draw();
        }
    }
}
