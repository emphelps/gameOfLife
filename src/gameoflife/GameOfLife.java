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

    private static boolean[][] board;
    private static boolean[][] boardCopy;
  
    public static void main(String[] args) 
    {
        initializeBoard();
        
        while(true)
        {
            boardCopy = board;
            for (int i = 0; i < ROW_SIZE; i++) 
            {
                for (int j = 0; j < COL_SIZE; j++) 
                {
                    calculateState(i, j);
                }
            }
            
            System.out.println(getBoardContents());
            
            try
            {
                Thread.sleep(PAUSE_MILLIS);
            }
            catch(InterruptedException e)
            {
                
            }
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
    
    private static void calculateState(int row, int col) 
    {
        int aliveNeighborCount = countAliveNeighbors(row, col);
        
        boolean tempState = false;
        if (getState(row, col)) 
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

    private static int countAliveNeighbors(int row, int col) 
    {
        int aliveCount = 0;
        
        for(int i = row - 1; i < row + 2 ; i++)
        {
            for(int j = col - 1; j < col + 2 ; j++)
            {
                if(row == i && col == j) continue;
                
                if(i < 0 || i >= ROW_SIZE) continue;
                
                if(j < 0 || j >= COL_SIZE) continue;
                
                if(getState(i, j))
                {
                    aliveCount++;
                }
            }
        }
        
        return aliveCount;
    }

    private static boolean getState(int row, int col) 
    {
        return boardCopy[row][col];
    }

    private static String getBoardContents() 
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