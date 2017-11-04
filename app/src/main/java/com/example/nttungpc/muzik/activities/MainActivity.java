package com.example.nttungpc.muzik.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.nttungpc.muzik.adapters.ViewPagerAdapter;
import com.example.nttungpc.muzik.R;
import com.example.nttungpc.muzik.database.MusicTypeModel;
import com.example.nttungpc.muzik.database.TopSongModel;
import com.example.nttungpc.muzik.events.OnClickMusicTypeEvent;
import com.example.nttungpc.muzik.events.OnTopSongEvent;
import com.example.nttungpc.muzik.fragments.MainSongFragment;
import com.example.nttungpc.muzik.fragments.TopSongFragment;
import com.example.nttungpc.muzik.utils.MusicHandle;
import com.example.nttungpc.muzik.utils.Utils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.example.nttungpc.muzik.utils.MusicHandle.searchSong;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String TAG = "AAA";
    private TopSongModel topSongModel;
    @BindView(R.id.layout_mini)
    RelativeLayout rlMini;
    @BindView(R.id.iv_top_song)
    ImageView ivTopSong;
    @BindView(R.id.tv_topsong_name)
    TextView tvSong;
    @BindView(R.id.tv_topsong_artist)
    TextView tvArtist;
    @BindView(R.id.seekbar)
    SeekBar seekBar;
    @BindView(R.id.fb_mini)
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        MusicTypeModel musicTypeModel = new MusicTypeModel();
        musicTypeModel.setKey("ALL");
        musicTypeModel.setImageID(R.raw.genre_x2_);
        musicTypeModel.setId("");
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void onRecievedTopSongEvent(OnTopSongEvent topSongEvent){
        topSongModel = topSongEvent.getTopSongModel();
        if (topSongModel.getSmallImage() != null){
            Picasso.with(this).load(topSongModel.getSmallImage())
                    .transform(new CropCircleTransformation())
                    .into(ivTopSong);
        }else{
            Picasso.with(this).load(R.drawable.genre_x2_25)
                    .transform(new CropCircleTransformation())
                    .into(ivTopSong);
        }
        tvSong.setText(topSongModel.getSong());
        tvArtist.setText(topSongModel.getArtist());
        searchSong(topSongModel,this);
        MusicHandle.updateRealTime(seekBar,floatingActionButton,ivTopSong,null,null);
        rlMini.setVisibility(View.VISIBLE);
    }


    private void setUI() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        ButterKnife.bind(this);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicHandle.playPause();
            }
        });

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_dashboard_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_file_download_black_24dp));
        tabLayout.getTabAt(0).getIcon().setAlpha(255);
        tabLayout.getTabAt(1).getIcon().setAlpha(100);
        tabLayout.getTabAt(2).getIcon().setAlpha(100);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // An 2 lan lien tuc vao tab
            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        rlMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openFragment(getSupportFragmentManager(),
                        R.id.ll_container,
                        new MainSongFragment());
            }
        });
    }
}
