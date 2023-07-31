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
import com.akeir.helper.Utils;
import com.akeir.model.SnakePiece;
import com.akeir.resources.controllers.FileController;
import com.akeir.resources.controllers.MusicController;
import com.akeir.resources.controllers.SoundController;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

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
    private ImageView imgAtechLogo;
    
    @FXML
    private ImageView imgInstructions;
    
    @FXML
    void handleBtnStart(ActionEvent event) 
    {
        if(!GlobalParams.GAME_STARTED && !GlobalParams.GAME_CRASHED)
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
        setCustomTextFontsForLabels();
        setInitialCounters();
        setCurrentHighScore();
        enableStartAndShowInstructions();
    }

    private void setTemplatesInvisible() 
    {
        foodTemplate.setVisible(false);
        snakeTemplate.setVisible(false);
        snakeDead.setVisible(false);
        imgAtechLogo.setVisible(false);
    }

    private void setGameElementsInvisible() 
    {
        lbDead.setVisible(false);
        paneSnake.setVisible(false);
    }
    
    private void setCustomTextFontsForLabels()
    {
        lbDead.setFont(Font.loadFont(getClass().getResource("/com/akeir/resources/fonts/creepy.ttf").toExternalForm(), 128.0));
        lbTitle.setFont(Font.loadFont(getClass().getResource("/com/akeir/resources/fonts/pioneer.ttf").toExternalForm(), 85.0));
    }
    
    private void setInitialCounters()
    {
        lbScoreCount.setText(Constants.ZERO_AS_NUMBER);
        lbHighScoreCount.setText(Constants.ZERO_AS_NUMBER);
        lbDeathCount.setText(Constants.ZERO_AS_NUMBER);
        lbFoodCount.setText(Constants.ZERO_AS_NUMBER);
    }
    
    private void setCurrentHighScore()
    {
        lbHighScoreCount.setText(FileController.get().loadHighScore());
    }

    private void enableStartAndShowInstructions() 
    {
        imgInstructions.setVisible(true);
        imgInstructions.toFront();
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
        SoundController.play().startGame();
        MusicController.get().playMusic();
        disableStartActionAndShowGamePane();
        createGameElements();
        resetCounters();
        resetMoveQueue();
        
        startMove();
    }
    
    private void disableStartActionAndShowGamePane()
    {
        btnStart.setVisible(false);
        lbDead.setVisible(false);
        imgInstructions.setVisible(false);
        
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
        Food food = new Food(foodTemplate, Utils.GET_RANDOM_POS_X()+12.5, Utils.GET_RANDOM_POS_Y()+12.5);
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
        
        lbSpeedCount.setText(Constants.INITIAL_GAME_SPEED + Constants.NO_CAP_LETTER_X);
    }
    
    private void resetMoveQueue()
    {
        AnimationController.MOVE_QUEUE = new LinkedList<>();
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
