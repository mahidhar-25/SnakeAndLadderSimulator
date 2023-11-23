package com.bridgelabz.SnakeAndLadderSimulator;

import java.util.*;

public class SnakeAndLadder {
    public static final int INITIAL_POSITION = 0;
    public static final int FINAL_POSITION = 100;
    public static final String CLIMB_LADDER = "Ladder";
    public static final String DESCEND_SNAKE = "Snake";
    public static final String STAY_CURRENT_POSITION = "Stay";
    public static final String WALK_DICE_VALUE = "Walk dice value";
    public static final int CHOOSE_MOVEMENT = 1;
    public static final int CHOOSE_STAY = 0;
    public int CURRENT_POSITION ;
    private HashMap<Integer, Integer> LADDERS;
    private HashMap<Integer, Integer> SNAKES;
    public Set<Integer> LADDER_SNAKE_POSITIONS;

    /*
    @desc : constructor , initializes the player with initial position
    @params : no params
    @return : no return
    */

    public SnakeAndLadder() {
        CURRENT_POSITION = INITIAL_POSITION;
        initializeSnakesAndLadders();
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
    private HashMap<Integer, Integer> generateLadderPositions(int min, int max, int count) {
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
    private HashMap<Integer, Integer> generateSnakesPositions(int min, int max, int count) {
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
    private void initializeSnakesAndLadders(){
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
    public SnakeAndLadder(int start) {
        if (start > 0 && start <= FINAL_POSITION) {
            CURRENT_POSITION = start;
        } else {
            CURRENT_POSITION = INITIAL_POSITION;
        }
        initializeSnakesAndLadders();
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
    public int getRandomValueInRange(int start , int end){
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
    public static void main(String[] args) {
        System.out.println("!!! Snake And Ladder Simulator !!!");
     // use case 1 , initialize player one at 0
        SnakeAndLadder playerOne = new SnakeAndLadder();
        System.out.println("current position of player one : " + playerOne.CURRENT_POSITION);

     // use case 2 , roll the dice and update the current position
        int diceValue = playerOne.rollDice();
     //use case 3 , get choice of user
        String getChoice = playerOne.getUserChoiceForTheMove(diceValue);
        playerOne.updateCurrentPosition(diceValue , getChoice);
       //use case 4,5 , loop until player wins and make sure reaches exact 100
        while(playerOne.CURRENT_POSITION < FINAL_POSITION){
            diceValue = playerOne.rollDice();
            getChoice = playerOne.getUserChoiceForTheMove(diceValue);
            playerOne.updateCurrentPosition(diceValue , getChoice);
        }
        if(playerOne.CURRENT_POSITION == FINAL_POSITION){
            System.out.println("won the game ");
        }
    }
}
