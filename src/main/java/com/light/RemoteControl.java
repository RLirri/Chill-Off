package com.light;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class RemoteControl {
    Command slot;

    public RemoteControl() {
    }

    public void setCommand(Command command) {
        slot = command;
    }

    public void buttonPressed(AnchorPane imageView) {
        slot.execute(imageView);
    }
}
