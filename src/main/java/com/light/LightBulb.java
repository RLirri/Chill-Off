package com.light;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LightBulb {
    private static Boolean status = false;

    public void turnLightOn(AnchorPane imageView) {
        status = true;
        imageView.setVisible(true);
        System.out.println("Light on");
    }

    public void turnLightOff(AnchorPane imageView)
    {
        status = false;
        System.out.println("Light off");
        imageView.setVisible(false);
    }

    public Boolean getStatus() {
        return status;
    }
}
