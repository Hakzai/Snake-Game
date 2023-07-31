/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.controller;

import com.akeir.global.Constants;
import com.akeir.model.SnakePiece;
import com.akeir.resources.controllers.SoundController;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 *
 * @author Codeiro
 */
public final class CollisionController {
    
    private CollisionController() { }
    
    private static CollisionController instance;
    
    public static CollisionController getInstance()
    {
        if(null == instance)
        {
            instance = new CollisionController();
        }
        
        return instance;
    }
    
    public boolean isCollideFood(SnakePiece snakeHead, double foodCurrPosX, double foodCurrPosY)
    {
        return snakeHead.getLayoutX() == foodCurrPosX-(snakeHead.getWidth()/Constants.TWO_AS_INTEGER) // DIVIDE BY TWO BECAUSE OF RADIUS INSTEAD OF LAYOUT X/Y
                && snakeHead.getLayoutY() == foodCurrPosY-(snakeHead.getHeight()/Constants.TWO_AS_INTEGER);
    }
    
    public boolean isCollideSnake(SnakePiece snakeHead, ObservableList<Node> snakeBody)
    {
        for(int i=2; i<snakeBody.size(); i++)
        {
            if(snakeHead.getLayoutX() == snakeBody.get(i).getLayoutX() && snakeHead.getLayoutY() == snakeBody.get(i).getLayoutY())
            {
                SoundController.play().snakeCollideBody();
                return true;
            }
        }
        return false;
    }
    
    public boolean isCollideWall(SnakePiece snakeHead)
    {
        if(Constants.ZERO_AS_DOUBLE > snakeHead.getLayoutX() 
                || snakeHead.getLayoutX() > Constants.PANE_SNAKE_WIDTH-Constants.ONE_AS_INTEGER // MINUS ONE TO MARK THE COLLISION FOR WALL
                || Constants.ZERO_AS_DOUBLE > snakeHead.getLayoutY() 
                || snakeHead.getLayoutY() > Constants.PANE_SNAKE_HEIGHT-Constants.ONE_AS_INTEGER)
        {
            SoundController.play().snakeCollideWall();
            return true;
        }
        
        return false;
    }
    
}
