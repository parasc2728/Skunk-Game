package ca.bc.bcit.skunk;

import java.util.Scanner;

/**
 * This class takes user input and creates players.
 */
class Player
{
    private final String  firstName;
    private final String  lastName;

    private int     scoreAfterEachPlay;
    private int     totalScoreAfterEachRound;
    private int     totalScoreInGame;
    private boolean decisionToContinue;

    private final static int NULL_VALUE = 0;

    /**
     * Constructor creates a Player and assign the default decision of player to True.
     */
    Player()
    {
        final Scanner scanner;

        int     userIntInput;
        long    userLongInput;

        scanner = new Scanner(System.in);

        //Initialization of First Name.
        while(true)
        {
            System.out.print("Enter your first name: ");
            if(scanner.hasNextInt())
            {
                userIntInput = scanner.nextInt();
                System.out.printf("Invalid input: %d. Please try again. " , userIntInput);
                scanner.nextLine();
            }
            else if(scanner.hasNextLong())
            {
                userLongInput = scanner.nextLong();
                System.out.printf("Invalid input: %d. Please try again. ", userLongInput);
                scanner.nextLine();
            }
            else
            {
                this.firstName = scanner.nextLine();
                break;
            }
        }

        //Initialization of last name.
        while(true)
        {
            System.out.print("Enter your last name: ");
            if(scanner.hasNextInt())
            {
                userIntInput = scanner.nextInt();
                System.out.printf("Invalid input: %d. Please try again. ", userIntInput);
                scanner.nextLine();
            }
            else if(scanner.hasNextLong())
            {
                userLongInput = scanner.nextLong();
                System.out.printf("Invalid input: %d. Please try again. ", userLongInput);
                scanner.nextLine();
            }
            else
            {
                this.lastName = scanner.nextLine();
                break;
            }
        }

        //Initializing Player's decision to play.
        decisionToContinue =  true;
    }

    /**
     * This method returns the First Name of the Player
     *
     * @return first name of the player
     */
    String getFirstName()
    {
        return firstName;
    }

    /**
     * This method returns the Last Name of the Player
     *
     * @return last name of the player
     */
    String getLastName()
    {
        return lastName;
    }

    /**
     * This method returns the score of the player after each of the Play/ Outcome
     *
     * @return score of the player after each Dice roll out.
     */
    int getScoreAfterEachPlay()
    {
        return scoreAfterEachPlay;
    }

    /**
     * This method returns the final score of the player after end of the round
     *
     * @return final score of the player after the round
     */
    int getTotalScoreAfterEachRound()
    {
        return totalScoreAfterEachRound;
    }

    /**
     * This method returns the total score of the player in game
     *
     * @return total score of the player in game
     */
    int getTotalScoreInGame()
    {
        return totalScoreInGame;
    }

    /**
     * This method returns the decision of the player to continue or not
     *
     * @return decision of the player
     */
    boolean getDecisionToContinue()
    {
        return decisionToContinue;
    }

    /**
     * This method modifies the scoreAfterEachPlay.
     */
    void setScoreAfterEachPlay(final int combinedOutOfTwoDice)
    {
        scoreAfterEachPlay = combinedOutOfTwoDice;
    }

    /**
     * This method sets the scoreAfterEachPlay to null for another player or for another match.
     */
    void setScoreAfterEachPlayNull()
    {
        scoreAfterEachPlay = NULL_VALUE;
    }

    /**
     * This method sets the decision of the player to continue or not.
     */
    void setDecisionToContinue(final boolean playerDecision)
    {
        decisionToContinue = playerDecision;
    }

    /**
     * This method add the current to the TotalScoreAfterEachRound.
     */
    void setTotalScoreAfterEachRound(final int scoreToAdd)
    {
        totalScoreAfterEachRound = totalScoreAfterEachRound + scoreToAdd;
    }

    /**
     * This method sets the scoreAfterEachRound to null for another round.
     */
    void setTotalScoreAfterEachRoundNull()
    {
        totalScoreAfterEachRound = NULL_VALUE;
    }

    /**
     * This method adds the Score after each round to totalScoreInGame.
     */
    void setTotalScoreInGame(final int scoreToAdd)
    {
        totalScoreInGame = totalScoreInGame + scoreToAdd;
    }

    /**
     * This method sets the value totalScoreInGame to null.
     */
    void setTotalScoreInGameNull()
    {
        totalScoreInGame = NULL_VALUE;
    }
}