package com.music.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.music.anotation.Autowired;
import com.music.file.FileMp3ServerImpl;
import com.music.init.Initialize;
import com.music.model.MusicInfo;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 *
 * @author Huang Ruixin
 */
public class LeftController implements Initialize {
    public ScrollPane scrollPane;
    public ListView<MusicInfo> myMusicListView;
    public TitledPane collectionList;
    public TitledPane createList;

    @Autowired
    private PlayController playController;

    @Autowired
    private MusicListController musicListController;
    
    private FileMp3ServerImpl fileMp3Server=new FileMp3ServerImpl();
    

    @FXML
    public void initialize() throws IOException {
        List<MusicInfo> musicList = new ArrayList<>();

        musicList = fileMp3Server.getMusicInfoList("/music");

        myMusicListView.getItems().addAll(musicList);
        myMusicListView.setCellFactory(musicListCell());
        myMusicListView.setOnMouseClicked(musicListListener());
       
    }

    

    private EventHandler<MouseEvent> musicListListener() {
        return event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                MusicInfo musicInfo = myMusicListView.getSelectionModel().getSelectedItem();
                if (event.getClickCount() == 2) {
                    playController.toggleMusic(musicInfo, true);
                    ObservableList<MusicInfo> currentMusicList = myMusicListView.getItems();
                    musicListController.toggleMusicList(currentMusicList);
                } else {
                    playController.toggleMusic(musicInfo, false);
                }
            }
        };
    }

    private Callback<ListView<MusicInfo>, ListCell<MusicInfo>> musicListCell() {
        Callback<ListView<MusicInfo>, ListCell<MusicInfo>> cell = TextFieldListCell.forListView(new StringConverter<MusicInfo>() {
            @Override
            public String toString(MusicInfo object) {
                return object.getMusicName();
            }

            @Override
            public MusicInfo fromString(String string) {
                return null;
            }
        });
        return cell;
    }

    
}
