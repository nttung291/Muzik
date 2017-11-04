package com.example.nttungpc.muzik.network.json_model;

import java.util.List;

/**
 * Created by Nttung PC on 10/12/2017.
 */

public class MainObjectJSON {
    List<SubgenresJSON> subgenres;

    public List<SubgenresJSON> getSubgenresJSONs(){
        return subgenres;
    }

    public void setSubgenresJSONs(List<SubgenresJSON> subgenresJSONs){
        this.subgenres = subgenresJSONs;
    }
}
