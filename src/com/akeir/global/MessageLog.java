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
        System.err.print("Starting Game!\n");
    }
    
    public static final void SNAKE_DIRECTION()
    {
        System.err.print("Snake going "+ GlobalParams.SNAKE_DIRECTION + "\n");
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
        System.err.print("GOING FASTER!! 10x\n");
    }
    
    public static final void EXCEPTION(Exception e)
    {
        e.printStackTrace();
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
}
