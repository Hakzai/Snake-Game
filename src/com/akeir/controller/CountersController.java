/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.controller;

import com.akeir.global.Constants;
import com.akeir.global.GlobalParams;
import com.akeir.helper.ElementsHolder;
import com.akeir.helper.Utils;
import javafx.scene.control.Label;

/**
 *
 * @author Codeiro
 */
public class CountersController {
    
    private int score = Constants.ZERO_AS_INTEGER;
    private int highScore = Constants.ZERO_AS_INTEGER;
    private int deaths = Constants.ZERO_AS_INTEGER;
    private int foods = Constants.ZERO_AS_INTEGER;
    
    private Label lbScoreCount;
    private Label lbHighScoreCount;
    private Label lbDeathCount;
    private Label lbFoodCount;
    private Label lbSpeedCount;
    
    public CountersController(ElementsHolder elements)
    {
        mapLabelCounters(elements);
    }
    
    private void mapLabelCounters(ElementsHolder elements)
    {
        if(null == lbScoreCount)
        {
            lbScoreCount = (Label) elements.getElementsMap().get("lbScoreCount");
        }
        if(null == lbHighScoreCount)
        {
            lbHighScoreCount = (Label) elements.getElementsMap().get("lbHighScoreCount");
        }
        if(null == lbDeathCount)
        {
            lbDeathCount = (Label) elements.getElementsMap().get("lbDeathCount");
        }
        if(null == lbFoodCount)
        {
            lbFoodCount = (Label) elements.getElementsMap().get("lbFoodCount");
        }
        if(null == lbSpeedCount)
        {
            lbSpeedCount = (Label) elements.getElementsMap().get("lbSpeedCount");
        }
    }
    
    public void handleScore()
    {
        mapScoreCountersToInt();
        mapResultsToScoreCounters();
    }
    
    private void mapScoreCountersToInt()
    {
        score = Utils.integerIncreaseByTen(Utils.stringToInt(lbScoreCount.getText()));
        highScore = score > Utils.stringToInt(lbHighScoreCount.getText()) ? score : Utils.stringToInt(lbHighScoreCount.getText());
        foods = Utils.integerIncreaseByOne(Utils.stringToInt(lbFoodCount.getText()));
    }
    
    private void mapResultsToScoreCounters()
    {
        lbScoreCount.setText(String.valueOf(score));
        lbHighScoreCount.setText(String.valueOf(highScore));
        lbFoodCount.setText(String.valueOf(foods));
    }
    
    public void handleDeathCount()
    {
        mapDeathCounterToInt();
        mapResultToDeathCounter();
    }
    
    public void mapDeathCounterToInt()
    {
        deaths = Utils.integerIncreaseByOne(Utils.stringToInt(lbDeathCount.getText()));
    }
    
    public void mapResultToDeathCounter()
    {
        lbDeathCount.setText(String.valueOf(deaths));
    }
    
    public void checkToIncreaseSpeed()
    {
        if(score%Constants.POINTS_TO_INCREASE_SPEED==Constants.ZERO_AS_INTEGER)
        {
            increaseSpeed();
        }
    }
    
    public void increaseSpeed()
    {
        GlobalParams.GAME_SPEED += Constants.ONE_AS_INTEGER;
        lbSpeedCount.setText(GlobalParams.GAME_SPEED + Constants.NO_CAP_LETTER_X);
    }
}
