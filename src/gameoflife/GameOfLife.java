package gameoflife;

import java.util.*;
/**
 *
 * @author emphelps
 */
public class GameOfLife {

    private static int ROW_SIZE = 10;
    private static int COL_SIZE = 10;
    
    private static int ALIVE_CHANCE = (int)(Math.random() * (ROW_SIZE * COL_SIZE));
    
    private static boolean[][] board;
    private static boolean[][] boardCopy;
    private static boolean[][] aliveCell;
    
    public static void main(String[] args)
    {
        initializeBoard();
        generateAliveCells();
        generateBoard();
        
        printBoard();
    }
    
    private static void initializeBoard()
    {
        board = new boolean[ROW_SIZE][COL_SIZE];
        boardCopy = new boolean[ROW_SIZE][COL_SIZE];
    }
    
    private static void generateAliveCells()
    {
        Random rand = new Random();
        
        for(int i = 0; i < ALIVE_CHANCE; i++)
        {
            int randomAliveI = rand.nextInt(ROW_SIZE);
            int randomAliveJ = rand.nextInt(COL_SIZE);
            
            if(board[randomAliveI][randomAliveJ])
            {
                i--;
            }
            else
            {
                board[randomAliveI][randomAliveJ] = true;
            }
        }
    }
    
    private static void generateBoard()
    {
        
        int aliveCount = 0;
        
        for(int i = 0; i < ROW_SIZE; i++)
        {
            for(int j = 0; j < COL_SIZE; j++)
            {
                if(board[i][j]) continue;
                
                board[i][j] = false;
               
            }
        }
    }
    
    private static void printBoard()
    {
        for(int i = 0; i < ROW_SIZE; i++)
        {
            for(int j = 0; j < COL_SIZE; j++)
            {
                if(board[i][j])
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
