package com.music;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.music.anotation.Autowired;
import com.music.controller.CenterController;
import com.music.controller.LeftController;
import com.music.controller.MusicListController;
import com.music.controller.PlayController;
import com.music.file.FileMp3ServerImpl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;

/**
 *
 * @author Huang Ruixin
 */

public class MusicScreen extends Application {

    private Map<String, Object> controllerMap = new HashMap<>();

    private static final String scanPackage = "com.music.controller";

    private PlayController playController;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        AnchorPane mainPane = FXMLLoader.load(getClass().getResource("/fxml/main_pane.fxml"));
        AnchorPane musicPane = initMusicController();
        AnchorPane playerPane = initPlayerController();
        AnchorPane musicListPane = initMusicListController();
        AnchorPane centerPane = initCenterController();

        mainPane.getChildren().addAll(centerPane, musicPane, playerPane, musicListPane);

        initAutowired();


        Scene scene = new Scene(mainPane);
        JMetro jMetro = new JMetro();
        jMetro.setScene(scene);

        scene.getStylesheets().addAll("/css/slider_style.css", "/css/style.css");

//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Chill-Off");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(950);
        primaryStage.setMinHeight(600);
        //primaryStage.getIcons().add(new Image("/image/icon.png"));
        primaryStage.show();

        playController.slider.prefWidthProperty().bind(primaryStage.widthProperty().subtract(1040 - 411));
    }

    private AnchorPane initMusicListController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/music_list_pane.fxml"));
        AnchorPane pane = loader.load();
        MusicListController musicListController = loader.getController();

        saveInstance(musicListController);

        pane.setMaxHeight(450);
        pane.setMinHeight(450);
        pane.setMinWidth(450);
        AnchorPane.setBottomAnchor(pane, 70.0);
        AnchorPane.setTopAnchor(pane, 130.0);
        AnchorPane.setRightAnchor(pane, 0.0);

        return pane;
    }

    private AnchorPane initPlayerController() throws IOException {
        FXMLLoader playLoader = new FXMLLoader(getClass().getResource("/fxml/play_pane.fxml"));
        AnchorPane playerPane = playLoader.load();
        playController = playLoader.getController();

        saveInstance(playController);

        playerPane.setMaxHeight(66);
        playerPane.setMinHeight(70);
        playerPane.setMinWidth(950);
        AnchorPane.setBottomAnchor(playerPane, 0.0);
        AnchorPane.setLeftAnchor(playerPane, 0.0);
        AnchorPane.setRightAnchor(playerPane, 0.0);

        return playerPane;
    }

    private AnchorPane initMusicController() throws IOException {
        FXMLLoader musicLoader = new FXMLLoader(getClass().getResource("/fxml/left_pane.fxml"));
        AnchorPane musicPane = musicLoader.load();
        LeftController leftController = musicLoader.getController();

        saveInstance(leftController);

        musicPane.setMaxWidth(250.0);
        musicPane.setMaxHeight(630);
        musicPane.setMinWidth(250);
        musicPane.setPrefWidth(250);
        AnchorPane.setBottomAnchor(musicPane, 70.0);
        AnchorPane.setLeftAnchor(musicPane, 0.0);
        AnchorPane.setTopAnchor(musicPane, 0.0);

        return musicPane;
    }

    private AnchorPane initCenterController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/center_pane.fxml"));
        AnchorPane pane = loader.load();
        CenterController centerController = loader.getController();

        saveInstance(centerController);

        pane.setMinWidth(450);
        pane.setPrefWidth(450);
        pane.setMinHeight(450);
        pane.setPrefHeight(450);
        AnchorPane.setBottomAnchor(pane, 130.0);
        AnchorPane.setLeftAnchor(pane, 250.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);

        return pane;
    }

    private void saveInstance(Object o) {
        String name = o.getClass().getName();
        controllerMap.put(name, o);
    }

   
    private void initAutowired() throws IllegalAccessException, IOException {

        List<String> classFiles;
   
        URL url = this.getClass().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File filePath = new File(url.getFile());

        classFiles = Stream.of(filePath.listFiles()).map(file -> {
            String className = scanPackage + "." + file.getName().replace(".class", "");
            Pattern pattern = Pattern.compile("\\$\\S+"); 
            Matcher matcher = pattern.matcher(className);
            className = matcher.replaceAll("");
            return className;
        }).collect(Collectors.toList());
        

        for (String className : classFiles) {

            Class clazz = controllerMap.get(className).getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }
                Autowired autowired = field.getAnnotation(Autowired.class);
                field.setAccessible(true);
                String fieldName = field.getType().getName();
                if ("".equals(autowired.value())) {
                    Object o = controllerMap.get(clazz.getName());
                    field.set(o, controllerMap.get(fieldName));
                } else {
                    field.set(autowired.value(), controllerMap.get(fieldName));
                }
            }
        }
    }
}
