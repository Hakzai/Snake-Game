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
import com.akeir.helper.Utils;
import com.akeir.model.SnakePiece;
import com.akeir.resources.controllers.FileController;
import com.akeir.resources.controllers.MusicController;
import com.akeir.resources.controllers.SoundController;
import java.util.LinkedList;
import java.util.Queue;
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
    public static Queue<String> MOVE_QUEUE = new LinkedList<>();
    
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
        GlobalParams.GAME_SPEED = Constants.INITIAL_GAME_SPEED;
    }
    
    @Override
    public void handle(long now) 
    {
        time += 0.005 * GlobalParams.GAME_SPEED;

        if (time > 0.1) 
        {
            executeAnimation();
            time = Constants.ZERO_AS_DOUBLE;
        }
    }
    
    private void executeAnimation()
    {
        if(!GlobalParams.GAME_STARTED)
        {
            return;
        }
        
        setSnakeLastPosition();
        moveSnakeHead();
        moveSnakeBody();
        handleCollisions();
        counter.updateSpeedCount();
    }
    
    private void setSnakeLastPosition() 
    {
        snakeLastPosX = snakeHead.getLayoutX();
        snakeLastPosY = snakeHead.getLayoutY();
    }
    
    private void moveSnakeHead() 
    {
        checkMoveQueue();
        
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
        
        SoundController.play().snakeMove();
    }
    
    private void checkMoveQueue()
    {
        if(!MOVE_QUEUE.isEmpty() && isValidMove(MOVE_QUEUE.peek()))
        {
            GlobalParams.SNAKE_DIRECTION = MOVE_QUEUE.poll();
            MessageLog.SNAKE_DIRECTION();
        }
        else if(!MOVE_QUEUE.isEmpty())
        {
            MOVE_QUEUE.poll();
            MessageLog.INVALID_MOVE();
        }
    }
    
    private boolean isValidMove(String key)
    {
        if(key.equals(Constants.DIR_RIGHT) && GlobalParams.SNAKE_DIRECTION.equals(Constants.DIR_LEFT))
        {
            return false;
        }
        else if(key.equals(Constants.DIR_LEFT) && GlobalParams.SNAKE_DIRECTION.equals(Constants.DIR_RIGHT))
        {
            return false;
        }
        else if(key.equals(Constants.DIR_DOWN) && GlobalParams.SNAKE_DIRECTION.equals(Constants.DIR_UP))
        {
            return false;
        }
        else if(key.equals(Constants.DIR_UP) && GlobalParams.SNAKE_DIRECTION.equals(Constants.DIR_DOWN))
        {
            return false;
        }
        else
        {
            return true;
        }
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
        SoundController.play().snakeEatsFood();
        
        spawnFood();
        SnakePiece snakeBody = new SnakePiece(snakeHead, snakeLastPosX, snakeLastPosY);
        snake.add(snakeBody);
    }
    
        private void spawnFood()
    {
        Circle foodTest = new Circle();
        
        foodTest.setLayoutX(Utils.GET_RANDOM_POS_X());
        foodTest.setLayoutY(Utils.GET_RANDOM_POS_Y());
        
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
        SoundController.play().deathSound();
        MusicController.get().stopMusic();
        this.stop();
        
        changeSnakeHeadToDead();
        counter.handleDeathCount();
        
        ((Button) elementsHolder.getElementsMap().get("btnStart")).setVisible(true);
        ((Label) elementsHolder.getElementsMap().get("lbDead")).setVisible(true);
        
        FileController.get().saveScore(((Label) elementsHolder.getElementsMap().get("lbHighScoreCount")).getText());
    }
    
    private void changeSnakeHeadToDead()
    {
        SnakePiece snakeDead = new SnakePiece((Rectangle) elementsHolder.getElementsMap().get("snakeDead"), snakeHead.getLayoutX(), snakeHead.getLayoutY());
        snake.set(1, snakeDead);
        snakeDead.toFront();
    }
}    
