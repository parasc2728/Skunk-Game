package ca.bc.bcit.skunk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is the set-up of the skunk game. It creates the number of the Players and add them into a List.
 */
class SetUp
{
    final static List<Player> ALL_PLAYERS = new ArrayList<>();

    /**
     * Constructor creates the list of all the players in the game.
     */
    SetUp()
    {
        final Scanner scanner;
        final int     numberOfPlayers;

        String falseInput;

        scanner = new Scanner(System.in);

        while(true)
        {
            System.out.print("Enter number of players who will be playing skunk game: ");

            if(scanner.hasNextInt())
            {
                numberOfPlayers = scanner.nextInt();
                scanner.nextLine();
                break;
            }
            else
            {
                falseInput = scanner.nextLine();
                System.out.printf("Invalid input: %s. Please enter a valid number of players! try again. ", falseInput);
            }
        }

        //add all the players to the list.
        addPlayers(numberOfPlayers);

        //shows the created list to the user.
        showPlayers();
    }

    /**
     * This method adds all the players to the List of all the players.
     */
    private void addPlayers(final int numberOfPlayers)
    {
        for(int i = 1; i <= numberOfPlayers; i++)
        {
            System.out.println("Player " + i + ": ");

            final Player player;
            player = new Player();
            ALL_PLAYERS.add(player);
        }
    }

    /**
     * This method shows all the players to the user.
     */
    private void showPlayers()
    {
        int i;
        i = 1;

        for(final Player eachPlayer : ALL_PLAYERS)
        {
            System.out.println("Player " + (i++) + ": " + eachPlayer.getFirstName() + " " + eachPlayer.getLastName());
        }
    }
}