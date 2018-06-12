package gameoflife;

import java.util.*;

/**
 *
 * @author emphelps
 */
public class GameOfLife {

    private static int ROW_SIZE = 10;
    private static int COL_SIZE = 10;

    private static int ALIVE_CHANCE = (int) (Math.random() * (ROW_SIZE * COL_SIZE));

    private static boolean[][] board;
    private static boolean[][] boardCopy;
    private static boolean[][] aliveCell;

    public static void main(String[] args) {
        initializeBoard();
        generateAliveCells();
        generateBoard();
        makeBoardCopy();
        printBoard();

        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                calculateState(i, j);
            }
        }

        System.out.println("----------");
        makeBoardCopy();
        printBoard();

    }

    private static void initializeBoard() {
        board = new boolean[ROW_SIZE][COL_SIZE];
        boardCopy = new boolean[ROW_SIZE][COL_SIZE];
    }

    private static void generateAliveCells() {
        Random rand = new Random();

        for (int i = 0; i < ALIVE_CHANCE; i++) {
            int randomAliveI = rand.nextInt(ROW_SIZE);
            int randomAliveJ = rand.nextInt(COL_SIZE);

            if (board[randomAliveI][randomAliveJ]) {
                i--;
            } else {
                board[randomAliveI][randomAliveJ] = true;
            }
        }
    }

    private static void makeBoardCopy() {
        for (int i = 0; i < ROW_SIZE; i++) {
            System.arraycopy(board[i], 0, boardCopy[i], 0, COL_SIZE);
        }
    }

    private static void generateBoard() {
        int aliveCount = 0;

        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                if (board[i][j]) {
                    continue;
                }

                board[i][j] = false;
            }
        }
    }

    private static void calculateState(int row, int col) {
        int aliveNeighborCount = countAliveNeighbors(row, col);

        System.out.println("aliveNeighborCount: " + aliveNeighborCount);

        if (boardCopy[row][col]) {
            switch (aliveNeighborCount) 
            {
                case 0:
                case 1:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    board[row][col] = false;
                    break;
            }
        } 
        else 
        {
            if (aliveNeighborCount == 3) 
            {
                board[row][col] = true;
            }
        }
    }

    private static int countAliveNeighbors(int row, int col) 
    {
        int aliveCount = 0;

        if (row == 0) 
        {
            if (col == 0) 
            {
                if (getState(row, col + 1)) 
                {
                    aliveCount++;
                }
                if (getState(row + 1, col)) 
                {
                    aliveCount++;
                }
                if (getState(row + 1, col + 1)) 
                {
                    aliveCount++;
                }
            } 
            else if (col == COL_SIZE - 1) 
            {
                if (getState(row, col - 1)) 
                {
                    aliveCount++;
                }
                if (getState(row + 1, col)) 
                {
                    aliveCount++;
                }
                if (getState(row + 1, col - 1)) 
                {
                    aliveCount++;
                }
            } 
            else 
            {
                if (getState(row, col - 1)) 
                {
                    aliveCount++;
                }
                if (getState(row, col + 1)) 
                {
                    aliveCount++;
                }
                if (getState(row + 1, col - 1)) 
                {
                    aliveCount++;
                }
                if (getState(row + 1, col)) 
                {
                    aliveCount++;
                }
                if (getState(row + 1, col + 1)) 
                {
                    aliveCount++;
                }
            }
        } 
        else if (col == 0) 
        {
            if(row == ROW_SIZE - 1)
            {
                if (getState(row - 1, col)) 
                {
                    aliveCount++;
                }
                if (getState(row - 1, col + 1)) 
                {
                    aliveCount++;
                }
                if (getState(row, col + 1)) 
                {
                    aliveCount++;
                }
            }
            else
            {
                if (getState(row - 1, col)) 
                {
                    aliveCount++;
                }
                if (getState(row - 1, col + 1)) 
                {
                    aliveCount++;
                }
                if (getState(row, col + 1)) 
                {
                    aliveCount++;
                }
                if (getState(row + 1, col)) 
                {
                    aliveCount++;
                }
                if (getState(row + 1, col + 1)) 
                {
                    aliveCount++;
                }
            }
        } 
        else if (row == ROW_SIZE - 1) 
        {
            if (col == COL_SIZE - 1) 
            {
                if (getState(row, col - 1)) 
                {
                    aliveCount++;
                }
                if (getState(row - 1, col - 1)) 
                {
                    aliveCount++;
                }
                if (getState(row - 1, col)) 
                {
                    aliveCount++;
                }
            }
            else
            {
                if (getState(row, col - 1)) 
                {
                    aliveCount++;
                }
                if (getState(row, col + 1)) 
                {
                    aliveCount++;
                }
                if (getState(row - 1, col - 1)) 
                {
                    aliveCount++;
                }
                if (getState(row - 1, col)) 
                {
                    aliveCount++;
                }
                if (getState(row - 1, col + 1)) 
                {
                    aliveCount++;
                }
            }

        } 
        else if (col == COL_SIZE - 1) 
        {
            if (getState(row - 1, col)) 
            {
                aliveCount++;
            }
            if (getState(row - 1, col - 1)) 
            {
                aliveCount++;
            }
            if (getState(row, col - 1)) 
            {
                aliveCount++;
            }
            if (getState(row + 1, col)) 
            {
                aliveCount++;
            }
            if (getState(row + 1, col - 1)) 
            {
                aliveCount++;
            }
        }
        else
        {
            if (getState(row - 1, col - 1)) 
            {
                aliveCount++;
            }
            if (getState(row - 1, col)) 
            {
                aliveCount++;
            }
            if (getState(row - 1, col + 1)) 
            {
                aliveCount++;
            }
            if (getState(row, col - 1)) 
            {
                aliveCount++;
            }
            if (getState(row, col + 1)) 
            {
                aliveCount++;
            }
             if (getState(row + 1, col - 1)) 
            {
                aliveCount++;
            }
            if (getState(row + 1, col)) 
            {
                aliveCount++;
            }
            if (getState(row + 1, col + 1)) 
            {
                aliveCount++;
            }
        }

        return aliveCount;
    }

    private static boolean getState(int row, int col) 
    {
        return board[row][col];
    }

    private static void printBoard() 
    {
        for (int i = 0; i < ROW_SIZE; i++) 
        {
            for (int j = 0; j < COL_SIZE; j++) 
            {
                if (board[i][j]) 
                {
                    System.out.print("*");
                } 
                else 
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}
