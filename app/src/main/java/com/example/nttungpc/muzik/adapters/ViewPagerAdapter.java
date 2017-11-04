package com.example.nttungpc.muzik.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nttungpc.muzik.fragments.DownloadFragment;
import com.example.nttungpc.muzik.fragments.FavoriteFragment;
import com.example.nttungpc.muzik.fragments.MusicFragment;

/**
 * Created by Nttung PC on 10/15/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MusicFragment();
            case 1:
                return new FavoriteFragment();
            case 2:
                return new DownloadFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
