package com.example.nttungpc.muzik.network.json_model;

/**
 * Created by nttungPC on 10/29/2017.
 */

public class SearchSongJSON {
    private DataJSON data;

    public DataJSON getData() {
        return data;
    }
    public class DataJSON{
        private String url;
        private String thumbnail;

        public String getUrl() {
            return url;
        }

        public String getThumbnail() {
            return thumbnail;
        }
    }
}
