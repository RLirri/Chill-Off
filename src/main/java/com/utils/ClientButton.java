package com.utils;

import com.chat.Gentleman;
import com.chat.Lady;
import com.music.SingletonDirector;

import javafx.fxml.FXML;
import com.light.RemoteLoader;
import javafx.scene.layout.AnchorPane;


public class ClientButton {

    @FXML
    private AnchorPane lightOff;

    @FXML
    void onGoPlayButtonClick() { //ActionEvent event
        System.out.println("onGoPlayButtonClick");
        //get the only object of the singleton class
        SingletonDirector performance = SingletonDirector.getInstance();
        //use the method to the start screen page
        performance.toStart();

    }

    @FXML
    void onCoffeeButtonClick() {
        System.out.println("onCoffeeButtonClick");
        SingletonDirector test = SingletonDirector.getInstance();
        test.toStart_coffee();

    }

    @FXML
    public void onLightButtonClick() { //ActionEvent event
        RemoteLoader.init(lightOff);
    }

    @FXML
    public void onWomenButtonClick() {new Lady().performChat();}

    @FXML
    public void onManButtonClick() {new Gentleman().performChat();}
}






