package com.example.project.Customer;

import android.content.Context;
import android.content.Intent;
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

public class List_Adapter extends RecyclerView.Adapter<List_Adapter.ListViewHolder> {
    Context context;
    private final List<com.example.project.Customer.List>list;

    public List_Adapter(Context context, List<com.example.project.Customer.List> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public List_Adapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.demofile,null);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull List_Adapter.ListViewHolder holder, int position) {
        com.example.project.Customer.List l=list.get(position);
        //  holder.iv.setImageResource(l.getImage());
        holder.textView9.setText(l.getName());
        Picasso.get()
                .load(l.getImage())
                .into(holder.iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(context, Cust_Recycler.class);
                in.putExtra("locationId",l.getId());
               // in.putExtra("iv",l.getImage());
                //in.putExtra("textView9",l.getName());
               // in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView textView9;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            textView9=itemView.findViewById(R.id.textView9);
        }
    }
}
