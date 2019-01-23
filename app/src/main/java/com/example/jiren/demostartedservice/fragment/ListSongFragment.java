package com.example.jiren.demostartedservice.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.jiren.demostartedservice.interface_app.OnFragmentInteractionListener;
import com.example.jiren.demostartedservice.MainActivity;
import com.example.jiren.demostartedservice.R;
import com.example.jiren.demostartedservice.adapter.ListSongAdapter;
import com.example.jiren.demostartedservice.object.Song;
import java.util.List;

public class ListSongFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ListView mListView;
    private ListSongAdapter mListSongAdapter;
    private List<Song> listSongs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_songs_fragment, container, false);
        mListView = view.findViewById(R.id.lv_ListSong);
        listSongs=MainActivity.data;
        mListSongAdapter=new ListSongAdapter(R.layout.song_item_layout,getActivity(),listSongs);
        mListView.setAdapter(mListSongAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onFragmentInteraction(position);
            }
        });

        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(
                    context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



}
