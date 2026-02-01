package com.example.project.Customer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.MyViewHolder> {
    Context context;
    List<Location> recycler_listList;

    public Recycler_Adapter(Context context, List<Location> recycler_listList) {
        this.context = context;
        this.recycler_listList = recycler_listList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cust_recycler_shop_info,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Location rl=recycler_listList.get(position);
        holder.name.setText((rl.getName()));
        holder.contact.setText(rl.getContact());
        holder.location.setText(rl.getLocation());
        String imageuri=null;
        imageuri=rl.getImup();
        Picasso.get().load(imageuri).into(holder.image);
        Log.d("AXE10", "Images place: "+imageuri);
    }

    @Override
    public int getItemCount() {
        return recycler_listList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,contact,location;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textView);
            contact=itemView.findViewById(R.id.textView2);
            location=itemView.findViewById(R.id.textView3);
            image=itemView.findViewById(R.id.imageView);
        }
    }
}
