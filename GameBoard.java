/**
 * This is the GameBoard Class
 * 
 * Contains private instances, and methods:
 * - stores row value = 10
 * - stores column value = 10
 * - stores totalCellCount, occupiedCellCount
 * - stores character that player has chosen
 * 
 * @author: Shonim
 * @date: 21/06/2022 [DD/MM/YYYY]
 * @modified: 22/06/2022 [DD/MM/YYYY]
 */ 

public class GameBoard {
    
    // Grid 10 x 10 
    public static final int row = 10;
    public static final int column = 10;
    public static final int totalCellCount = row * column;
    public static int occupiedCellCount = 0;
    
    // 2D Array
    public static char[][] grid;

    // Each cells in the grid are displayed as "-" by default
    public static void initGrid() {
        grid = new char[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = '-';
            }
        }
    }

    /**
     * METHOD: printGrid();
     * 
     * Used to display the layout the Grid
     * @return void
     */
    public static void printGrid() {
        System.out.print("#########################");
        System.out.println();
        System.out.print("# # ");
        for (int i = 0; i < column; i++)
            System.out.print(i + " ");
        System.out.print("#");
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print("# "+i+" ");
            for (int j = 0; j < column; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("#");
            System.out.println();
        }
        System.out.print("#########################");
        System.out.println();
    }

    /**
     * METHOD: isValidMove();
     * 
     * Used to check if user has inputed valid number of row and cells
     * 
     * @param r
     * @param c
     * @return Grid Cells if condition is met
     */
    public static boolean isValidMove(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < column && grid[r][c] == '-';
    }

    /**
     * METHOD: occupiedCellCount
     * 
     * Used to check the no. of cells that has been used by the player when a character is placed in the cell
     * 
     * @return occupiedCellCount
     */
    public static int getOccupiedCellCount() {
        return occupiedCellCount;
    }

}
