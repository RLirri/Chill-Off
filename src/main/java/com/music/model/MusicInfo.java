package com.music.model;

/**
 *
 * @author Huang Ruixin
 */

public class MusicInfo {

    String musicName;

    String musicPath;

    public MusicInfo() {
    }

    public MusicInfo(String musicName, String musicPath) {
        this.musicName = musicName;
        this.musicPath = musicPath;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }
}
