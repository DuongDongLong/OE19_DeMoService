package com.example.jiren.demostartedservice.object;

import android.graphics.Bitmap;

public class Song {

    public Song(String songName, String singer, Bitmap imgSong) {
        this.songName = songName;
        this.singer = singer;
        this.imgSong = imgSong;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Bitmap getImgSong() {
        return imgSong;
    }

    public void setImgSong(Bitmap imgSong) {
        this.imgSong = imgSong;
    }

    private String songName,singer;
    private Bitmap imgSong;
}
