package com.bridgelabz.SnakeAndLadderSimulator;

public class SnakeAndLadder {
    public static final int INITIAL_POSITION = 0;
    public static final int FINAL_POSITION = 100;
    public int CURRENT_POSITION ;

    /*
    @desc : constructor , initializes the player with initial position
    @params : no params
    @return : no return
    */

    public SnakeAndLadder() {
        CURRENT_POSITION = INITIAL_POSITION;
    }
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
   @desc : it updates the current position , after dice rolled
   @param : integer value is passed rolling the dice
   @return : it won't return , it updates the position
    */
    public void updateCurrentPosition(int diceValue){
        CURRENT_POSITION += diceValue;
    }

    public static void main(String[] args) {
        System.out.println("!!! Snake And Ladder Simulator !!!");
     // use case 1 , initialize player one at 0
        SnakeAndLadder playerOne = new SnakeAndLadder();
        System.out.println("current position of player one : " + playerOne.CURRENT_POSITION);

     // use case 2 , roll the dice and update the current position
        int diceValue = playerOne.rollDice();
        playerOne.updateCurrentPosition(diceValue);
        System.out.println("current position of player one : " + playerOne.CURRENT_POSITION);
    }
}
