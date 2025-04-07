package ca.bc.bcit.skunk;

import java.util.*;

/**
 * This class represent each round in game.
 */
public class Round
{
    private final static int DICE_OUTPUT_ONE  = 1;
    private final static int NULL_VALUE       = 0;
    private final static int NUMBER_OF_ROUNDS = 5;
    private final static int FIRST_ROUND      = 1;

    private final List<String> stayCommands;
    private final List<String> riskCommands;
    private final List<Player> currentPlayers;


    /**
     * Constructor creates object and runs the game. Display two random numbers per player and then finds the winner at last.
     */
    Round()
    {
        currentPlayers = new ArrayList<>();
        stayCommands   = new ArrayList<>();
        riskCommands   = new ArrayList<>();

        stayCommands.add("stay");
        stayCommands.add("s");
        stayCommands.add("yes");
        stayCommands.add("y");

        riskCommands.add("risk");
        riskCommands.add("r");
        riskCommands.add("no");
        riskCommands.add("n");

        for(int i = FIRST_ROUND; i <= NUMBER_OF_ROUNDS; i++)
        {
            currentPlayers.addAll(SetUp.ALL_PLAYERS);

            System.out.println("Round " + i + ": \n");

            while(!currentPlayers.isEmpty())
            {
                final Iterator<Player> it;
                it = currentPlayers.iterator();

                while (it.hasNext())
                {
                    //assign the Player to the variable eachPlayer.
                    final Player eachPlayer;
                    eachPlayer = it.next();

                    System.out.printf("%s's tern: \n", eachPlayer.getFirstName());
                    System.out.print("Do you want to stay or risk? ");
                    playerDecision(eachPlayer);

                    if(!eachPlayer.getDecisionToContinue())
                    {
                        System.out.println("Player Removed");
                        it.remove();
                    }
                }

                System.out.println("Let's Start!");
                gamePlay();
            }
            addStats();
            showData(i);
            setNullData();
        }
        findWinner();
    }

    /**
     * Method makes players to play game by asking their decision to continue or not.
     */
    void gamePlay()
    {
        do
        {
            final Iterator<Player> it;
            final Random           randomNumberGenerator;
            final int              diceOutputOne;
            final int              diceOutputTwo;

            randomNumberGenerator = new Random();
            diceOutputOne         = randomNumberGenerator.nextInt(1, 7);
            diceOutputTwo         = randomNumberGenerator.nextInt(1, 7);
            it                    = currentPlayers.iterator();


            if ((diceOutputOne == DICE_OUTPUT_ONE && diceOutputTwo != DICE_OUTPUT_ONE) || (diceOutputTwo == DICE_OUTPUT_ONE && diceOutputOne != DICE_OUTPUT_ONE))
            {
                System.out.println("Output of dice one: " + diceOutputOne);
                System.out.println("Output of dice two: " + diceOutputTwo);

                if (diceOutputOne == DICE_OUTPUT_ONE)
                {
                    System.out.printf("As result of dice one is %d, you all will get %d score for this match and round.\n", DICE_OUTPUT_ONE, NULL_VALUE);
                }
                else
                {
                    System.out.printf("As result of dice two is %d, you all will get %d score for this Round.\n", DICE_OUTPUT_ONE, NULL_VALUE);
                }
                System.out.println("And you are out of the round!");
                setThisRoundScoreNullForAll();
                currentPlayers.clear();

            }
            else if (diceOutputOne == DICE_OUTPUT_ONE && diceOutputTwo == DICE_OUTPUT_ONE)
            {
                System.out.println("Output of dice one: " + diceOutputOne);
                System.out.println("Output of dice two: " + diceOutputTwo);

                System.out.printf("Output of both the dices is %d, so score of the current players till this round in game will be %d.\n", DICE_OUTPUT_ONE, NULL_VALUE);
                System.out.println("And you are out of the round!");

                setThisRoundScoreNullForAll();
                setGameScoreNullForAll();
                currentPlayers.clear();
            }
            else
            {
                System.out.println("Output of dice one: " + diceOutputOne);
                System.out.println("Output of dice two: " + diceOutputTwo);

                setScoreForAll(diceOutputOne, diceOutputTwo);
                System.out.printf("Each Player will get %d score for this Round.\n", (diceOutputOne+ diceOutputTwo));
            }

            while(it.hasNext())
            {
                Player eachPlayer;
                eachPlayer = it.next();
                System.out.printf("%s's turn: \n", eachPlayer.getFirstName());
                System.out.print("Do you want to stay or risk? ");
                playerDecision(eachPlayer);

                if(!eachPlayer.getDecisionToContinue())
                {
                    System.out.println("Player Removed");
                    it.remove();
                    System.out.println("Let's Start!");
                }
            }
        }
        while(!currentPlayers.isEmpty());
    }

    /**
     * Method sets the score of a particular round to zero if dice output is DICE_OUTPUT_ONE
     */
    void setThisRoundScoreNullForAll()
    {
        for(final Player eachPlayer : currentPlayers)
        {
            eachPlayer.setTotalScoreAfterEachRoundNull();
        }
    }

    /**
     * Method sets the score for each player in game to NULL, if output of both the dices is DICE_OUTPUT_ONE
     */
    void setGameScoreNullForAll()
    {
        for(final Player eachPlayer : currentPlayers)
        {
            eachPlayer.setTotalScoreInGameNull();
        }
    }

    /**
     * Method sets the score after winning dice outputs
     * @param diceOutputOne is the output of dice one
     * @param diceOutputTwo is the output of dice two
     */
    void setScoreForAll(final int diceOutputOne ,
                        final int diceOutputTwo)
    {
        for(final Player eachPlayer : currentPlayers)
        {
            eachPlayer.setScoreAfterEachPlay(diceOutputOne + diceOutputTwo);
            eachPlayer.setTotalScoreAfterEachRound(eachPlayer.getScoreAfterEachPlay());
        }
    }

    /**
     * This method changes the decision of the player to continue or not
     * @param player is current whose decision will be changed
     */
    private void playerDecision(final Player player)
    {
        final Scanner scanner;

        int    userInputInt;
        String userInputString;

        scanner = new Scanner(System.in);

        while(true)
        {
            if(scanner.hasNextLine())
            {
                userInputString = scanner.nextLine();

                if(stayCommands.contains(userInputString.toLowerCase()))
                {
                    player.setDecisionToContinue(false);
                    break;
                }
                else if(riskCommands.contains(userInputString.toLowerCase()))
                {
                    player.setDecisionToContinue(true);
                    break;
                }
                else
                {
                    System.out.print("Invalid input. Try again: ");
                }
            }
            else
            {
                userInputInt = scanner.nextInt();
                System.out.printf("Invalid input: %d. Try again: ", userInputInt);
                scanner.nextLine();
            }
            scanner.close();
        }
    }

    /**
     * This method sets whole data to NULL_VALUE for next player to get fresh variables
     */
    void setNullData()
    {
        for(final Player eachPlayer : SetUp.ALL_PLAYERS)
        {
            eachPlayer.setTotalScoreAfterEachRoundNull();
            eachPlayer.setScoreAfterEachPlayNull();
        }
        currentPlayers.clear();
    }

    /**
     * This method shows the stats
     * @param roundNumber is the round which players are playing
     */
    void showData(final int roundNumber)
    {
        for(final Player player : SetUp.ALL_PLAYERS)
        {
            System.out.println(player.getFirstName() + " have total score: " + player.getTotalScoreAfterEachRound() + " for round " + roundNumber);
            System.out.println("Total score of " + player.getFirstName() + " in game is " + player.getTotalScoreInGame());
        }
    }

    /**
     * This method adds the score after each round.
     */
    void addStats()
    {
        for(final Player eachPlayer : SetUp.ALL_PLAYERS)
        {
            eachPlayer.setTotalScoreInGame(eachPlayer.getTotalScoreAfterEachRound());
        }
    }

    /**
     * This method finds the winner among all the current players
     */
    void findWinner()
    {
        int highestScore;
        int indexOfWinner;

        indexOfWinner = NULL_VALUE;
        highestScore = SetUp.ALL_PLAYERS.get(NULL_VALUE).getTotalScoreInGame();

        for(int i = 0; i < SetUp.ALL_PLAYERS.size(); i++)
        {
            if(SetUp.ALL_PLAYERS.get(i).getTotalScoreInGame() > highestScore)
            {
                highestScore = SetUp.ALL_PLAYERS.get(i).getTotalScoreInGame();
                indexOfWinner = i;
            }
        }

        System.out.println(SetUp.ALL_PLAYERS.get(indexOfWinner).getFirstName() + " wins!");
    }
}