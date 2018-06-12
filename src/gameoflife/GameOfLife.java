package gameoflife;

import java.util.*;

/**
 *
 * @author emphelps
 */
public class GameOfLife {

    private static int ROW_SIZE = 20;
    private static int COL_SIZE = 20;
    private static final int PAUSE_MILLIS = 1000;

    private static int ALIVE_CHANCE = (int)(.3 * (ROW_SIZE * COL_SIZE));
  
    public static void main(String[] args) 
    {
        boolean[][] board = initializeBoard();
        boolean[][] boardCopy = board;
        
        while(true)
        {
            boardCopy = board;
            for (int i = 0; i < ROW_SIZE; i++) 
            {
                for (int j = 0; j < COL_SIZE; j++) 
                {
                    calculateState(board, boardCopy, i, j);
                }
            }
            
            System.out.println(getBoardContents(board));
            
            try
            {
                Thread.sleep(PAUSE_MILLIS);
            }
            catch(InterruptedException e)
            {
                
            }
        }
    }

    private static boolean[][] initializeBoard() 
    {
        boolean[][] board = new boolean[ROW_SIZE][COL_SIZE];
        
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
        
        return board;
    }
    
    private static void calculateState(boolean[][] board, boolean[][] boardCopy, int row, int col) 
    {
        int aliveNeighborCount = countAliveNeighbors(boardCopy, row, col);
        
        boolean tempState = false;
        if (boardCopy[row][col]) 
        {
            switch (aliveNeighborCount) 
            {
                case 2:
                case 3:
                    tempState = true;
                    break;
            }
        } 
        else 
        {
            if (aliveNeighborCount == 3) 
            {
                tempState = true;
            }
        }
        
        board[row][col] = tempState;
    }

    private static int countAliveNeighbors(boolean[][] boardCopy, int row, int col) 
    {
        int aliveCount = 0;
        
        for(int i = row - 1; i < row + 2 ; i++)
        {
            for(int j = col - 1; j < col + 2 ; j++)
            {
                if(row == i && col == j) continue;
                
                if(i < 0 || i >= ROW_SIZE) continue;
                
                if(j < 0 || j >= COL_SIZE) continue;
                
                if(boardCopy[i][j])
                {
                    aliveCount++;
                }
            }
        }
        
        return aliveCount;
    }

    private static String getBoardContents(boolean[][] board) 
    {
        String boardContents = "";
        
        for (int i = 0; i < ROW_SIZE; i++) 
        {
            for (int j = 0; j < COL_SIZE; j++) 
            {
                boardContents += (board[i][j] ? "*" : " ");
            }
            boardContents += "\n";
        }
        
        return boardContents;
    }
}