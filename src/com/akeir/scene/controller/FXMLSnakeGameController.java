/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.scene.controller;

import com.akeir.controller.AnimationController;
import com.akeir.global.GlobalParams;
import com.akeir.global.Constants;
import com.akeir.model.Food;
import com.akeir.global.MessageLog;
import com.akeir.model.SnakePiece;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class FXMLSnakeGameController implements Initializable {
    
    private AnimationTimer timer;
    
    @FXML
    private AnchorPane apMain;
    
    @FXML
    private Label lbTitle;

    @FXML
    private Pane paneSnake;

    @FXML
    private Rectangle snakeTemplate;
    
    private Rectangle snakeHead;
    
    @FXML
    private Rectangle snakeDead;
    
    @FXML
    private Circle foodTemplate;

    @FXML
    private Button btnStart;

    @FXML
    private Label lbDead;
    
    @FXML
    private Label lbScore;

    @FXML
    private Label lbScoreCount;

    @FXML
    private Label lbHighScore;

    @FXML
    private Label lbHighScoreCount;

    @FXML
    private Label lbDeaths;

    @FXML
    private Label lbDeathCount;

    @FXML
    private Label lbFood;

    @FXML
    private Label lbFoodCount;

    @FXML
    private Label lbSpeed;

    @FXML
    private Label lbSpeedCount;
    
    @FXML
    void handleBtnStart(ActionEvent event) 
    {
        if(!GlobalParams.GAME_STARTED)
        {
            startGame();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        MessageLog.INITIALIZE();
        setTemplatesInvisible();
        setGameElementsInvisible();
        setInitialCounters();
        enableStart();
    }

    private void setTemplatesInvisible() 
    {
        foodTemplate.setVisible(false);
        snakeTemplate.setVisible(false);
        snakeDead.setVisible(false);
    }

    private void setGameElementsInvisible() 
    {
        lbDead.setVisible(false);
        paneSnake.setVisible(false);
    }
    
    private void setInitialCounters()
    {
        lbScoreCount.setText(Constants.ZERO_AS_NUMBER);
        lbHighScoreCount.setText(Constants.ZERO_AS_NUMBER);
        lbDeathCount.setText(Constants.ZERO_AS_NUMBER);
        lbFoodCount.setText(Constants.ZERO_AS_NUMBER);
    }

    private void enableStart() 
    {
        btnStart.setVisible(true);
    }    
    
    public void startGame()
    {
        if(GlobalParams.GAME_STARTED)
        {
            MessageLog.ALREADY_STARTED();
            return;
        }

        MessageLog.STARTING();
        disableStartActionAndShowGamePane();
        createGameElements();
        resetCounters();
        
        startMove();
    }
    
    private void disableStartActionAndShowGamePane()
    {
        btnStart.setVisible(false);
        lbDead.setVisible(false);
        
        paneSnake.setVisible(true);
        GlobalParams.GAME_STARTED = true;
    }
    
    private void createGameElements() 
    {
        deleteGameElements();
        
        createFood();
        createSnake();
    }
    
    private void deleteGameElements()
    {
        timer = null;
        
        for(int i=0; i<paneSnake.getChildren().size(); i++)
        {
            paneSnake.getChildren().remove(i);
            i--;
        }
    }
    
    private void createFood()
    {
        Food food = new Food(foodTemplate, GlobalParams.GET_RANDOM_POS_X()+12.5, GlobalParams.GET_RANDOM_POS_Y()+12.5);
        paneSnake.getChildren().add(food);
    }
    
    private void createSnake()
    {
        snakeHead = new SnakePiece(snakeTemplate, Constants.SNAKE_INITIAL_POS_X, Constants.PANE_CENTER_Y);
        SnakePiece snakeBody = new SnakePiece(snakeTemplate, Constants.SNAKE_INITIAL_POS_X-Constants.SCENE_BLOCK_SIZE, Constants.PANE_CENTER_Y);
        
        paneSnake.getChildren().add(snakeHead);
        paneSnake.getChildren().add(snakeBody);
        
        GlobalParams.SNAKE_DIRECTION = Constants.DIR_RIGHT;
    }
    
    private void resetCounters()
    {
        lbScoreCount.setText(Constants.ZERO_AS_NUMBER);
        lbFoodCount.setText(Constants.ZERO_AS_NUMBER);
        
        GlobalParams.GAME_SPEED = Constants.ONE_AS_INTEGER;
        lbSpeedCount.setText(GlobalParams.GAME_SPEED + Constants.NO_CAP_LETTER_X);
    }
    
    private void startMove()
    {
        if(timer==null)
        {
            timer = new AnimationController(paneSnake.getChildren(), apMain.getChildren());
        }
        timer.start();
    }

}    
