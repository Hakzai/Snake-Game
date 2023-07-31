/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.helper;

import com.akeir.global.Constants;
import com.akeir.global.GlobalParams;
import com.akeir.global.MessageLog;
import com.akeir.resources.controllers.MusicController;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Codeiro
 */
public final class Utils {
    
    private Utils() { }
    
    public static final int stringToInt(String number)
    {
        int result;
        
        try
        {
            result = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            MessageLog.EXCEPTION(e);
            return -1;
        }
        
        return result;
    }
    
    public static final int integerIncreaseByOne(int number)
    {
        return number + Constants.ONE_AS_INTEGER;
    }
    
    public static final int integerIncreaseByTen(int number)
    {
        return number + Constants.TEN_AS_INTEGER;
    }
    
    public static final double GET_RANDOM_POS_X()
    {
        int position = ThreadLocalRandom.current().nextInt(Constants.ZERO_AS_INTEGER, (int) (Constants.PANE_SNAKE_WIDTH / Constants.SCENE_BLOCK_SIZE));
        return position * Constants.SCENE_BLOCK_SIZE;
    }

    public static final double GET_RANDOM_POS_Y() 
    {
        int position = ThreadLocalRandom.current().nextInt(Constants.ZERO_AS_INTEGER, (int) (Constants.PANE_SNAKE_HEIGHT / Constants.SCENE_BLOCK_SIZE));
        return position * Constants.SCENE_BLOCK_SIZE;
    }
    
    public static final void DO_CRASH()
    {
        GlobalParams.GAME_CRASHED = true;
        GlobalParams.GAME_STARTED = false;
        GlobalParams.IS_SOUND_ENABLED = false;
        GlobalParams.IS_MUSIC_ENABLED = false;
        MusicController.get().stopMusic();
    }
}
