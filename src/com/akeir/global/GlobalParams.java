/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.global;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Codeiro
 */
public class GlobalParams {

    private GlobalParams() { }
    
    public static boolean GAME_STARTED = false;
    
    public static int GAME_SPEED = Constants.ONE_AS_INTEGER;
    
    public static String SNAKE_DIRECTION = Constants.DIR_RIGHT;
    
    public static double GET_RANDOM_POS_X() 
    {
        int position = ThreadLocalRandom.current().nextInt(Constants.ZERO_AS_INTEGER, (int) (Constants.PANE_SNAKE_WIDTH/Constants.SCENE_BLOCK_SIZE));
        return position * Constants.SCENE_BLOCK_SIZE;
    }
    
    public static double GET_RANDOM_POS_Y() 
    {
        int position = ThreadLocalRandom.current().nextInt(Constants.ZERO_AS_INTEGER, (int) (Constants.PANE_SNAKE_HEIGHT/Constants.SCENE_BLOCK_SIZE));
        return position * Constants.SCENE_BLOCK_SIZE;
    }
}
