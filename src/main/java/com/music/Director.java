package com.music;

import com.Client;
import javafx.stage.Stage;
/**
 *
 * @author Huang Ruixin
 */

public class Director {
	 //construct an object for the singleton
    private static Director instance = new Director();
    
    private Stage stage;
    
    private Client client;
    //the constructor of this class
    private Director(){

    }
    
    //get the only usable object
    public static Director getInstance(){

        return instance;
    }
    
    public void init(Client client,Stage stage){
    	this.client=client;
        this.stage = stage;
    }

    // load the start screen
    public void toStart(){
        MusicScreen main=new MusicScreen();
        try {
            main.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // load the start screen
    public void toStart_coffee(){
        com.coffee.Coffee main=new com.coffee.Coffee();
        try {
            main.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // load the client screen
    public void toClient(){
    	try {
    		client.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
