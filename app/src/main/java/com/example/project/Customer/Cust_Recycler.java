package com.example.project.Customer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.example.project.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class Cust_Recycler extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    RecyclerView recyclerview1;
    Recycler_Adapter adapter;
    List<Location> recyclerLists;
    String LocationId;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_recycler);
        LocationId=getIntent().getStringExtra("locationId");
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("CustomerRV");
        storage=FirebaseStorage.getInstance();
        recyclerview1=findViewById(R.id.recyclerview1);

        recyclerLists=new ArrayList<Location>();

        initComponents();

        database.getReference().child("CustomerRV").child(LocationId).child("Location").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    for (DataSnapshot dataSnapshot:snapshot.getChildren())
                    {
                        Location location =dataSnapshot.getValue(Location.class);
                        location.setId(dataSnapshot.getKey());
                        recyclerLists.add(location);
                    }
                    adapter.notifyDataSetChanged();

                }
                else {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initComponents() {
        recyclerview1.setHasFixedSize(true);
        recyclerview1.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Recycler_Adapter(Cust_Recycler.this,recyclerLists);
        recyclerview1.setAdapter(adapter);
    }
}