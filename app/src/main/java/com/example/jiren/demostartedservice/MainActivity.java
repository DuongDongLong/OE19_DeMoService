package com.example.jiren.demostartedservice;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.jiren.demostartedservice.adapter.ViewPagerAdapter;
import com.example.jiren.demostartedservice.fragment.ListSongFragment;
import com.example.jiren.demostartedservice.fragment.PlayFragment;
import com.example.jiren.demostartedservice.object.Song;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements ListSongFragment.OnFragmentInteractionListener, View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static boolean SHUFFLE = false;
    private static boolean PAUSE = false;
    private static boolean REPEAT = false;
    private static int position;
    public static List<Song> data = new ArrayList<>();
    public static List<File> paths = new ArrayList<>();
    MediaPlayer mMediaPlayer;
    SeekBar mSeekBar;
    Uri song;
    private TextView txtStart, txtStop;
    private ImageView imgShuffle, imgBack, imgRestart, imgNext, imgRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListFile();
        initListSong();
        initView();
        initEven();
    }

    private void initListFile() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";
        File file = new File(path);
        File[] files = file.listFiles();
        String name;
        for (File file1 : files) {
            name = file1.getName();
            if (name.endsWith(".mp3")) {
                paths.add(file1);
            }
        }
    }

    private void initListSong() {

        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        String title, singer;
        Bitmap img;
        for (int i = 0; i < paths.size(); i++) {
            mmr.setDataSource(paths.get(i).getAbsolutePath());
            title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            singer = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            byte [] datas = mmr.getEmbeddedPicture();
            img=BitmapFactory.decodeByteArray(datas, 0,datas.length );
            data.add(new Song(title, singer,img));
        }
    }

    private void initEven() {
        imgShuffle.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        imgRestart.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        imgRepeat.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mMediaPlayer != null)
                    mMediaPlayer.seekTo(seekBar.getProgress());
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (REPEAT) {
                            mMediaPlayer.start();
                            timeSong();
                            updateTime();
                        } else {
                            nextSong();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        imgShuffle = findViewById(R.id.img_Shuffle);
        imgBack = findViewById(R.id.img_Back);
        imgRestart = findViewById(R.id.img_Restart);
        imgNext = findViewById(R.id.img_Next);
        imgRepeat = findViewById(R.id.img_Repeat);
        ViewPager viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        mSeekBar = findViewById(R.id.sb_Process);
        txtStart = findViewById(R.id.tv_Start);
        txtStop = findViewById(R.id.tv_Stop);
    }

    public void timeSong() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("mm:ss");
        if (mMediaPlayer != null) {
            txtStop.setText(simpleDateFormat.format(mMediaPlayer.getDuration()));
            mSeekBar.setMax(mMediaPlayer.getDuration());
        }
    }

    public void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mMediaPlayer != null) {
                    mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat =
                            new SimpleDateFormat("mm:ss");
                    txtStart.setText(simpleDateFormat.format(mMediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                }
            }
        }, 300);
    }

    @Override
    public void onFragmentInteraction(int value1) {
        position = value1;
        changerSong(position);
    }

    public void changerSong(int position) {

        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
        PAUSE = false;
        imgRestart.setImageResource(R.drawable.pause_w);
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.root_frame_layout,
                PlayFragment.newInstance(position));
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.commit();
        song = Uri.parse(paths.get(position).toString());
        mMediaPlayer = MediaPlayer.create(this, song);
        mMediaPlayer.start();
        timeSong();
        updateTime();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_Shuffle:
                Log.d(TAG, "onClick: ");
                if (!SHUFFLE) {
                    if (REPEAT) {
                        imgRepeat.setImageResource(R.drawable.repeat_w);
                        REPEAT = false;
                    }
                    imgShuffle.setImageResource(R.drawable.shuffle2);
                    SHUFFLE = true;
                } else {
                    imgShuffle.setImageResource(R.drawable.shuffle_w);
                    SHUFFLE = false;
                }
                break;

            case R.id.img_Back:
                if (SHUFFLE) {
                    Random random = new Random();
                    position = random.nextInt(data.size() - 1);
                    Log.d(TAG, "onClick: " + position);
                    changerSong(position);
                }
                if (position > 0) {
                    position--;
                    changerSong(position);
                }
                break;
            case R.id.img_Restart:
                if (PAUSE) {
                    imgRestart.setImageResource(R.drawable.pause_w);
                    mMediaPlayer.start();
                    PAUSE = false;
                } else {
                    imgRestart.setImageResource(R.drawable.play_w);
                    mMediaPlayer.pause();
                    PAUSE = true;
                }
                break;
            case R.id.img_Next:
                nextSong();
                break;
            case R.id.img_Repeat:
                Log.d(TAG, "onClick: ");
                if (!REPEAT) {
                    if (SHUFFLE) {
                        imgShuffle.setImageResource(R.drawable.shuffle_w);
                        SHUFFLE = false;
                    }
                    imgRepeat.setImageResource(R.drawable.repeat2);
                    REPEAT = true;
                } else {
                    imgRepeat.setImageResource(R.drawable.repeat_w);
                    REPEAT = false;
                }
                break;
        }
    }

    private void nextSong() {
        if (SHUFFLE) {
            Random random = new Random();
            position = random.nextInt(data.size() - 1);
            Log.d(TAG, "onClick: " + position);
            changerSong(position);
        }
        if (position < data.size() - 1) {
            position++;
            changerSong(position);
        }
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        mMediaPlayer.stop();
        super.onPause();
    }
}
