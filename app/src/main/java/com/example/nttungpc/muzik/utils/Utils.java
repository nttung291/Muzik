package com.example.nttungpc.muzik.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.nttungpc.muzik.database.TopSongModel;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nttung PC on 10/17/2017.
 */

public class Utils {
    public static void openFragment(FragmentManager fragmentManager, int layoutID, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void rotateImage(ImageView imageView,boolean isRotate){
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(15000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        if (isRotate){
           if (imageView.getAnimation() == null){
               imageView.startAnimation(rotateAnimation);
           }
        }else{
            imageView.setAnimation(null);
        }
    }

    public static String convertTime(long milis){
        long min =  TimeUnit.MILLISECONDS.toMinutes(milis);
        return String.format("%02d:%02d",
                min,
                TimeUnit.MILLISECONDS.toSeconds(milis-min*60*1000));
    }

    public static TopSongModel convertSong(String s, String path){
       if (s!=null){
           for (int i=0;i<s.length();i++){
               if (s.charAt(i) == '-'){
                   TopSongModel topSongModel = new TopSongModel();
                   String url = path;
                   String song = s.substring(0,i);
                   String artist = s.substring(i+1,s.length()-4);
                   topSongModel.setSong(song);
                   topSongModel.setArtist(artist);
                   topSongModel.setUrl(url);
                   topSongModel.setSmallImage(null);
                   topSongModel.setLargeImage(null);
                   return  topSongModel;
               }
           }

       }
        return null;
    }

}
