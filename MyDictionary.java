/**
 * This is the MyDictionary Class
 * 
 * Used for file-reading
 * - connects 5000CommonWords.txt to MyDictionary Class
 * - used in main class to implement the 5000 words in the game
 * 
 * @author: Shonim
 * @date: 21/06/2022 [DD/MM/YYYY]
 * @modified: 22/06/2022 [DD/MM/YYYY]
 */ 

// Libraries 
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MyDictionary {
    public static List<String> getWords()
    {
        // Connects the text file to MyDictionary Class
        // Paste the file path of the text file accordingly
        List l = readFileInList("5000CommonWords.txt");
        return l;
    }
    public static List<String> readFileInList(String fileName) {

        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
