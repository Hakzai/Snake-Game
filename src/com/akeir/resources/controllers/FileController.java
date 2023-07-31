/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.resources.controllers;

import com.akeir.resources.ResourcesConstants;
import com.akeir.global.Constants;
import com.akeir.global.MessageLog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Codeiro
 */
public class FileController {
    
    private FileController() { }
    
    private static FileController instance;
    
    public static FileController get()
    {
        if(null == instance)
        {
            instance = new FileController();
        }
        
        return instance;
    }
            
    public void saveScore(String highScore)
    {
        MessageLog.SAVE_HIGH_SCORE();

        try 
        {
            FileWriter file = new FileWriter(ResourcesConstants.HIGH_SCORE_FILE_PATH);
            PrintWriter writeFile = new PrintWriter(file);

            writeFile.printf(ResourcesConstants.HIGH_SCORE_FILE_STRING + highScore);
            
            file.close();
            
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String loadHighScore()
    {
        MessageLog.LOAD_HIGH_SCORE();
        String currentHighScore = Constants.ZERO_AS_NUMBER;
        
        try
        {
            BufferedReader fileRead = new BufferedReader(new FileReader(ResourcesConstants.HIGH_SCORE_FILE_PATH));
            
            List<Object> linesList = Arrays.asList(fileRead.lines().toArray());
            String lastLine = String.valueOf(linesList.get(linesList.size()-1));
            currentHighScore = lastLine.substring(ResourcesConstants.HIGH_SCORE_FILE_STRING.length());
            
            fileRead.close();
            
        } catch (IOException ex) {
            if(ex instanceof FileNotFoundException)
            {
                MessageLog.FILE_NOT_FOUND_ERROR();
            }
            else
            {
                MessageLog.FILE_READ_ERROR();
                MessageLog.EXCEPTION(ex);
            }

            return currentHighScore;
        }

        return currentHighScore;
    }
}

