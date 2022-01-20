package com.light;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LightOnCommand implements Command{
    LightBulb lightBulb;

    public LightOnCommand(LightBulb lightBulb)
    {
        this.lightBulb=lightBulb;
    }


    @Override
    public void execute(AnchorPane imageView) {
        // Display light on GUI
        lightBulb.turnLightOn(imageView);
    }

}
