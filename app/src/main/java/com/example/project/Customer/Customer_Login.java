package com.example.project.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Customer_Login extends AppCompatActivity {
Button button;
TextView textView4, textView6;
EditText editText, editText2;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        button=findViewById(R.id.button);
        textView4=findViewById(R.id.textView4);
        textView6=findViewById(R.id.textView6);
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        firebaseAuth=FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editText.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(editText.getText().toString(),editText2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent in=new Intent(Customer_Login.this, Cust_Option_Main.class);
                                startActivity(in);
                            }else{
                                Toast.makeText(Customer_Login.this, "Please Check Username and Password...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(Customer_Login.this, "All Fields are Required!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Customer_Login.this, Cust_validation.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a=new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(a);
    }
}