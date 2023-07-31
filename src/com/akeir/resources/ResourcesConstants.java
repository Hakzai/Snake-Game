/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.resources;

/**
 *
 * @author Codeiro
 */
public final class ResourcesConstants {

    private ResourcesConstants() { }
    
    public static final String HIGH_SCORE_FILE_STRING = "HighScore: ";
    public static final String HIGH_SCORE_FILE_PATH = System.getenv("AppData") +"\\SnakeSave.txt";
    
    public static final String SOUNDS_FORMAT = ".wav";
    public static final String SOUNDS_PATH = "/com/akeir/resources/sounds/";
    
    public static final String BG_MUSIC_PATH = "/com/akeir/resources/music/backgroundMusic.mp3";
}
