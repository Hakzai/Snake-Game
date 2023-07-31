/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.controller;

import com.akeir.global.GlobalParams;
import com.akeir.global.Constants;
import com.akeir.global.MessageLog;
import com.akeir.helper.Utils;
import com.akeir.resources.controllers.MusicController;
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
        if(event.getEventType().toString().equals(Constants.EVENT_KEY_PRESSED) && !GlobalParams.GAME_CRASHED)
        {
            handleKeyAction((KeyEvent) event);
        }
    }
    
    private void handleKeyAction(KeyEvent event) 
    {
        KeyCode key = event.getCode();
        
        switch(key)
        {
            case ESCAPE:
                MessageLog.STOP();
                Utils.DO_CRASH();
                break;
            case ENTER:
                if(!GlobalParams.GAME_STARTED)
                {
                    GlobalParams.GAME_STARTED = true;
                    MessageLog.RESUME();
                }
                else
                {
                    GlobalParams.GAME_STARTED = false;
                    MessageLog.PAUSE();
                }
                break;
            case RIGHT:
                AnimationController.MOVE_QUEUE.offer(Constants.DIR_RIGHT);
                break;
            case LEFT:
                AnimationController.MOVE_QUEUE.offer(Constants.DIR_LEFT);
                break;
            case DOWN:
                AnimationController.MOVE_QUEUE.offer(Constants.DIR_DOWN);
                break;
            case UP:
                AnimationController.MOVE_QUEUE.offer(Constants.DIR_UP);
                break;
            case TAB:
                GlobalParams.GAME_SPEED += Constants.ONE_AS_INTEGER;
                MessageLog.FAST_FORWARD();
                break;
            case F1:
                if(!GlobalParams.IS_MUSIC_ENABLED)
                {
                    GlobalParams.IS_MUSIC_ENABLED = true;
                    MessageLog.ENABLE_MUSIC();
                    MusicController.get().playMusic();
                }
                else
                {
                    GlobalParams.IS_MUSIC_ENABLED = false;
                    MessageLog.DISABLE_MUSIC();
                    MusicController.get().pauseMusic();
                }
                break;
            case F2:
                if(!GlobalParams.IS_SOUND_ENABLED)
                {
                    GlobalParams.IS_SOUND_ENABLED = true;
                    MessageLog.ENABLE_SOUND();
                }
                else
                {
                    GlobalParams.IS_SOUND_ENABLED = false;
                    MessageLog.DISABLE_SOUND();
                }
                break;
        }
    }
}
