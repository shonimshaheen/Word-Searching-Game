/**
 * This is the Player Class
 * 
 * Contains constructor, and private instances, and methods:
 * - stores player name
 * - stores score of the player
 * 
 * @author: Shonim
 * @date: 21/06/2022 [DD/MM/YYYY]
 * @modified: 22/06/2022 [DD/MM/YYYY]
 */ 

public class Player {
    
    // Private Instances
    private String name;
    private Integer score;

    /**
     * Constructor to Player information 
     * - given the name of the player, score of player
     * 
     * @param name Name of Player
     * @param score Score of Player
     */
    public Player(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Method getName();
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method setName();
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * method getScore();
     * 
     * @return
     */
    public Integer getScore() {
        return score;
    }

    /**
     * method setScore();
     * 
     * @param score
     */
    public void setScore(Integer score) {
        this.score = score;
    }
}