/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.global;

import com.akeir.global.GlobalParams;
import com.akeir.model.Food;
import com.akeir.model.SnakePiece;

/**
 *
 * @author Codeiro
 */
public final class MessageLog {
    
    private MessageLog() { }
    
    public static final void INITIALIZE()
    {
        System.err.print("Initializing Engine...\n");
    }
    
    public static final void STARTING()
    {
        System.err.print("Starting Game!\n");
    }
    
    public static final void ALREADY_STARTED()
    {
        System.err.print("Game Already Started!\n");
    }
    
    public static final void SNAKE_DIRECTION()
    {
        System.err.print("Snake going " + GlobalParams.SNAKE_DIRECTION + "\n");
    }
    
    public static final void RESPAWN_FOOD()
    {
        System.err.print("INVALID FOOD POSITION! Respawning...\n");
    }
    
    public static final void LOG_POSITION(SnakePiece snakeHead, Food food) 
    {
        System.out.print("Snake pos X: " + snakeHead.getLayoutX() + " Y: " + snakeHead.getLayoutY() + "\t");
        System.out.print("Food pos X: " + food.getLayoutX() + " Y: " + food.getLayoutY() + "\n");
    }
    
    public static final void INVALID_MOVE()
    {
        System.err.print("NOT A VALID MOVE!!\n");
    }
    
    public static final void GOTCHA()
    {
        System.err.print("GOTCHA!!!\n");
    }

    public static final void FAST_FORWARD()
    {
        System.err.print("GOING FASTER!! +1x\n");
    }
    
    public static final void ENABLE_MUSIC()
    {
        System.err.print("BG MUSIC HAS BEEN ENABLED!\n");
    }
    
    public static final void DISABLE_MUSIC()
    {
        System.err.print("BG MUSIC HAS BEEN DISABLED!\n");
    }
    
    public static final void ENABLE_SOUND()
    {
        System.err.print("SOUND FX HAS BEEN ENABLED!\n");
    }
    
    public static final void DISABLE_SOUND()
    {
        System.err.print("SOUND FX HAS BEEN DISABLED!\n");
    }
    
    public static final void EXCEPTION(Exception e)
    {
        e.printStackTrace();
    }
    
    public static final void PAUSE()
    {
        System.err.print("GAME IS NOW PAUSED\n");
    }
    
    public static final void RESUME()
    {
        System.err.print("GAME IS RESUMING\n");
    }
    
    public static final void STOP()
    {
        System.err.print("Stopping Engine...\n");
        System.err.print("Please Close the window and Re-start!\n");
    }
    
    public static final void GAME_OVER()
    {
        System.err.print("GAME OVER!!\n");
    }
    
    public static final void SAVE_HIGH_SCORE()
    {
        System.err.print("Saving High Score...\n");
    }
    
    public static final void LOAD_HIGH_SCORE()
    {
        System.err.print("Loading High Score...\n");
    }
    
    public static final void FILE_NOT_FOUND_ERROR()
    {
        System.err.print("File does not exist! Creating new save...\n");
    }
    
    public static final void FILE_READ_ERROR()
    {
        System.err.print("Not able to read file!\n");
    }
    
    public static void CLOSE_GAME_MESSAGE()
    {
        System.err.print("\n** Thank you for Playing!! **\n");
        System.err.print("** Visit: www.github.com/hakzai **\n");
        System.err.print("** Game by Isaac Alencar **\n");
    }
}
