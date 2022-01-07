package com.music.controller;

import com.music.constant.RotateConstant;
import com.music.init.Initialize;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
/**
 *
 * @author Huang Ruixin
 */

public class CenterController implements Initialize {

    public ImageView coverImageView;
    public Label lyricLabel;
    public HBox firstBox;
    public AnchorPane secondBox;
   

    private RotateTransition rotateTransition;

    @FXML
    public void initialize() {
        setCoverImage(new Image("/image/cover/cover.jpg"));
    }

    public void setRotate(double sec) {
        
        double byAngle = sec / 0.1;
//        double toAngle = sec / 0.1 + fromAngle;
        rotateTransition = new RotateTransition(Duration.seconds(sec), coverImageView);
       
        rotateTransition.setByAngle(byAngle);
       
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
    }

    public void toggleRotate(int status) {
        toggleRotate(status, 0);
    }

    public void toggleRotate(int status, double sec) {
        if (rotateTransition == null) {
            setRotate(sec);
            return;
        }

        switch (status) {
            case RotateConstant.PLAY: {
                rotateTransition.play();
                break;
            }
            case RotateConstant.PAUSE:
            case RotateConstant.STOP: {
                rotateTransition.stop();
                break;
            }
            case RotateConstant.REPLAY: {
                rotateTransition.stop();
                setRotate(sec);
                break;
            }
        }
    }

    public void setLyricLabel(String text) {
        lyricLabel.setText(text);
    }
    public void setCoverImage(Image image) {
        coverImageView.setImage(image);
        
        Rectangle rectangle = new Rectangle(coverImageView.getFitWidth(), coverImageView.getFitHeight());
        rectangle.setArcWidth(coverImageView.getFitWidth());
        rectangle.setArcHeight(coverImageView.getFitHeight());
        coverImageView.setClip(rectangle);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage newImage = coverImageView.snapshot(parameters, null);
        coverImageView.setClip(null);
//        coverImageView.setEffect(new GaussianBlur(100));
        coverImageView.setEffect(new DropShadow(20, Color.BLACK));
        coverImageView.setImage(newImage);
    }
    
   
}
