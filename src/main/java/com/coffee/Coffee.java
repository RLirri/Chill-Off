package com.coffee;

import com.music.SingletonDirector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Hafizul
 */
public class Coffee extends Application {

    Stage window;
    Scene scene;
    String main_output;

    public void start(Stage primaryStage) {

        window = primaryStage;
        window.setTitle("Coffee Machine");
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20, 20, 80, 120));
        scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.show();

        ChoiceBox<String> Beverage = new ChoiceBox<>();
        ChoiceBox<String> Condiment = new ChoiceBox<>();
        Button confirm = new Button("Confirm");
        Button back = new Button("Back");
        Label label = new Label("Select Beverage: ");
        Label label2 = new Label("Select Condiment: ");
        Label output = new Label("You not order anything yet");

        Beverage.getItems().add("Americano");
        Beverage.getItems().add("Espresso");
        Beverage.getItems().add("Cappuccino");
        Beverage.setValue("Americano");
        Condiment.getItems().addAll("Milk","Mocha", "Chocolate", "NONE");
        Condiment.setValue("NONE");

        layout.getChildren().addAll(label, Beverage, label2, Condiment, confirm,  back, output);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                main_output = getChoice(Beverage, Condiment);
                output.setText("Your order is: " +main_output);
            }
        };

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                SingletonDirector performance = SingletonDirector.getInstance();
                performance.toClient_coffee(main_output);
            }
        };

        confirm.setOnAction(event);
        back.setOnAction(event2);
    }


    private String getChoice(ChoiceBox<String> choiceBox, ChoiceBox<String> choiceBox2) {

        String Beverage = choiceBox.getValue();
        String Condiment = choiceBox2.getValue();
        Beverage beverage = null;
        Beverage condiment = null;
        String output;

        if (Beverage.equals("Americano") ){
            beverage = new Americano();
        } else if (Beverage.equals("Espresso")){
            beverage = new Espresso();
        } else if (Beverage.equals("Cappuccino")){
            beverage = new Cappuccino();
        } else {
            System.out.println("Error");
        }

        if (Condiment.equals("Milk")){
            condiment = new Milk(beverage);
            output = condiment.getDescription();
        } else if (Condiment.equals("Mocha")){
            condiment = new Mocha(beverage);
            output = condiment.getDescription();
        } else if (Condiment.equals("Chocolate")){
            condiment = new Chocolate(beverage);
            output = condiment.getDescription();
        } else{
            output = beverage.getDescription();
        }
        System.out.println(output);

        return output;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
