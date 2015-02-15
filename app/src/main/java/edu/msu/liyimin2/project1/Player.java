package edu.msu.liyimin2.project1;

import java.util.ArrayList;

public class Player {
    //region "Member variables"

    /**
     * Player name
     */
    public String name = "player1";

    /**
     * Score
     */
    public int score;

    /**
     * List of available birds to use
     */
    public ArrayList<Bird> birdList = new ArrayList<Bird>();

    //endregion

    //region "Constructor"

    public Player()
    {
        score = 0;
    }

    //endregion
}
