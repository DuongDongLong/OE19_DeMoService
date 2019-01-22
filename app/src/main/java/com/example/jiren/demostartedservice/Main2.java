package com.example.jiren.demostartedservice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.example.jiren.demostartedservice.adapter.SongRecyclerViewAdapter;
import com.example.jiren.demostartedservice.object.Song;
import java.util.ArrayList;
import java.util.List;

public class Main2 extends AppCompatActivity {
    List<Integer> mSongs=new ArrayList<>();
    SongRecyclerViewAdapter mSongRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView recyclerView=findViewById(R.id.recyclerViewTest);
        mSongs.add(R.drawable.download);
        mSongs.add(R.drawable.music);
        mSongs.add(R.drawable.download);
        mSongs.add(R.drawable.music);
        mSongs.add(R.drawable.download);
        mSongs.add(R.drawable.music);
        mSongs.add(R.drawable.download);
        mSongs.add(R.drawable.music);
       // mSongRecyclerViewAdapter=new SongRecyclerViewAdapter(mSongs,this);
        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new CenterScrollListener());
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        recyclerView.setAdapter(mSongRecyclerViewAdapter);
        layoutManager.scrollToPosition(1);
        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager
                .OnCenterItemSelectionListener() {
            @Override
            public void onCenterItemChanged(int adapterPosition) {
                Log.e("xxx","con cac");
            }
        });
    }
}
