package com.upworkapp.upworkapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerLoginActivity extends AppCompatActivity {

    private EditText eEmail,ePassword;
    private Button bLogin,bRegistration;

    private FirebaseAuth sAuth;
    private FirebaseAuth.AuthStateListener fireBaseListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        sAuth = FirebaseAuth.getInstance();

        fireBaseListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null) {
                    Intent intDriver = new Intent(CustomerLoginActivity.this, MapActivity.class);
                    startActivity(intDriver);
                }
            }
        };

        eEmail = findViewById(R.id.textEmail);
        ePassword = findViewById(R.id.textPassword);
        bLogin = findViewById(R.id.buttonLogin);
        bRegistration = findViewById(R.id.buttonRegistration);

        bRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = eEmail.getText().toString(), password = ePassword.getText().toString();
                sAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(CustomerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isComplete()) {
                            Toast.makeText(CustomerLoginActivity.this,"Sign up error.",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String userID = sAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDB = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID);
                            currentUserDB.setValue(true);
                        }
                    }
                });
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = eEmail.getText().toString(), password = ePassword.getText().toString();
                sAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(CustomerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isComplete()) {
                            Toast.makeText(CustomerLoginActivity.this,"Sign up error.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        sAuth.addAuthStateListener(fireBaseListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sAuth.addAuthStateListener(fireBaseListener);
    }
}