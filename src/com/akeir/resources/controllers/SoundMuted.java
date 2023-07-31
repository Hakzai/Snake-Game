/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.resources.controllers;

import com.akeir.resources.controllers.model.Sounds;

/**
 *
 * @author Codeiro
 */
public class SoundMuted extends Sounds {
  
    private SoundMuted() { }
    
    private static SoundMuted instance;
    
    public static SoundMuted mute()
    {
        if(null == instance)
        {
            instance = new SoundMuted();
        }
        
        return instance;
    }
    
    @Override
    public void snakeMove() {    }

    @Override
    public void snakeEatsFood() {    }

    @Override
    public void snakeCollideWall() {    }

    @Override
    public void snakeCollideBody() {    }

    @Override
    public void snakeSpeedsUp() {    }

    @Override
    public void deathSound() {    }
    
    @Override
    public void deathLaugh() {    }

    @Override
    public void startGame() {    }
       
}
