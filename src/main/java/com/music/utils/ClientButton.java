package com.music.utils;

import com.music.Director;

import javafx.fxml.FXML;

/**
 *
 * @author Huang Ruixin
 */

public class ClientButton {
	
	@FXML
    void onGoPlayButtonClick(){ //ActionEvent event
		System.out.println("onGoPlayButtonClick");
        //get the only object of the singleton class
        Director performance = Director.getInstance();
        //use the method to the start screen page
        performance.toStart();
        
    }
}
