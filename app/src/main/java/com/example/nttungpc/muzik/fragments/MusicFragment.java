package com.example.nttungpc.muzik.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nttungpc.muzik.R;
import com.example.nttungpc.muzik.adapters.MusicTypeAdapter;
import com.example.nttungpc.muzik.database.MusicTypeModel;
import com.example.nttungpc.muzik.network.retrofit.GetTypeMusicService;
import com.example.nttungpc.muzik.network.json_model.MainObjectJSON;
import com.example.nttungpc.muzik.network.retrofit.RetrofitFactory;
import com.example.nttungpc.muzik.network.json_model.SubgenresJSON;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {
    private RecyclerView rvMusicType;
    private List<MusicTypeModel> musicTypeModelList = new ArrayList<>();
    MusicTypeAdapter musicTypeAdapter;

    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        loadData();
        setUI(view);
        return view;
    }

    private void loadData() {
        if (musicTypeModelList.size() == 0){
            GetTypeMusicService getTypeMusicService = RetrofitFactory.getInstance()
                    .create(GetTypeMusicService.class);
            final Context context = getContext();
            getTypeMusicService.getMusicType().enqueue(new Callback<MainObjectJSON>() {
                @Override
                public void onResponse(Call<MainObjectJSON> call, Response<MainObjectJSON> response) {
                    List<SubgenresJSON> subgenresJSONs = response.body().getSubgenresJSONs();
                    for (int i=0;i<subgenresJSONs.size();i++){
                        SubgenresJSON subgenresJSON = subgenresJSONs.get(i);
                        MusicTypeModel musicTypeModel = new MusicTypeModel();
                        musicTypeModel.setId(subgenresJSON.getId());
                        musicTypeModel.setKey(subgenresJSON.getTranslation_key());
                        musicTypeModel.setImageID(context.getResources()
                                .getIdentifier("genre_x2_" + subgenresJSON.getId(),"raw",context.getPackageName()));
                        musicTypeModelList.add(musicTypeModel);
                        musicTypeAdapter.notifyItemChanged(i);
                    }


                }

                @Override
                public void onFailure(Call<MainObjectJSON> call, Throwable t) {
                    Toast.makeText(context, "No Connection", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setUI(View view) {
        rvMusicType = view.findViewById(R.id.rv_music);
        musicTypeAdapter = new MusicTypeAdapter(musicTypeModelList,getContext());
        rvMusicType.setAdapter(musicTypeAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // Neu chia het cho 3 thi chiem 2 o nguoc lai chiem 1 o
                return position%3 == 0 ? 2 : 1;
            }
        });

        rvMusicType.setLayoutManager(gridLayoutManager);
        rvMusicType.setItemAnimator(new SlideInUpAnimator());
        rvMusicType.getItemAnimator().setAddDuration(300);
    }

}
