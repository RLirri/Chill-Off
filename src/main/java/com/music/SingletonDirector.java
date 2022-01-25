package com.music;

import com.Client;
import javafx.stage.Stage;
/**
 *
 * @author Huang Ruixin
 */

public class SingletonDirector {
	 //construct an object for the singleton
    private static SingletonDirector instance = new SingletonDirector();
    
    private Stage stage;
    
    private Client client;
    //the constructor of this class
    private SingletonDirector(){

    }
    
    //get the only usable object
    public static SingletonDirector getInstance(){
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

    public void toClient_coffee(String Output){
        try {
            client.start(stage);
            System.out.println("You coffee in Client_page is : " +Output);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
