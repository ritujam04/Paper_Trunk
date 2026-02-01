package com.example.project.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cust_validation extends AppCompatActivity {
TextView textView8;
Button button5;
TextInputLayout layout1, layout2;
TextInputEditText username, password;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_validation);
        textView8=findViewById(R.id.textView8);
        button5=findViewById(R.id.button5);
        layout1=findViewById(R.id.layout1);
        layout2=findViewById(R.id.layout2);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password1();
                String user=layout1.getEditText().getText().toString();
                String pass=layout2.getEditText().getText().toString();

                mAuth=FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(Cust_validation.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            layout1.getEditText().setText("");
                            layout2.getEditText().setText("");
                            Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                        }else{
                            layout1.getEditText().setText("");
                            layout2.getEditText().setText("");
                            Toast.makeText(getApplicationContext(), "Process Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private boolean password1()
    {
        String s=layout1.getEditText().getText().toString();
        String val="^"+
                "(?=.*[a-zA-Z])"+
                "(?=.*[@#$%^&*])"+
                "(?=\\S+$)"+
                ".{4,}"+
                "$";
        if (s.isEmpty())
        {
            layout2.setError("Need to be Filled!!");
            return false;
        } else if (!s.matches(val)) {
            layout2.setError("Password is too weak!!");
            return false;
        }else{
            layout2.setError(null);
            layout2.setErrorEnabled(false);
            return false;
        }
    }
   /* private boolean username1(){
        String s=layout1.getEditText().getText().toString();
        String ows="\\A\\w{4,20}\\z";
        if (s.isEmpty()){
            layout1.setError("Needs to be filled!!");
            return false;
        } else if (s.length()>10) {
            layout1.setError("Username too Long!!");
            return false;
        } else if (!s.matches(ows)) {
            layout1.setError("Spaces are not filled!!!");
            return false;
        } else {
            layout1.setError(null);
            layout1.setErrorEnabled(false);
            return true;
        }
    }*/
}