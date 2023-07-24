/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.controller;

import com.akeir.global.GlobalParams;
import com.akeir.global.Constants;
import com.akeir.global.MessageLog;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Codeiro
 */
public class KeyController implements EventHandler {
    
    @Override
    public void handle(Event event) {
        if(event.getEventType().toString().equals(Constants.EVENT_KEY_PRESSED))
        {
            handleKeyAction((KeyEvent) event);
        }
    }
    
    private void handleKeyAction(KeyEvent event) 
    {
        KeyCode key = event.getCode();
        if(!isValidMove(key, GlobalParams.SNAKE_DIRECTION))
        {
            MessageLog.INVALID_MOVE();
            return;
        }
        
        switch(key)
        {
            case ENTER:
                MessageLog.STOP();
                GlobalParams.GAME_STARTED = false;
                break;
            case RIGHT:
                GlobalParams.SNAKE_DIRECTION = Constants.DIR_RIGHT;
                MessageLog.SNAKE_DIRECTION();
                break;
            case LEFT:
                GlobalParams.SNAKE_DIRECTION = Constants.DIR_LEFT;
                MessageLog.SNAKE_DIRECTION();
                break;
            case DOWN:
                GlobalParams.SNAKE_DIRECTION = Constants.DIR_DOWN;
                MessageLog.SNAKE_DIRECTION();
                break;
            case UP:
                GlobalParams.SNAKE_DIRECTION = Constants.DIR_UP;
                MessageLog.SNAKE_DIRECTION();
                break;
            case TAB:
                GlobalParams.GAME_SPEED *= Constants.TWO_AS_INTEGER;
                MessageLog.FAST_FORWARD();
                break;
        }
    }
    
    private boolean isValidMove(KeyCode key, String currentDirection)
    {
        if(key.equals(KeyCode.RIGHT) && currentDirection.equals(Constants.DIR_LEFT))
        {
            return false;
        }
        else if(key.equals(KeyCode.LEFT) && currentDirection.equals(Constants.DIR_RIGHT))
        {
            return false;
        }
        else if(key.equals(KeyCode.DOWN) && currentDirection.equals(Constants.DIR_UP))
        {
            return false;
        }
        else if(key.equals(KeyCode.UP) && currentDirection.equals(Constants.DIR_DOWN))
        {
            return false;
        }
        else
        {
            return true;
        }
    }    
}
