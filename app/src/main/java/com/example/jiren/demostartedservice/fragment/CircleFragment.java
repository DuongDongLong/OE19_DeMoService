package com.example.jiren.demostartedservice.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import com.example.jiren.demostartedservice.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class CircleFragment extends Fragment {
    private CircleImageView mCircleImageView;
    private ObjectAnimator mObjectAnimator;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.img_circle_fragment,container,false);
        mCircleImageView=view.findViewById(R.id.image_circle);
        mObjectAnimator=ObjectAnimator.ofFloat(mCircleImageView,"rotation",0f,360f);
        mObjectAnimator.setDuration(10000);
        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimator.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        return view;
    }
}
