package com.maliknajeeb.khalihaan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;


public class OpenFragment extends Fragment {



    public OpenFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_open, container, false);
        TextView tv = view.findViewById(R.id.hehe);
        Bundle bd = this.getArguments();
        String name = bd.get("name").toString();
        String photo =bd.getString("image");
        tv.setText(name);
        ImageView imageView = view.findViewById(R.id.expandPhoto);

        Glide.with(getContext()).load(photo).into(imageView);



        return view;
    }
}