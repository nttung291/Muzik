package com.example.nttungpc.muzik.adapters;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nttungpc.muzik.R;
import com.example.nttungpc.muzik.database.MusicTypeModel;
import com.example.nttungpc.muzik.events.OnClickMusicTypeEvent;
import com.example.nttungpc.muzik.fragments.TopSongFragment;
import com.example.nttungpc.muzik.utils.Utils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Nttung PC on 10/15/2017.
 */

public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.MusicTypeViewHolder> {
    private List<MusicTypeModel> musicTypeModels;
    private Context context;
    public MusicTypeAdapter(List<MusicTypeModel> musicTypeModels,Context context) {
        this.musicTypeModels = musicTypeModels;
        this.context = context;
    }

    @Override
    public MusicTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.item_music_type,parent,false);
        return new MusicTypeViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MusicTypeViewHolder holder, int position) {
        holder.setData(musicTypeModels.get(position));
    }

    @Override
    public int getItemCount() {
        return musicTypeModels.size();
    }

    class MusicTypeViewHolder extends RecyclerView.ViewHolder{
        ImageView imMusicType;
        TextView tvMusicType;
        View view;
        public MusicTypeViewHolder(View itemView) {
            super(itemView);
            imMusicType = itemView.findViewById(R.id.iv_music_type);
            tvMusicType = itemView.findViewById(R.id.tv_music_type);
            view = itemView;
        }

        public void setData(final MusicTypeModel musicTypeModel){
            Picasso.with(context).load(musicTypeModel.getImageID()).into(imMusicType);
            tvMusicType.setText(musicTypeModel.getKey());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().postSticky(new OnClickMusicTypeEvent(musicTypeModel));
                    Utils.openFragment(((FragmentActivity) context).getSupportFragmentManager(),
                            R.id.layout_container,new TopSongFragment()
                    );
                }
            });
        }
    }
}
