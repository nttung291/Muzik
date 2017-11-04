package com.example.nttungpc.muzik.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nttungpc.muzik.R;
import com.example.nttungpc.muzik.adapters.TopSongAdapter;
import com.example.nttungpc.muzik.database.TopSongModel;
import com.example.nttungpc.muzik.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {
    private static final String TAG = "";
    @BindView(R.id.rv_download)
    RecyclerView rvDownloadSong;
    public List<TopSongModel> topSongModels = new ArrayList<>();
    TopSongAdapter topSongAdapter;
    public DownloadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        loadData();
        setupUI(view);
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this,view);
        topSongAdapter = new TopSongAdapter(getContext(),topSongModels);
        rvDownloadSong.setAdapter(topSongAdapter);
        rvDownloadSong.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDownloadSong.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    private void loadData() {
        topSongModels.clear();
        File[] files =  getContext().getExternalFilesDir("").listFiles();
        for (File file : files){
            TopSongModel topSongModel = Utils.convertSong(file.getName(),file.getAbsolutePath());
            if (topSongModel != null){
                topSongModels.add(topSongModel);
            }
        }
    }

}
