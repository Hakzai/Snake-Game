/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.controller;

import com.akeir.global.GlobalParams;
import com.akeir.global.Constants;
import com.akeir.helper.ElementsHolder;
import com.akeir.model.Food;
import com.akeir.global.MessageLog;
import com.akeir.model.SnakePiece;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Codeiro
 */
public final class AnimationController extends AnimationTimer {
    
    private double time = Constants.ZERO_AS_DOUBLE;
    
    private double snakeLastPosX;
    private double snakeLastPosY;
    
    private ObservableList<Node> snake;
    private ElementsHolder elementsHolder;
    private CountersController counter;
    private Food food;
    private SnakePiece snakeHead;
    
    public AnimationController(ObservableList<Node> snakeList, ObservableList<Node> elements)
    {
        this.snake = snakeList;
        
        elementsHolder = new ElementsHolder(elements);
        counter = new CountersController(elementsHolder);
        
        setBaseElements();
    }
    
    private void setBaseElements()
    {
        this.snakeHead = (SnakePiece) snake.get(1);
        this.food = (Food) snake.get(0);
    }
    
    @Override
    public void handle(long now) 
    {
        time += 0.005 * GlobalParams.GAME_SPEED;

        if (time > 0.1) 
        {
            startAnimation();
            time = Constants.ZERO_AS_DOUBLE;
        }
    }
    
    public void startAnimation()
    {
        if(!GlobalParams.GAME_STARTED)
        {
            return;
        }
        
        setSnakeLastPosition();
        moveSnakeHead();
        moveSnakeBody();
        handleCollisions();
    }
    
    private void setSnakeLastPosition() 
    {
        snakeLastPosX = snakeHead.getLayoutX();
        snakeLastPosY = snakeHead.getLayoutY();
    }
    
    private void moveSnakeHead() 
    {
        switch (GlobalParams.SNAKE_DIRECTION) 
        {
            case Constants.DIR_RIGHT:
                snakeHead.setLayoutX(snakeLastPosX+Constants.SCENE_BLOCK_SIZE);
                break;
            case Constants.DIR_LEFT:
                snakeHead.setLayoutX(snakeLastPosX-Constants.SCENE_BLOCK_SIZE);
                break;
            case Constants.DIR_DOWN:
                snakeHead.setLayoutY(snakeLastPosY+Constants.SCENE_BLOCK_SIZE);
                break;
            case Constants.DIR_UP:
                snakeHead.setLayoutY(snakeLastPosY-Constants.SCENE_BLOCK_SIZE);
                break;
        }
        
        MessageLog.LOG_POSITION(snakeHead, food);
    }

    private void moveSnakeBody() 
    {
        double currentPosX;
        double currentPosY;
            
        for(int i=2; i<snake.size(); i++)
        {
            currentPosX = snake.get(i).getLayoutX();
            currentPosY = snake.get(i).getLayoutY();

            snake.get(i).setLayoutX(snakeLastPosX); 
            snake.get(i).setLayoutY(snakeLastPosY);

            snakeLastPosX = currentPosX;
            snakeLastPosY = currentPosY;
        }
    }
    
    private void handleCollisions()
    {
        if(CollisionController.getInstance().isCollideFood(snakeHead, food.getLayoutX(), food.getLayoutY()))
        {
            handleGrowSnake();
            handleScoreCount();
        }
        else if(CollisionController.getInstance().isCollideSnake(snakeHead, snake) || CollisionController.getInstance().isCollideWall(snakeHead))
        {
            handleDeath();
        }
    }
    
    private void handleGrowSnake()
    {
        MessageLog.GOTCHA();
        
        spawnFood();
        SnakePiece snakeBody = new SnakePiece(snakeHead, snakeLastPosX, snakeLastPosY);
        snake.add(snakeBody);
    }
    
        private void spawnFood()
    {
        Circle foodTest = new Circle();
        
        foodTest.setLayoutX(GlobalParams.GET_RANDOM_POS_X());
        foodTest.setLayoutY(GlobalParams.GET_RANDOM_POS_Y());
        
        if(isValidFoodPosition(foodTest))
        {
            food.setLayoutX(foodTest.getLayoutX()+12.5);
            food.setLayoutY(foodTest.getLayoutY()+12.5);
        }
        else
        {
            spawnFood();
        }
        
    }
    
    private boolean isValidFoodPosition(Circle foodTest)
    {
        for(int i=1; i<snake.size(); i++)
        {
            if(foodTest.getLayoutX() == snake.get(i).getLayoutX() && foodTest.getLayoutY() == snake.get(i).getLayoutY())
            {
                return false;
            }
        }
        
        return true;
    }
    
    private void handleScoreCount() 
    {
        counter.handleScore();
        counter.checkToIncreaseSpeed();
    }
    
    private void handleDeath()
    {
        GlobalParams.GAME_STARTED = false;
        MessageLog.GAME_OVER();
        this.stop();
        
        changeSnakeHeadToDead();
        counter.handleDeathCount();
        
        ((Button) elementsHolder.getElementsMap().get("btnStart")).setVisible(true);
        ((Label) elementsHolder.getElementsMap().get("lbDead")).setVisible(true);
    }
        
    private void changeSnakeHeadToDead()
    {
        SnakePiece snakeDead = new SnakePiece((Rectangle) elementsHolder.getElementsMap().get("snakeDead"), snakeHead.getLayoutX(), snakeHead.getLayoutY());
        snake.set(1, snakeDead);
        snakeDead.toFront();
    }
}    
