package com.bridgelabz.snakeandladder;

import java.util.*;
/*
@desc :

# SnakeAndLadder Class
The SnakeAndLadder class is a Java program representing a simple implementation of the Snake and Ladder game.
It incorporates the rules and functionalities of the classic game played on a board with numbered squares.

Constants:

INITIAL_POSITION: Represents the starting position of the player.
FINAL_POSITION: Represents the final position or the winning position of the player.
CLIMB_LADDER, DESCEND_SNAKE, STAY_CURRENT_POSITION, WALK_DICE_VALUE: Constants for different movements in the game.
CHOOSE_MOVEMENT, CHOOSE_STAY: Constants used for decision-making during the game.
PLAYER_ONE_MOVE, PLAYER_TWO_MOVE: Constants representing player moves.
LADDERS, SNAKES: HashMaps containing positions of ladders and snakes on the board.
LADDER_SNAKE_POSITIONS: Set containing positions of both ladders and snakes to ensure unique positions.

Constructors:

public SnakeAndLadder(): Initializes a player with the default initial position.
public SnakeAndLadder(int start): Initializes a player with a specified initial position.

Methods:
private static HashMap<Integer, Integer> generateLadderPositions(int min, int max, int count): Generates random positions for ladders on the game board.
private static HashMap<Integer, Integer> generateSnakesPositions(int min, int max, int count): Generates random positions for snakes on the game board.
public static void initializeSnakesAndLadders(): Randomly generates the number of ladders and snakes and their positions on the board.
public int rollDice(): Simulates rolling a dice and returns a random value between 1 and 6.
public void updateCurrentPosition(int diceValue, String movement): Updates the player's current position based on the dice value and the chosen movement.
public String getUserChoiceForTheMove(int diceValue): Determines the player's choice of movement based on the given dice value, current position, and the presence of ladders or snakes.

Usage:
The class can be used to create instances of the Snake and Ladder game, roll the dice, and simulate player movements.
 */
public class SnakeAndLadder {
    public static final int INITIAL_POSITION = 0;
    public static final int FINAL_POSITION = 100;
    public static final String CLIMB_LADDER = "Ladder";
    public static final String DESCEND_SNAKE = "Snake";
    public static final String STAY_CURRENT_POSITION = "Stay";
    public static final String WALK_DICE_VALUE = "Walk dice value";
    public static final int CHOOSE_MOVEMENT = 1;
    public static final int CHOOSE_STAY = 0;
    public static final int PLAYER_ONE_MOVE = 0;
    public static final int PLAYER_TWO_MOVE = 1;
    public int CURRENT_POSITION ;
    public int NO_OF_TIMES_DICE_ROLLED;
    private static HashMap<Integer, Integer> LADDERS;
    private static HashMap<Integer, Integer> SNAKES;
    public static Set<Integer> LADDER_SNAKE_POSITIONS;

    /*
    @desc : constructor , initializes the player with initial position
    @params : no params
    @return : no return
    */

    public SnakeAndLadder() {
        CURRENT_POSITION = INITIAL_POSITION;
    }

    /*
      @desc: Generates the positions of ladders on the game board.
             Randomly selects positions for ladders based on the specified count within the given range.
      @params:
        - min: The minimum position on the board.
        - max: The maximum position on the board.
        - count: The number of snakes to be generated.
      @return: A HashMap representing the positions of ladders, where the key is the start position and the value is the end position.
     */
    private static HashMap<Integer, Integer> generateLadderPositions(int min, int max, int count) {
         LADDER_SNAKE_POSITIONS = new HashSet<>();
        HashMap<Integer, Integer>ladders = new HashMap<>();
        while (LADDER_SNAKE_POSITIONS.size() < count) {
            int startPosition = getRandomValueInRange(min, max);
            int endPosition = getRandomValueInRange(startPosition + 1, FINAL_POSITION);
            ladders.put(startPosition, endPosition);
            LADDER_SNAKE_POSITIONS.add(startPosition);
        }

        return ladders;
    }

    /*
      @desc: Generates the positions of snakes on the game board.
             Randomly selects positions for snakes based on the specified count within the given range.
      @params:
        - min: The minimum position on the board.
        - max: The maximum position on the board.
        - count: The number of snakes to be generated.
      @return: A HashMap representing the positions of snakes, where the key is the start position and the value is the end position.
     */
    private static HashMap<Integer, Integer> generateSnakesPositions(int min, int max, int count) {
        LADDER_SNAKE_POSITIONS = new HashSet<>();
        HashMap<Integer, Integer>snakes = new HashMap<>();
        while (LADDER_SNAKE_POSITIONS.size() < count) {
            int startPosition = getRandomValueInRange(min, max);
            int endPosition = getRandomValueInRange(INITIAL_POSITION, startPosition-1);
            snakes.put(startPosition, endPosition);
            LADDER_SNAKE_POSITIONS.add(startPosition);
        }

        return snakes;
    }
    /*
      @desc: Initializes the snakes and ladders on the game board.
            Randomly generates the number of ladders and snakes, and their positions on the board.
      @params: No explicit parameters.
      @return: No explicit return value.
     */
    public static void initializeSnakesAndLadders(){
        int noOfLadders = getRandomValueInRange(1 , 25);
        int noOfSnakes = getRandomValueInRange(1 , 15);
        LADDERS = generateLadderPositions(INITIAL_POSITION+1 , FINAL_POSITION , noOfLadders);
        SNAKES = generateSnakesPositions(INITIAL_POSITION , FINAL_POSITION-1 , noOfSnakes + noOfLadders);
        System.out.println("Ladders are : ");
        for (Map.Entry<Integer, Integer> e : LADDERS.entrySet())
            System.out.println("Key: " + e.getKey()+ " Value: " + e.getValue());
        System.out.println("snakes are : ");
        for (Map.Entry<Integer, Integer> e : SNAKES.entrySet())
            System.out.println("Key: " + e.getKey()+ " Value: " + e.getValue());

    }
    /*
 @desc : constructor , initializes the player with initial position
 @params : integer to assign the current position
 @return : no return
 */
    public SnakeAndLadder(int start) {
        if (start > 0 && start <= FINAL_POSITION) {
            CURRENT_POSITION = start;
        } else {
            CURRENT_POSITION = INITIAL_POSITION;
        }
    }

    /*
    @desc : it rolls the dice and generates a random value from 1 to 6
    @param : no params required
    @return : return an integer value of dice rolled
     */
    public int rollDice(){
        return (int)(Math.random()*6) + 1;
    }

    /*
      @desc: Updates the current position based on the provided dice value and movement type.
      @param: diceValue - the value obtained from rolling the dice.
              Movement - the type of movement (CLIMB_LADDER, DESCEND_SNAKE, WALK_DICE_VALUE).
      @return: No explicit return value.
     */
    public void updateCurrentPosition(int diceValue , String Movement){
        NO_OF_TIMES_DICE_ROLLED++;
        switch (Movement){
            case CLIMB_LADDER -> {
                CURRENT_POSITION = LADDERS.get(CURRENT_POSITION+diceValue);
                System.out.println("I took Ladder my position current position is " + CURRENT_POSITION);
            }
            case DESCEND_SNAKE -> {
                CURRENT_POSITION = SNAKES.get(CURRENT_POSITION+diceValue);
                System.out.println("I took Snake my position current position is " + CURRENT_POSITION);
            }
            case WALK_DICE_VALUE -> {
                CURRENT_POSITION += diceValue;
                System.out.println("I took Dice Value my position current position is " + CURRENT_POSITION);
            }
            default -> {
                System.out.println("No movement has happened");
            }
        }
    }


    /*
      @desc: Generates a random integer value within the specified range (inclusive).
      @param: start - the lower bound of the range.
              end - the upper bound of the range.
      @return: An integer representing a random value within the specified range.
     */
    public static int getRandomValueInRange(int start, int end){
        return ((int)(Math.random()*(end - start)) + start);
    }

    /*
     @desc: Determines the player's choice of movement based on the given dice value and current position.
             The method considers the position on the board, random decision-making, and the presence of ladders or snakes.
     @param: diceValue - an integer representing the value rolled on the dice.
     @return: A string indicating the player's choice of movement. Possible values are:
              - CLIMB_LADDER: Player chooses to climb a ladder.
              - DESCEND_SNAKE: Player chooses to descend a snake.
              - STAY_CURRENT_POSITION: Player chooses to stay at the current position.
              - WALK_DICE_VALUE: Player chooses to move forward by the dice value.
     */
    public String getUserChoiceForTheMove(int diceValue){
        int newPosition = CURRENT_POSITION + diceValue;
        System.out.println("current position : " + CURRENT_POSITION + " , dice value : " + diceValue);
        if(newPosition > FINAL_POSITION){
            System.out.println("new position is greater than 100");
            return STAY_CURRENT_POSITION;
        }else if(newPosition == FINAL_POSITION){
            System.out.println("new position is  100");
            return WALK_DICE_VALUE;
        }
        int chooseOne = (int)(Math.random()*2);
        if(LADDER_SNAKE_POSITIONS.contains(newPosition)){
            if (chooseOne == CHOOSE_MOVEMENT) {
                if (LADDERS.containsKey(newPosition)) {
                    return CLIMB_LADDER;
                }
                return DESCEND_SNAKE;
            }
            return WALK_DICE_VALUE;
        }else{
            if(chooseOne == CHOOSE_MOVEMENT){
                return WALK_DICE_VALUE;
            }else{
                return STAY_CURRENT_POSITION;
            }

        }
    }

}
