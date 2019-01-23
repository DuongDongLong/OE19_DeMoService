package com.example.jiren.demostartedservice.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.example.jiren.demostartedservice.interface_app.ChangViewPager;
import com.example.jiren.demostartedservice.MainActivity;
import com.example.jiren.demostartedservice.R;
import com.example.jiren.demostartedservice.adapter.SongRecyclerViewAdapter;
import com.example.jiren.demostartedservice.interface_app.NextBackMedia;
import com.example.jiren.demostartedservice.object.Song;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

public class PlayFragment extends Fragment implements View.OnClickListener,NextBackMedia {
    private static final String TAG = "PlayFragment";
    private static final String ARG_PARAM1 = "param1";
    CarouselLayoutManager layoutManager;
    private int mParam1;
    private CircleImageView mCircleImageView;
    private ObjectAnimator mObjectAnimator;
    private RecyclerView mRecyclerView;
    private SongRecyclerViewAdapter mAdapter;
    private List<Song> mListSong;
    private ChangViewPager mChangViewPager;
    TextView tv_Singer,tv_Author;
    public static PlayFragment newInstance(int param1) {
        Log.d(TAG, "newInstance: ");
        PlayFragment fragment = new PlayFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mParam1 = getArguments().getInt(ARG_PARAM1);
            Log.d(TAG, "onCreate: "+mParam1);
            }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_fragment, container, false);
        mListSong=MainActivity.data;
        init(view);
        initAnimation();
        initListSong();
        return view;
    }

    private void initListSong() {
        mAdapter=new SongRecyclerViewAdapter(mListSong,getContext());
        layoutManager= new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new CenterScrollListener());
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        mRecyclerView.setAdapter(mAdapter);
        layoutManager.scrollToPosition(mParam1);
        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager
                .OnCenterItemSelectionListener() {
            @Override
            public void onCenterItemChanged(int adapterPosition) {
                if (adapterPosition!=mParam1) {
                    mParam1=adapterPosition;
                    mChangViewPager.setOnChangItem(mParam1);
                    mCircleImageView.setImageBitmap(mListSong.get(mParam1).getImgSong());
                    tv_Singer.setText(mListSong.get(mParam1).getSongName());
                    tv_Author.setText(mListSong.get(mParam1).getSinger());
                }
            }
        });
    }

    private void init(View view) {
        mCircleImageView = view.findViewById(R.id.image_circle);
        mCircleImageView.setImageBitmap(mListSong.get(mParam1).getImgSong());
        mRecyclerView=view.findViewById(R.id.rv_ImgSong);
        tv_Singer = view.findViewById(R.id.tv_PlaySinger);
        tv_Singer.setText(mListSong.get(mParam1).getSongName());
        tv_Author = view.findViewById(R.id.tv_PlayAuthor);
        tv_Author.setText(mListSong.get(mParam1).getSinger());
    }

    public void initAnimation() {
        mObjectAnimator = ObjectAnimator.ofFloat(mCircleImageView, "rotation", 0f, 360f);
        mObjectAnimator.setDuration(11000);
        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimator.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mObjectAnimator.start();
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChangViewPager) {
            mChangViewPager = (ChangViewPager) context;
        } else {
            throw new RuntimeException(
                    context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mChangViewPager = null;
    }

    @Override
    public void onChangerSong(int value) {
        Log.d(TAG, "onChangerSong: "+value);
        //layoutManager.scrollToPosition(value);
    }
    public void changScoll(int value){
        layoutManager.scrollToPosition(value);
    }
}
