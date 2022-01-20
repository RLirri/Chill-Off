package com;

import com.music.Director;

import javafx.fxml.FXML;
import com.light.RemoteLoader;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author Huang Ruixin
 */

public class ClientButton {

    @FXML
    private AnchorPane lightOff;
    private Object coffee_machine;

    @FXML
    void onGoPlayButtonClick(){ //ActionEvent event
		System.out.println("onGoPlayButtonClick");
        //get the only object of the singleton class
        Director performance = Director.getInstance();
        //use the method to the start screen page
        performance.toStart();
        
    }

    @FXML
    void onCoffeeButtonClick(){
        System.out.println("onCoffeeButtonClick");
//        Coffee coffee = ((Coffee) this.getClass(coffee_machine));
        Director test = Director.getInstance();
        test.toStart_coffee();

    }

    //onMouseClicked='#onCoffeeButtonClick' styleClass="handClass"

    @FXML
    public void onLightButtonClick() { //ActionEvent event
        RemoteLoader.init(lightOff);
    }
}



