package com.light;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LightOffCommand implements Command{
    LightBulb lightBulb;

    public LightOffCommand(LightBulb lightBulb)
    {
        this.lightBulb=lightBulb;
    }

    @Override
    public void execute(AnchorPane imageView) {
        // Hide light from GUI
        lightBulb.turnLightOff(imageView);
    }

}
