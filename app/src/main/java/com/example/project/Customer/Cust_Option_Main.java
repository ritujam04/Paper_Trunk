package com.example.project.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Cust_Option_Main extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    RecyclerView rv;
    List_Adapter list_adapter;
    List<com.example.project.Customer.List>list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_option_main);
        firebaseDatabase=FirebaseDatabase.getInstance();
        rv=findViewById(R.id.rv1);


        list=new ArrayList<>();
        firebaseDatabase.getReference().child("CustomerRV").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot:snapshot.getChildren())
                    {
                        com.example.project.Customer.List list1=dataSnapshot.getValue(com.example.project.Customer.List.class);
                        list.add(list1);
                       list1.setId(dataSnapshot.getKey());
                    }
                    list_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*list.add(new com.example.project.Customer.List("Sell the Blank Pages",R.drawable.p1));
        list.add(new com.example.project.Customer.List("Sell the Used Pages/Books",R.drawable.p3));
        list.add(new com.example.project.Customer.List("Reprocess Blank Pages",R.drawable.p2));
        list.add(new com.example.project.Customer.List("Want used Pages Reusable",R.drawable.p4));*/
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
       list_adapter=new List_Adapter(this,list);
        rv.setHasFixedSize(true);
        rv.setAdapter(list_adapter);

    }
}