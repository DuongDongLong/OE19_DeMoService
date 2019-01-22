package com.example.jiren.demostartedservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.jiren.demostartedservice.R;
import com.example.jiren.demostartedservice.object.Song;
import java.util.List;

public class ListSongAdapter extends BaseAdapter {
    private int mlayout;
    private Context mContext;
    private List<Song> arrItemObjects;

    public ListSongAdapter(int layout, Context context, List<Song> arrItemObjects) {
        this.mlayout = layout;
        mContext = context;
        this.arrItemObjects = arrItemObjects;
    }

    @Override
    public int getCount() {
        return arrItemObjects.size();
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
        tvSinger.setText(arrItemObjects.get(position).getSongName());
        tvAuthor.setText(arrItemObjects.get(position).getSinger());
        return convertView;
    }
}
