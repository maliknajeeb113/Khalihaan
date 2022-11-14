package com.maliknajeeb.khalihaan;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model)
    {   
        holder.name.setText(model.getName());
        holder.sciname.setText(model.getSciname());
        holder.type.setText(model.getType());
        Glide.with(holder.img.getContext()).load(model.getPhoto()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bd =new Bundle();
                bd.putString("name", model.getName().toString());
                bd.putString("sciname", model.getSciname().toString());
                bd.putString("image",model.getPhoto());

                OpenFragment openFragment = new OpenFragment();
                openFragment.setArguments(bd);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,openFragment).addToBackStack(null).commit();



            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView name,sciname,type;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            sciname=(TextView)itemView.findViewById(R.id.scinametext);
            type=(TextView)itemView.findViewById(R.id.typetext);
        }
    }
}
