/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.global;

/**
 *
 * @author Codeiro
 */
public final class GlobalParams {

    private GlobalParams() { }
    
    public static boolean GAME_STARTED = false;
    public static boolean GAME_CRASHED = false;
    public static boolean IS_SOUND_ENABLED = true;
    public static boolean IS_MUSIC_ENABLED = true;
    
    public static int GAME_SPEED = Constants.INITIAL_GAME_SPEED;
    
    public static String SNAKE_DIRECTION = Constants.DIR_RIGHT;
}
