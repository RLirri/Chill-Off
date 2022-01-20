package com;

import com.music.Director;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Huang Ruixin
 */

public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/chill_off_gui.fxml"));
        primaryStage.setTitle("Chill-Off");
        primaryStage.setMinWidth(748);
        primaryStage.setMinHeight(502);
        primaryStage.setMaxWidth(748);
        primaryStage.setMaxHeight(502);
        primaryStage.setWidth(748);
        primaryStage.setHeight(502);
        primaryStage.setScene(new Scene(root, 748, 502));
        Director performance = Director.getInstance();
        //use the method to initiate the game windows
        performance.init(this,primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
