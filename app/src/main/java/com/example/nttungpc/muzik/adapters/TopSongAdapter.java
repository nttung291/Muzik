package com.example.nttungpc.muzik.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nttungpc.muzik.R;
import com.example.nttungpc.muzik.database.TopSongModel;
import com.example.nttungpc.muzik.events.OnTopSongEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by Nttung PC on 10/24/2017.
 */

public class TopSongAdapter extends RecyclerView.Adapter<TopSongAdapter.TopSongViewHolder> {
    private Context context;
    private List<TopSongModel> topSongModels;

    public TopSongAdapter(Context context, List<TopSongModel> topSongModels) {
        this.context = context;
        this.topSongModels = topSongModels;
    }

    @Override
    public TopSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_top_song,parent,false);
        return new TopSongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopSongViewHolder holder, int position) {
        holder.setData(topSongModels.get(position));
    }

    @Override
    public int getItemCount() {
        return topSongModels.size();
    }

    public class TopSongViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_top_song)
        ImageView imageView;
        @BindView(R.id.tv_topsong_name)
        TextView tvSong;
        @BindView(R.id.tv_topsong_artist)
        TextView tvArtist;
        View view;

        public TopSongViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            view = itemView;
        }

        public void setData(final TopSongModel topSongModel){
            if (topSongModel.getSmallImage() != null){
                Picasso.with(context).load(topSongModel.getSmallImage())
                        .transform(new CropCircleTransformation())
                        .into(imageView);
            }else{
                Picasso.with(context).load(R.drawable.genre_x2_25)
                        .transform(new CropCircleTransformation())
                        .into(imageView);
            }
            tvSong.setText(topSongModel.getSong());
            tvArtist.setText(topSongModel.getArtist());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().postSticky(new OnTopSongEvent(topSongModel));
                }
            });
        }
    }
}
