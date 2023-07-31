/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.resources.controllers;

import com.akeir.global.GlobalParams;
import com.akeir.resources.ResourcesConstants;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Codeiro
 */
public class MusicController{
    
    private Media music;
    private MediaPlayer player;
    
    private MusicController()
    {
        setBackgroundMusic();
    }
    
    private void setBackgroundMusic()
    {
        music = new Media(getClass().getResource(ResourcesConstants.BG_MUSIC_PATH).toString());
        player = new MediaPlayer(music);
    }
    
    private static MusicController instance;
    
    public static MusicController get()
    {
        if(null == instance)
        {
            instance = new MusicController();
        }
        
        return instance;
    }
    
    public void playMusic()
    {
        if(GlobalParams.IS_MUSIC_ENABLED)
        {
            player.setOnEndOfMedia(new Runnable() {
                public void run() 
                {
                    player.seek(Duration.ZERO);
                }
            });
            player.play();
            player.setVolume(0.4);
        }
    }
    
    public void pauseMusic()
    {
        player.pause();
    }
    
    public void stopMusic()
    {
        player.stop();
    }
    
}
