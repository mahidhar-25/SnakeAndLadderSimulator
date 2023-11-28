package com.bridgelabz.snakeandladder;

import static com.bridgelabz.snakeandladder.SnakeAndLadder.*;
/*
@desc :

# SnakeAndLadderApplication Class
The SnakeAndLadderApplication class is a Java program that serves as a simulator for the Snake and Ladder game. It encapsulates the logic for simulating moves of two players on the game board until one of them reaches the winning position (100).

Functionality:

Initialization:
    Initializes the Snake and Ladder game by calling SnakeAndLadder.initializeSnakesAndLadders(). This method generates random positions for ladders and snakes on the game board.

Player Instances:
    Creates instances of the SnakeAndLadder class for two players, namely playerOne and playerTwo.

Game Simulation:
    Simulates the game by rolling the dice, getting the user's choice for the move, and updating the current position of each player in a loop until one of them reaches or exceeds the final position (100).
    The loop alternates between moves of playerOne and playerTwo.

Outcome Display:
    Prints the winner of the game based on which player reached the final position first.
    Displays the total number of times the dice was rolled by each player.

Usage:
    The main method serves as the entry point to run the Snake and Ladder game simulation.
    Players take turns rolling the dice, making moves, and the game continues until one player reaches or exceeds the final position.

Code Structure:
    The code uses switch-case statements to determine the move of each player and follows a modular approach with well-defined methods in the SnakeAndLadder class for various functionalities.
 */
public class SnakeAndLadderApplication {
    public static void main(String[] args) {
        System.out.println("!!! Snake And Ladder Simulator !!!");
        // use case 1 , initialize player one at 0
        SnakeAndLadder.initializeSnakesAndLadders();
        SnakeAndLadder playerOne = new SnakeAndLadder();
        SnakeAndLadder playerTwo = new SnakeAndLadder();
/*
     // use case 2 , roll the dice and update the current position
        int diceValue = playerOne.rollDice();
     //use case 3 , get choice of user
        String getChoice = playerOne.getUserChoiceForTheMove(diceValue);
        playerOne.updateCurrentPosition(diceValue , getChoice);

        */
        int diceValue;
        String getChoice;
        int move = (int)(Math.random()*2);
        //use case 4,5 , loop until player wins and make sure reaches exact 100
        while(playerOne.CURRENT_POSITION < FINAL_POSITION || playerTwo.CURRENT_POSITION < FINAL_POSITION){
            switch(move){
                case PLAYER_ONE_MOVE -> {
                    System.out.println("Player 1 move : ");
                    diceValue = playerOne.rollDice();
                    getChoice = playerOne.getUserChoiceForTheMove(diceValue);
                    playerOne.updateCurrentPosition(diceValue , getChoice);
                    if(!getChoice.equals(CLIMB_LADDER)){
                        move = PLAYER_TWO_MOVE;
                    }
                }
                case PLAYER_TWO_MOVE -> {
                    System.out.println("Player 2 move : ");
                    diceValue = playerTwo.rollDice();
                    getChoice = playerTwo.getUserChoiceForTheMove(diceValue);
                    playerTwo.updateCurrentPosition(diceValue , getChoice);
                    if(!getChoice.equals(CLIMB_LADDER)){
                        move = PLAYER_ONE_MOVE;
                    }
                }
                default -> System.out.println("No player started the game");
            }
        }
        if(playerOne.CURRENT_POSITION == FINAL_POSITION){
            System.out.println("Player one won the game ");
        }else if(playerTwo.CURRENT_POSITION == FINAL_POSITION){
            System.out.println("Player two won the game");

        }
        //use case 6 , no of times dice rolled
        System.out.println("total no of time dice rolled by player one: " + playerOne.NO_OF_TIMES_DICE_ROLLED);
        System.out.println("total no of time dice rolled by player two : " + playerTwo.NO_OF_TIMES_DICE_ROLLED);
    }
}
