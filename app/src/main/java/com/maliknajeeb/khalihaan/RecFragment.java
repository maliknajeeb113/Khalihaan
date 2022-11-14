package com.maliknajeeb.khalihaan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class RecFragment extends Fragment {

    private RecyclerView recview;
    private myadapter adapter;


    public RecFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rec, container, false);

        // Add the following lines to create RecyclerView
        recview = view.findViewById(R.id.recview);
        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(view.getContext()));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("plants"), model.class)
                        .build();

        adapter=new myadapter(options);
        recview.setAdapter(adapter);



        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }





    }
