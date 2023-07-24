/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.model;

import javafx.scene.shape.Circle;

/**
 *
 * @author Codeiro
 */
public class Food extends Circle{
    
    public Food(Circle foodTemplate, double initialPosX, double initialPosY)
    {
        this.setRadius(foodTemplate.getRadius());
        this.setFill(foodTemplate.getFill());
        this.setLayoutX(initialPosX);
        this.setLayoutY(initialPosY);
        this.setStroke(foodTemplate.getStroke());
        this.setStrokeType(foodTemplate.getStrokeType());
        this.setStrokeWidth(foodTemplate.getStrokeWidth());
        this.setVisible(true);
    }
}
