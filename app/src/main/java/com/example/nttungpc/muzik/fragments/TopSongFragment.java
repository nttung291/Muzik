package com.example.nttungpc.muzik.fragments;


import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nttungpc.muzik.R;
import com.example.nttungpc.muzik.adapters.TopSongAdapter;
import com.example.nttungpc.muzik.database.MusicTypeModel;
import com.example.nttungpc.muzik.database.TopSongModel;
import com.example.nttungpc.muzik.events.OnClickMusicTypeEvent;
import com.example.nttungpc.muzik.network.json_model.TopSongResponseJSON;
import com.example.nttungpc.muzik.network.retrofit.GetTopSongService;
import com.example.nttungpc.muzik.network.retrofit.RetrofitFactory;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;
import jp.wasabeef.recyclerview.animators.FlipInLeftYAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment {
    @BindView(R.id.toolbar) 
    Toolbar tbTopSongs;
    @BindView(R.id.iv_favorite)
    ImageView ivFavorite;
    @BindView(R.id.tv_musictype)
    TextView tvMusicType;
    @BindView(R.id.iv_topsongs)
    ImageView ivMusicType;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.rv_topsongs)
    RecyclerView rvTopSOngs;
    @BindView(R.id.av_loading)
    AVLoadingIndicatorView avloading;

    private MusicTypeModel musicTypeModel;
    private TopSongAdapter topSongAdapter;
    private List<TopSongModel> topSongModels = new ArrayList<>();
    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_song, container, false);
        ButterKnife.bind(this,view);
        EventBus.getDefault().register(this);
        setupUI();
        loadData();
        return view;
    }

    private void loadData() {
        GetTopSongService getTopSongService = RetrofitFactory
                .getInstance().create(GetTopSongService.class);
        getTopSongService.getTopSongs(musicTypeModel.getId()).enqueue(new Callback<TopSongResponseJSON>() {
            public static final String TAG = "";

            @Override
            public void onResponse(Call<TopSongResponseJSON> call, Response<TopSongResponseJSON> response) {
                avloading.hide();
                List<TopSongResponseJSON.FeedJSON.EntryJSON> entryJSONList = response.body().getFeedJSON().getEntry();
                for (int i=0;i<entryJSONList.size();i++){
                    TopSongResponseJSON.FeedJSON.EntryJSON entryJSON = entryJSONList.get(i);
                    String song = entryJSON.getNameJSON().getLabel();
                    String artist = entryJSON.getArtistJSON().getLabel();
                    String smallImage = entryJSON.getImageJSONList().get(2).getLabel();
                    TopSongModel topSongModel = new TopSongModel(song,artist,smallImage);
                    topSongModels.add(topSongModel);
                    topSongAdapter.notifyItemChanged(i);
                }
//                topSongAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TopSongResponseJSON> call, Throwable t) {

            }
        });

    }

    @Subscribe(sticky = true)
    public void onEventMusicTypeClicked(OnClickMusicTypeEvent onClickMusicTypeEvent){
        musicTypeModel = onClickMusicTypeEvent.getMusicTypeModel();
    }

    private void setupUI() {
        avloading.show();
        tbTopSongs.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        tbTopSongs.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        tvMusicType.setText(musicTypeModel.getKey());
        Picasso.with(getContext()).load(musicTypeModel.getImageID()).into(ivMusicType);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0){
                   tbTopSongs.setBackground(getResources().getDrawable(R.drawable.gradient2));
                }else{
                    tbTopSongs.setBackground(null);
                }
            }
        });

        topSongAdapter = new TopSongAdapter(getContext(),topSongModels);
        rvTopSOngs.setAdapter(topSongAdapter);
        rvTopSOngs.setItemAnimator(new SlideInLeftAnimator());
        rvTopSOngs.getItemAnimator().setAddDuration(300);
        rvTopSOngs.setLayoutManager(new LinearLayoutManager(getContext()));
//        rvTopSOngs.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

}
