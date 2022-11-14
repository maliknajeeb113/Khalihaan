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
        ImageView imageView = view.findViewById(R.id.expandPhoto);
        TextView tv2 = view.findViewById(R.id.sciName);
        TextView tv3 = view.findViewById(R.id.typeData);
        TextView tv4 = view.findViewById(R.id.descData);



        Bundle bd = this.getArguments();
        String name = bd.getString("name");
        String photo =bd.getString("photo");
        String sciname = bd.getString("sciname");
        String type = bd.getString("type");
        String desc = bd.getString("desc");

        tv.setText(name);
        tv2.setText(sciname);
        Glide.with(getContext()).load(photo).into(imageView);
        tv3.setText(type);
        tv4.setText(desc);




        return view;
    }
}