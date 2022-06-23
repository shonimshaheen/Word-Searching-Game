/**
 * Program: A Word Game with approximately 5000 Words
 * - used OOP concepts, data structures [ArrayLists, 2D Arrays], and complex algorithmic functions [recursion]
 * 
 * This is the WordSearchGame Class [Main Class]
 * 
 * Executes the game ~
 * - startGame();
 * - initializeGame();
 * - gamerun();
 * - gamePlay();
 * - playerMove();
 * - findWords();
 * - extractWords();
 * - wordExtractorRowWise();
 * - wordExtractorColumnWise();
 * - printScore();
 * - gameOver();
 * 
 * NOTE: Each Method documentation are written on top its respective code
 * 
 * Text File 5000CommonWords.txt retrieved from: https://github.com/mahsu/IndexingExercise/blob/master/5000-words.txt 
 * - has been modified eventually
 * 
 * @author: Shonim
 * @date: 21/06/2022 [DD/MM/YYYY]
 * @modified: 22/06/2022 [DD/MM/YYYY]
 */ 

// Libraries
import java.util.*;

public class WordSearchGame {
    
    // Player A and Player B, the game surrounds the two human players
    private static Player A, B;
    
    // Generates List: Words of Row and Column of the Grid
    static List<String> rowWiseExtractedWords = new ArrayList<>();
    static List<String> columnWiseExtractedWords = new ArrayList<>();
    
    // this string used in a recursion function to check all possible words: wordExtractorRowWise(); and wordExtractorColumnWise();
    static String temp = new String();
    
    // Scanner for Player input
    private final static Scanner input = new Scanner(System.in);
    
    // Row and Colum that player selects
    private static int selectedRow;
    private static int selectedColumn;
    
    // List for storing all the words that has been implented in the game
    private static List<String> myDictionary = new ArrayList<>();
    
    // Letters/Alphabets that Player choses will be stored in char: Character
    private static char character;
     
    /**
     * MAIN Class Statement 
     * - Executes the game using method: startGame();
     */   
    public static void main(String[] args) {
        startGame();
    }

    /**
     * METHOD: startGame();
     */
    private static void startGame() {
        
        // User-friendly message at the beginning of the game
        System.out.println();
        System.out.println("Happy Gaming! ^_^");
        
        // initalizeGame() method;
        initializeGame();
        
        while (true) {
            // base condition
            if (GameBoard.occupiedCellCount == GameBoard.totalCellCount) {
                gameOver();
                return;
            }
            gameRun();
        }
    }

    /**
     * METHOD: initializeGame();
     * 
     * Used for:
     * - Initalized Grid
     * - Print Initial Score of the Players
     */
    private static void initializeGame() {
        A = new Player("A", 0);
        B = new Player("B", 0);
        GameBoard.initGrid();
        GameBoard.printGrid();
        printScore();
        // All the words in the MyDictonary class has been stored in myDictionary
        myDictionary = MyDictionary.getWords();
    }

    // Turn is set to false, 
    // As it allows to implent the if else statement of player turns in the round
    private static boolean turn = false;

    /**
     * METHOD: gameRun();
     * 
     * Used for: 
     * - give turns to Player A and Player
     * - prints the updated gameBoard, displays current score
     */
    private static void gameRun() {
        
        // turn is set to !turn, so turn is true, meaning it's Player A's turn
        turn = !turn;
        if (turn) {
            gamePlay(A);

        } 
         // Else turn is false, which means it's Player B's turn  
        else {
            gamePlay(B);
        }
        GameBoard.printGrid();
        printScore();
    }

    /**
     * METHOD: gamePlay();
     * 
     * @param player
     * 
     * Used for:
     * - Initalized Grid
     * - Print Initial Score of the Players
     */
    private static void gamePlay(Player player) {
        System.out.println(player.getName() + "'s turn");
        // playerMove(); method
        playerMove();
        if (!GameBoard.isValidMove(selectedRow, selectedColumn)) {
            System.out.println("Invalid cell selected.");
            return;
        } 
        // As player selects row, column and a character, occupiedCell will increment
        GameBoard.grid[selectedRow][selectedColumn] = character;
        GameBoard.occupiedCellCount++;
        Integer score = findWords(player, selectedRow, selectedColumn);
        player.setScore(player.getScore() + score);
    }

    /**
     * METHOD: playerMove();
     * 
     * Menu in each round
     * - asks user for row no., column no. and alphabet/letter
     */
    private static void playerMove() {
        System.out.println();
        System.out.println("Select cell by selecting row and column");
        System.out.println("");
        System.out.print("Row (0 - 9): ");
        selectedRow = input.nextInt();
        System.out.print("Column (0 - 9): ");
        selectedColumn = input.nextInt();
        System.out.println();
        System.out.println("Please chose any value from a - z"); 
        System.out.println("[Capital Letters will not form any word!]");
        System.out.println();
        System.out.print("Character: ");
        character = input.next().charAt(0);
        System.out.println();
    }

    /**
     * METHOD: findWords();
     * 
     * Words will be added after seeing all possible word combinations (Row and Column wise)
     * 
     * @param player
     * @param row
     * @param column
     * @return count
     */
    private static Integer findWords(Player player, int row, int column) {
        
        // extractWords() method;
        extractWords(row, column);

        // stores the total no. of words made
        int count = 0;
        List<String> validWords = new ArrayList<>();
       
        // If dictionary contains the word, it will be counted [ROW WISE]
        for(String str : rowWiseExtractedWords) {
            if(myDictionary.contains(str)) {
                count++;
                validWords.add(str);
            }
        }
        
        // If dictionary contains the word, it will be counted [COLUMN WISE]
        for(String str : columnWiseExtractedWords) {
            if(myDictionary.contains(str)) {
                count++;
                validWords.add(str);
            }
        }
        
        // If valid word is not empty, then the word exists
        // The word that has been made will be displayed
        // Total no. of words will be displayed
        if(!validWords.isEmpty()) {
            System.out.println(player.getName()+" has made: ");
            for(String str: validWords)
                System.out.println(str);
            System.out.println("Total "+count+" word(s)");
        }
        return count;
    }

    /**
     * METHOD: extractWords();
     * 
     * - recursive function used to find all the combination of possible words 
     * [ROW WISE], [COLUMN WISE]
     * 
     * @param row
     * @param column
     */
    private static void extractWords(int row, int column) {
        rowWiseExtractedWords.clear();
        // recursive function wordExtractRowWise();
        wordExtractorRowWise(row, column, column);
        columnWiseExtractedWords.clear();
        // recursive function wordExtractColumnwise();
        wordExtractorColumnWise(column, row, row);
    }

    /**
     * METHOD: wordExtractorRowWise();
     * Recursive function used to find all the combination of possible words [COLUMN WISE]
     * 
     * @param row
     * @param p
     * @param q
     */
    private static void wordExtractorRowWise(int row, int p, int q) {
        
        // The Grid cells are displayed as "-"
        if (p < 0 || q == GameBoard.column || GameBoard.grid[row][p] == '-' || GameBoard.grid[row][q] == '-') {
            return;
        }
        temp = "";
        for (int i = p; i <= q; i++) {
            temp += GameBoard.grid[row][i];
        }
        
        // ROW WISE (left and right cells are checked)
        // Recursive function used to find all the combination of possible words
        if (!rowWiseExtractedWords.contains(temp)) {
            rowWiseExtractedWords.add(temp);
            wordExtractorRowWise(row, p - 1, q);
            wordExtractorRowWise(row, p, q + 1);
        }
    }

    /**
     * METHOD: wordExtractorColumnWise
     * 
     * @param column
     * @param p
     * @param q
     */
    private static void wordExtractorColumnWise(int column, int p, int q) {
        
        // The Grid cells are displayed as "-"
        if(p < 0 || q == GameBoard.row || GameBoard.grid[p][column] == '-' || GameBoard.grid[q][column] == '-') {
            return;
        }
        temp = "";
        for (int i = p; i <= q; i++) {
            temp += GameBoard.grid[i][column];
        }
        
        // COLUMN WISE (top and bottom cells are checked)
        // Recursive function used to find all the combination of possible words
        if (!columnWiseExtractedWords.contains(temp)) {
            columnWiseExtractedWords.add(temp);
            wordExtractorColumnWise(column, p - 1, q);
            wordExtractorColumnWise(column, p, q + 1);
        }
    }

    /**
     * METHOD: printScore();
     * 
     * - prints current scores of players
     */
    private static void printScore() {
        System.out.print(A.getName()+"'s score "+ A.getScore());
        System.out.println(" | "+B.getName()+"'s score "+ B.getScore());
    }

    /**
     * METHOD: gameOver();
     * 
     * Determines the winner based on which player has more score.
     * Displays the final score at the end of the game
     * 
     */
    private static void gameOver() {
        
        // If Player A Score has more score than Player B, Player A wins.
        if (A.getScore() > B.getScore()) {
            System.out.println("The winner is " + A.getName());
        } 
        
        // If Player B Score has more score than Player A, Player B wins.
        else if (A.getScore() < B.getScore()) {
            System.out.println("The winner is " + B.getName());
        } 
        
        // If Player A Score and Player B score is same, game is tie.
        else {
            System.out.println("Game has been tied.");
        }
        
        // User-friendly Fairewell Message
        System.out.println();
        System.out.println("Thank you for playing the game! Hope you enjoyed Player! ^_^");
    }
}
