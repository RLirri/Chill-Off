package com.music.controller;

import java.io.IOException;
import java.util.List;

import com.music.Director;
import com.music.anotation.Autowired;
import com.music.constant.RotateConstant;
import com.music.constant.ToggleMusicConstant;
import com.music.file.FileMp3ServerImpl;
import com.music.init.Initialize;
import com.music.model.MusicInfo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Huang Ruixin
 */
public class PlayController implements Initialize {

    public Button lastOne;
    public Button playButton;
    public Button nextOne;
    public Label startLabel;
    public Slider slider;
    public Label endLabel;
    public Button musicMenu;

    public Label soundLabel;
    public Slider soundSlider;

    @Autowired
    private MusicListController musicListController;

    @Autowired
    private CenterController centerController;

    @Autowired
    private LeftController leftController;
    
    private FileMp3ServerImpl fileMp3Server=new FileMp3ServerImpl();

    private MediaPlayer mediaPlayer;
   
    boolean isPlay = false;

   
    SimpleDoubleProperty volume = new SimpleDoubleProperty(30);

    
    private MusicInfo musicInfo = new MusicInfo();

    @FXML
    public void initialize() throws IOException {
        initMediaPlayer();

        soundSlider.setValue(volume.getValue());
        volume.bind(soundSlider.valueProperty());

        slider.valueChangingProperty().addListener((observable, oldValue, newValue) -> {
            
            if (newValue) {
                return;
            }
            int value = (int) slider.getValue();
            playWithTime(value);
        });

        slider.setOnMouseClicked(event -> {
            if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                int value = (int) slider.getValue();
                playWithTime(value);
            }
        });

        musicMenu.setOnAction(event -> musicListController.toggleShow());

    }

    /**
     *
     *
     * @param musicInfo
     * @param play     
     */
    public void toggleMusic(MusicInfo musicInfo, boolean play) {
        this.musicInfo = musicInfo;
        Media media = getMedia(musicInfo.getMusicPath());
        reset(media);
        centerController.setLyricLabel(musicInfo.getMusicName());

        if (play) {
           
            isPlay = false;
            mediaPlayer.play();
            togglePlayImage();
            isPlay = true;
        } else if (isPlay) {
            mediaPlayer.play();
        }
    }

    /**
     * 
     *
     * @param media
     */
    private void reset(Media media) {
        mediaPlayer = createMediaPlayer(media);
        mediaPlayer.setOnPlaying(() -> centerController.toggleRotate(RotateConstant.REPLAY, mediaPlayer.getStopTime().toSeconds()));
    }

    /**
     * 
     */
    public void toggleState() {
        if (isPlay) {
            mediaPlayer.pause();
            centerController.toggleRotate(RotateConstant.PAUSE);
        } else {
            mediaPlayer.play();
            centerController.toggleRotate(RotateConstant.PLAY, mediaPlayer.getStopTime().toSeconds());
        }
        togglePlayImage();
        isPlay = !isPlay;
    }

    /**
     * 
     *
     * @param time unit: second
     */
    private void playWithTime(int time) {
        mediaPlayer.stop();
        mediaPlayer.setStartTime(Duration.seconds(time));
        if (isPlay) {
            mediaPlayer.play();
        }
    }

    @FXML
    private void playNextOne() {
        toggleOneMusic(ToggleMusicConstant.NEXT_ONE);
    }

    @FXML
    private void playLastOne() {
        toggleOneMusic(ToggleMusicConstant.LAST_ONE);
    }

    /**
     * mediaPlayer 
     *
     * @return
     */
    private ChangeListener<Duration> timeListener() {
        return new ChangeListener<Duration>() {
            int oldTime;

            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                oldTime = (int) oldValue.toSeconds();
                int newTime = (int) newValue.toSeconds();

                if (newTime - oldTime < 1) {
                    return;
                }
                oldTime = newTime;

                slider.setValue(newTime);

                int min = (int) ((double) newTime / 60);
                int sec = (int) ((double) newTime % 60);
                String minText = min == 0 ? "00" : (min < 10 ? "0" + min : String.valueOf(min));
                String secText = sec == 0 ? "00" : (sec < 10 ? "0" + sec : String.valueOf(sec));
                startLabel.setText(minText + ":" + secText);
            }
        };
    }

    /**
     * 
     * 
     *
     * @param toggleStatus
     */
    private void toggleOneMusic(int toggleStatus) {
        List<MusicInfo> musicList = musicListController.getMusicInfoList();
        for (MusicInfo info : musicList) {
            if (musicInfo.getMusicPath().equals(info.getMusicPath())) {
                musicInfo = info;
                break;
            }
        }
        int currentIndex = musicListController.playListView.getItems().indexOf(musicInfo);
        if (toggleStatus == ToggleMusicConstant.NEXT_ONE) {
            if (currentIndex + 1 >= musicList.size()) {
                musicInfo = musicList.get(0);
            } else {
                musicInfo = musicList.get(currentIndex + 1);
            }
        } else if (toggleStatus == ToggleMusicConstant.LAST_ONE) {
            if (currentIndex == 0) {
                musicInfo = musicList.get(musicList.size() - 1);
            } else {
                musicInfo = musicList.get(currentIndex - 1);
            }
        }
        this.musicInfo = musicInfo;
//            Media media1 = new Media(getClass().getResource(musicUrl).toExternalForm());
//            mediaPlayer = createMediaPlayer(media1);
        toggleMusic(musicInfo, true);
    }

    /**
     *  mediaPlayer
     *
     * @param media
     * @return
     */
    private MediaPlayer createMediaPlayer(Media media) {
        if (mediaPlayer != null) mediaPlayer.dispose();
        this.mediaPlayer = null;

        MediaPlayer mediaPlayer = new MediaPlayer(media);

        slider.setValue(0);
        startLabel.setText("00:00");

        soundSlider.setValue(volume.getValue());

        mediaPlayer.volumeProperty().bind(soundSlider.valueProperty().divide(100));
        mediaPlayer.currentTimeProperty().addListener(timeListener());
        mediaPlayer.setOnEndOfMedia(() -> toggleOneMusic(ToggleMusicConstant.NEXT_ONE));

        //
        mediaPlayer.setOnReady(() -> {
            Duration stopTime = mediaPlayer.getStopTime();
            double time = Double.parseDouble(String.format("%.2f", stopTime.toSeconds()));
            slider.setMax(time);
            int min = (int) (time / 60);
            double sec = time % 60;
            String secStr = sec == 0 ? "00" : (sec < 10 ? "0" + sec : "" + sec);
            endLabel.setText("0" + min + ":" + secStr.substring(0, 2));

            
        });
        return mediaPlayer;
    }

    /**
     * 
     */
    private void togglePlayImage() {
        Image image;
        if (isPlay) {
            image = new Image("/image/play_bar/play.png");
        } else {
            image = new Image("/image/play_bar/pause.png");
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        playButton.setGraphic(imageView);
    }

    private Media getMedia(String path) {
        if (fileMp3Server.isJar()) {
            path = String.format("jar:file:%s!%s", fileMp3Server.getJarPath(), path);
            return new Media(path);
        } else {
            return new Media(FileMp3ServerImpl.class.getResource(path).toExternalForm());
        }
    }

    private void initMediaPlayer() throws IOException {
        musicInfo = fileMp3Server.getMusicInfoList("/music").get(0);
        Media media = getMedia(musicInfo.getMusicPath());
        mediaPlayer = createMediaPlayer(media);
    }
    
    
    /**
     * 
     */
    @FXML
    public void toClientClick(ActionEvent event) {
        Director performance = Director.getInstance();
        performance.toClient();
    }


   
}


