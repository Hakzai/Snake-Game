/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this snakeTemplate file, choose Tools | Templates
 * and open the snakeTemplate in the editor.
 */
package com.akeir.model;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author Codeiro
 */
public class SnakePiece extends Rectangle{
    
    public SnakePiece(Rectangle snakeTemplate, double currentPosX, double currentPosY)
    {
        this.setArcHeight(snakeTemplate.getArcHeight());
        this.setArcWidth(snakeTemplate.getArcWidth());
        this.setFill(snakeTemplate.getFill());
        this.setHeight(snakeTemplate.getHeight());
        this.setWidth(snakeTemplate.getWidth());
        this.setLayoutX(currentPosX);
        this.setLayoutY(currentPosY);
        this.setStroke(snakeTemplate.getStroke());
        this.setStrokeType(snakeTemplate.getStrokeType());
        this.setStrokeWidth(snakeTemplate.getStrokeWidth());
        this.setVisible(true);
    }
}
