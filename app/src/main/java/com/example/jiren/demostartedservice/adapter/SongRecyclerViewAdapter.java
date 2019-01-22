package com.example.jiren.demostartedservice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jiren.demostartedservice.R;
import com.example.jiren.demostartedservice.object.Song;
import java.util.List;
import java.util.zip.Inflater;

public class SongRecyclerViewAdapter
        extends RecyclerView.Adapter<SongRecyclerViewAdapter.ViewHolder> {
    private List<Song> data;
    private Context mContext;


    public SongRecyclerViewAdapter(List<Song> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View studentView = inflater.inflate(R.layout.song_item_recycle_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mImageView.setImageBitmap(data.get(position).getImgSong());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            mImageView = itemView.findViewById(R.id.imgTest);
        }

    }


}