package com.example.project.Shopkeeper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Customer.Cust_Option_Main;
import com.example.project.Customer.Cust_Option_Main2;
import com.example.project.Customer.List;
import com.example.project.Customer.Location;
import com.example.project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class Shop_login extends AppCompatActivity {
    Spinner spinner;
    EditText name, contact, location;
    Button save;
    FirebaseAuth firebaseAuth;
    FirebaseStorage mStorage;
    ProgressDialog progressDialog;
    FirebaseDatabase firebaseDatabase;
    ImageView imup;
    private static final int Gallery_code = 1;
    Uri imageUrl = null;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    ArrayList<String> id1;
    ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_login);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        location= findViewById(R.id.location);
        spinner = findViewById(R.id.spinner);
        save = findViewById(R.id.save);
        imup = findViewById(R.id.imup);
        progressBar=findViewById(R.id.progressBar2);
        progressDialog=new ProgressDialog(this);
        mStorage=FirebaseStorage.getInstance();
        imup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery_code);
            }
        });
        list = new ArrayList<>();
        list.add("Select Option");
        id1 = new ArrayList<>();
        id1.add("temple Id");
        adapter = new ArrayAdapter<String>(Shop_login.this, android.R.layout.simple_spinner_dropdown_item, list);
        firebaseDatabase.getReference().child("CustomerRV").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    List l = dataSnapshot.getValue(List.class);
                    list.add(l.getName());
                    l.setId(dataSnapshot.getKey());
                    id1.add(l.getId());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        spinner.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Gallery_code && requestCode == RESULT_OK) {
            imageUrl = data.getData();
            imup.setImageURI(imageUrl);
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String fn=name.getText().toString();
                String dc=contact.getText().toString();
                String dn=location.getText().toString();
                String tn=spinner.getSelectedItem().toString();
                final String image = imageUrl != null ? imageUrl.toString() : null;
                if (!(fn.isEmpty() && dc.isEmpty() && dn.isEmpty() && tn.equals("Select Option")&& image.isEmpty())) {
                    progressDialog.setTitle("uploading...");
                    progressDialog.show();
                    Uri uri = data.getData();
                    StorageReference filepath = mStorage.getReference().child("imagePost").child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadurl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t=task.getResult().toString();
                                    String id = firebaseDatabase.getReference().push().getKey();
                                    Location loc = new Location(name.getText().toString(), contact.getText().toString(), location.getText().toString(),  spinner.getSelectedItem().toString(),task.getResult().toString());
                                    firebaseDatabase.getReference().child("Location").child(id).setValue(loc);
                                    firebaseDatabase.getReference().child("CustomerRV").child(id1.get(spinner.getSelectedItemPosition())).child("Location").child(id).setValue(loc);
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(Shop_login.this, "Added Successfully...", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(Shop_login.this, "All Fields Are Required...", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}