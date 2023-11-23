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

    public static void main(String[] args) {
        System.out.println("!!! Snake And Ladder Simulator !!!");
// use case 1 , initialize player one at 0
        SnakeAndLadder playerOne = new SnakeAndLadder();
        System.out.println("current position of player one : " + playerOne.CURRENT_POSITION);
    }
}
