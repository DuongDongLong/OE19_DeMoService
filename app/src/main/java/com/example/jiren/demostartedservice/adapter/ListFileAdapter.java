package com.example.jiren.demostartedservice.adapter;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.jiren.demostartedservice.R;
import com.example.jiren.demostartedservice.object.Song;
import java.io.File;
import java.util.List;

public class ListFileAdapter extends BaseAdapter {
    public ListFileAdapter(int mlayout, Context context, List<File> path) {
        this.mlayout = mlayout;
        mContext = context;
        this.path = path;
    }

    private int mlayout;
    private Context mContext;
    private List<File> path;
    @Override
    public int getCount() {
        return path.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(mlayout,null);

        TextView tvSinger=convertView.findViewById(R.id.tv_Singer);
        TextView tvAuthor=convertView.findViewById(R.id.tv_Author);

        MediaMetadataRetriever mmr=new MediaMetadataRetriever();
        mmr.setDataSource(path.get(position).getAbsolutePath());
        String title =mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String singer=mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        tvSinger.setText(title);
        tvAuthor.setText(singer);
        return convertView;
    }
}
