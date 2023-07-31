/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.resources.controllers.model;

/**
 *
 * @author Codeiro
 */
public abstract class Sounds {
    
    public abstract void snakeMove();
    
    public abstract void snakeEatsFood();
    
    public abstract void snakeCollideWall();
    
    public abstract void snakeCollideBody();
    
    public abstract void snakeSpeedsUp();
    
    public abstract void deathSound();
    
    public abstract void deathLaugh();

    public abstract void startGame();
    
}
