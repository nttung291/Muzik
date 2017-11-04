package com.example.nttungpc.muzik.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.nttungpc.muzik.R;
import com.example.nttungpc.muzik.database.TopSongModel;
import com.example.nttungpc.muzik.events.OnTopSongEvent;
import com.example.nttungpc.muzik.utils.MusicHandle;
import com.squareup.picasso.Picasso;
import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListener;
import com.thin.downloadmanager.ThinDownloadManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainSongFragment extends Fragment {
    @BindView(R.id.im_download)
    ImageView ivDownload;
    @BindView(R.id.tv_name_mainsong)
    TextView tvSong;
    @BindView(R.id.tv_artist_mainsong)
    TextView tvArtist;
    @BindView(R.id.im_play_mainsong)
    ImageView ivPlaySong;
    @BindView(R.id.time_end_mainsong)
    TextView tvTimeEnd;
    @BindView(R.id.time_start_mainsong)
    TextView tvTimeStart;
    @BindView(R.id.fb_play_mainsong)
    FloatingActionButton fabPlay;
    @BindView(R.id.iv_next_mainsong)
    ImageView ivNextSong;
    @BindView(R.id.iv_previous_mainsong)
    ImageView ivPreviousSong;
    @BindView(R.id.sb_mainsong)
    SeekBar sbPlay;
    @BindView(R.id.tb)
    android.support.v7.widget.Toolbar toolbar;
    private TopSongModel topSongModel;

    public MainSongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_song,container,false);
        setUI(view);
        EventBus.getDefault().register(this);
        return view;
    }
    @Subscribe(sticky = true)
    public void onReceiveTopSong(OnTopSongEvent onTopSongEvent){
        topSongModel = onTopSongEvent.getTopSongModel();
        tvSong.setText(topSongModel.getSong());
        tvArtist.setText(topSongModel.getArtist());
        Picasso.with(getContext()).load(topSongModel.getLargeImage())
                .transform(new CropCircleTransformation())
                .into(ivPlaySong);
        MusicHandle.updateRealTime(sbPlay,fabPlay,ivPlaySong,tvTimeStart,tvTimeEnd);
    }
    private void setUI(View view) {
        ButterKnife.bind(this,view);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        fabPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MusicHandle.playPause();
            }
        });

        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThinDownloadManager downloadManager = new ThinDownloadManager();
                Uri downloadUri = Uri.parse(topSongModel.getUrl());
                Uri destinationUri = Uri.parse(getContext().getExternalFilesDir("") + "/" + topSongModel.getSong()+ "-" +topSongModel.getArtist()+ ".mp3");
                DownloadRequest downloadRequest = new DownloadRequest(downloadUri)
                        .setRetryPolicy(new DefaultRetryPolicy())
                        .setDestinationURI(destinationUri)
                        .setPriority(DownloadRequest.Priority.HIGH)
                        .setDownloadContext("Download")
                        .setDownloadListener(new DownloadStatusListener() {
                            @Override
                            public void onDownloadComplete(int id) {

                            }

                            @Override
                            public void onDownloadFailed(int id, int errorCode, String errorMessage) {

                            }

                            @Override
                            public void onProgress(int id, long totalBytes, long downloadedBytes, int progress) {

                            }
                        });
                downloadManager.add(downloadRequest);
            }
        });
    }
}
