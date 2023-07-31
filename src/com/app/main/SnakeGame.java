/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.main;

import com.akeir.controller.KeyController;
import com.akeir.global.MessageLog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Codeiro
 */
public class SnakeGame extends Application {
    
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("/com/akeir/scene/view/FXMLSnakeGame.fxml"));
        
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new KeyController());
        stage.setResizable(false);
        stage.setTitle("Akeir Snake Game");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
        MessageLog.CLOSE_GAME_MESSAGE();
    }
}
