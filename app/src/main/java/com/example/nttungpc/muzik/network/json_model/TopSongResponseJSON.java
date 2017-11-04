package com.example.nttungpc.muzik.network.json_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nttung PC on 10/24/2017.
 */

public class TopSongResponseJSON {
    public FeedJSON feed;

    public FeedJSON getFeedJSON() {
        return feed;
    }

    public class FeedJSON {
        List<EntryJSON> entry;

        public List<EntryJSON> getEntry() {
            return entry;
        }

        public class EntryJSON {
            @SerializedName("im:name")
            public NameJSON nameJSON;

            public NameJSON getNameJSON() {
                return nameJSON;
            }

            public class NameJSON {
                public String label;

                public String getLabel() {
                    return label;
                }
            }

            @SerializedName("im:image")
            public List<ImageJSON> imageJSONList;

            public List<ImageJSON> getImageJSONList() {
                return imageJSONList;
            }

            public class ImageJSON {
                public String label;

                public String getLabel() {
                    return label;
                }
            }

            @SerializedName("im:artist")
            public ArtistJSON artistJSON;

            public ArtistJSON getArtistJSON() {
                return artistJSON;
            }

            public class ArtistJSON {
                public String label;

                public String getLabel() {
                    return label;
                }
            }
        }

    }
}
