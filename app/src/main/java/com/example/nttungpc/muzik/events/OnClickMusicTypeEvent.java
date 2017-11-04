package com.example.nttungpc.muzik.events;

import com.example.nttungpc.muzik.database.MusicTypeModel;

/**
 * Created by Nttung PC on 10/17/2017.
 */

public class OnClickMusicTypeEvent {
    private MusicTypeModel musicTypeModel;

    public OnClickMusicTypeEvent(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }

    public MusicTypeModel getMusicTypeModel() {
        return musicTypeModel;
    }
}
