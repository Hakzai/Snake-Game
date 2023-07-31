/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.resources.controllers;

import com.akeir.resources.controllers.model.Sounds;
import com.akeir.global.GlobalParams;
import com.akeir.resources.ResourcesConstants;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.AudioClip;

/**
 *
 * @author Codeiro
 */
public class SoundController extends Sounds{
    
    private Map<String, AudioClip> soundMap = new HashMap<>();
    
    private SoundController()
    { 
        createSoundsMap();
    }
    
    private void createSoundsMap()
    {
        Method[] soundsMethods = getClass().getSuperclass().getDeclaredMethods();
        for(Method method : soundsMethods)
        {
            String url = getClass().getResource(ResourcesConstants.SOUNDS_PATH + method.getName() + ResourcesConstants.SOUNDS_FORMAT).toString();
            AudioClip clip = new AudioClip(url);
            soundMap.put(method.getName(), clip);
        }
    }
    
    private static SoundController playerInstance;
    
    public static Sounds play()
    {
        if(null == playerInstance)
        {
            playerInstance = new SoundController();
        }
        
        if(!GlobalParams.IS_SOUND_ENABLED)
        {
            return SoundMuted.mute();
        }

        return playerInstance;
    }
    
    public void snakeMove()
    {
        soundMap.get("snakeMove").setVolume(0.2);
        soundMap.get("snakeMove").play();
    }
    
    public void snakeEatsFood()
    {
        soundMap.get("snakeEatsFood").setVolume(0.3);
        soundMap.get("snakeEatsFood").play();
    }
    
    public void snakeCollideWall()
    {
        soundMap.get("snakeCollideWall").setVolume(0.7);
        soundMap.get("snakeCollideWall").play();
    }
    
    public void snakeCollideBody()
    {
        soundMap.get("snakeCollideBody").setVolume(0.7);
        soundMap.get("snakeCollideBody").play();
    }
    
    public void snakeSpeedsUp()
    {
        soundMap.get("snakeSpeedsUp").setVolume(0.4);
        soundMap.get("snakeSpeedsUp").play();
    }
    
    public void deathSound()
    {
        soundMap.get("deathSound").setVolume(0.2);
        soundMap.get("deathSound").play();
        this.deathLaugh();
    }
    
    public void deathLaugh()
    {
        soundMap.get("deathLaugh").setVolume(0.6);
        soundMap.get("deathLaugh").play();
    }
        
    public void startGame()
    {
        soundMap.get("startGame").setVolume(0.3);
        soundMap.get("startGame").play();
    }
}
