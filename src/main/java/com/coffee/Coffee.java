package com.coffee;

import com.music.Director;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Hafizul
 */
public class Coffee extends Application {

    Stage window;
    Scene scene;
    private static final String scanPackage = "coffee";


    // launch the application


    public void start(Stage primaryStage) {

//        AnchorPane mainPane = FXMLLoader.load(getClass().getResource("/fxml/coffee_pane.fxml"));
//        Scene scene = new Scene(mainPane, 400, 400);
//        JMetro jMetro = new JMetro();
//        jMetro.setScene(scene);
        window = primaryStage;

        window.setTitle("Coffee Machine");

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20, 20, 80, 250));

        scene = new Scene(layout, 748, 502);
        window.setScene(scene);
        window.show();

        ChoiceBox<String> Beverage = new ChoiceBox<>();
        ChoiceBox<String> Condiment = new ChoiceBox<>();
        Button button = new Button("Confirm");
        Button button2 = new Button("Back");
        Label label = new Label("Select Beverage: ");
        Label label2 = new Label("Select Condiment: ");


        //getItems returns
        Beverage.getItems().add("Americano");
        Beverage.getItems().add("Espresso");
        Beverage.getItems().add("Cappuccino");
        Beverage.setValue("Americano");



        Condiment.getItems().addAll("Milk","Mocha", "Chocolate");
        Condiment.setValue("Milk");
        VBox layout2 = new VBox(20);
        layout2.setPadding(new Insets(20, 20, 80, 250));



        layout.getChildren().addAll(label, Beverage, label2, Condiment, button,  button2);


        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                getChoice(Beverage, Condiment);
                Director performance = Director.getInstance();
                performance.toClient();
            }
        };

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Director performance = Director.getInstance();
                performance.toClient();
            }
        };

        button.setOnAction(event);
        button2.setOnAction(event2);
    }

    private void getChoice(ChoiceBox<String> choiceBox, ChoiceBox<String> choiceBox2) {

        String Beverage = choiceBox.getValue();
        String Condiment = choiceBox2.getValue();
        Beverage beverage = null;
        Beverage condiment = null;

        if (Beverage == "Americano"){
            beverage = new Americano();
        }
        else if (Beverage == "Espresso"){
            beverage = new Espresso();
        }
        else if (Beverage == "Cappuccino"){
            beverage = new Cappuccino();
        }
        else {
            System.out.println("Error");
        }

        if (Condiment == "Milk"){
            condiment = new Milk(beverage);
        }
        else if (Condiment == "Mocha"){
            condiment = new Mocha(beverage);
        }
        else if (Condiment == "Chocolate"){
            condiment = new Chocolate(beverage);
        }
        else{
            System.out.println("Error");
        }
        System.out.println(condiment.getDescription());

    }


    public static void main(String[] args) {
        launch(args);
    }

}
