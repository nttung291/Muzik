package com.example.nttungpc.muzik.events;

import com.example.nttungpc.muzik.database.TopSongModel;

/**
 * Created by nttungPC on 10/29/2017.
 */

public class OnTopSongEvent  {
    private TopSongModel topSongModel;

    public OnTopSongEvent(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public TopSongModel getTopSongModel() {
        return topSongModel;
    }

    public void setTopSongModel(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }
}
