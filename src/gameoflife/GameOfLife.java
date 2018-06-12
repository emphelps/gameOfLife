package gameoflife;

import java.util.*;

/**
 *
 * @author emphelps
 */
public class GameOfLife {

    private static int ROW_SIZE = 20;
    private static int COL_SIZE = 20;

    private static int ALIVE_CHANCE = (int)(.3 * (ROW_SIZE * COL_SIZE));

    private static boolean[][] board;
    private static boolean[][] boardCopy;
    private static boolean[][] aliveCell;

    public static void main(String[] args) {
        initializeBoard();
        
        while(true)
        {
            for (int i = 0; i < ROW_SIZE; i++) 
            {
                for (int j = 0; j < COL_SIZE; j++) 
                {
                    calculateState(i, j);
                }
            }
            boardCopy = board;
            System.out.println(getBoardContents());
        }

    }

    private static void initializeBoard() 
    {
        board = new boolean[ROW_SIZE][COL_SIZE];
        boardCopy = new boolean[ROW_SIZE][COL_SIZE];
        
        Random rand = new Random();
        for (int i = 0; i < ALIVE_CHANCE; i++) 
        {
            int randomAliveI = rand.nextInt(ROW_SIZE);
            int randomAliveJ = rand.nextInt(COL_SIZE);

            if (board[randomAliveI][randomAliveJ]) 
            {
                i--;
            } 
            else 
            {
                board[randomAliveI][randomAliveJ] = true;
            }
        }
    }
    
    private static void calculateState(int row, int col) {
        int aliveNeighborCount = countAliveNeighbors(row, col);

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

    private static String getBoardContents() 
    {
        String boardContents = "";
        
        for (int i = 0; i < ROW_SIZE; i++) 
        {
            for (int j = 0; j < COL_SIZE; j++) 
            {
                calculateState(i, j);
                //System.out.print(board[i][j] ? "*" : " ");
                boardContents += (board[i][j] ? "*" : " ").toString();
            }
            boardContents += "\n";
        }
        
        return boardContents;
    }

}
