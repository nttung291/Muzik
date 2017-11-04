package com.example.nttungpc.muzik.database;

/**
 * Created by Nttung PC on 10/24/2017.
 */

public class TopSongModel {
    private String song;
    private String artist;
    private String smallImage;
    private String url;
    private String largeImage;

    public TopSongModel() {
    }

    public TopSongModel(String song, String artist, String smallImage) {
        this.song = song;
        this.artist = artist;
        this.smallImage = smallImage;
    }

    public String getSong() {
        return song;
    }

    public String getArtist() {
        return artist;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public String getUrl() {
        return url;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }
}
