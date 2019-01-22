package com.example.jiren.demostartedservice.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jiren.demostartedservice.R;

public class RootFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rootfragment, container, false);
        getFragmentManager().beginTransaction().add(R.id.root_frame_layout,new EmplyFragment()).commit();
        return view;
    }
}
