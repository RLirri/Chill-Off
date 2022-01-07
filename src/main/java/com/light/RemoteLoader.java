package com.light;

import javafx.scene.layout.AnchorPane;

public class RemoteLoader {
    public static void init(AnchorPane lightBackground) {
        RemoteControl remote = new RemoteControl();
        LightBulb bulb = new LightBulb();
        LightOnCommand lightOn = new LightOnCommand(bulb);
        LightOffCommand lightOff = new LightOffCommand(bulb);

        remote.setCommand(bulb.getStatus() ? lightOff : lightOn);
        remote.buttonPressed(lightBackground);
    }
}
