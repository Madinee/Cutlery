package com.example.cutlery.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cutlery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {
    private EditText useremail, userpassword, username;
    private Button button_register;

    private TextView sign_in;
    private ProgressBar loading;
    private FirebaseAuth firebaseAuth;
    String name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        useremail = findViewById(R.id.email);
        userpassword = findViewById(R.id.password);
        username = findViewById(R.id.username);
        button_register = findViewById(R.id.button_register);
        loading = findViewById(R.id.loading);
        sign_in = findViewById(R.id.sign_in);

        firebaseAuth = FirebaseAuth.getInstance();


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Register()) {
                    //Upload data to the database
                    String user_email = useremail.getText().toString().trim();
                    String user_password = userpassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                //sendEmailVerification();
                                Toast.makeText(RegisterActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                            } else {

                                Toast.makeText(getApplicationContext(), "registration failed", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
            }
        });
    }
        private Boolean Register(){
            Boolean result = false;
            loading.setVisibility(View.VISIBLE);
            button_register.setVisibility(View.GONE);

            if (username.getText().toString().isEmpty() || useremail.getText().toString().isEmpty() || userpassword.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter all the details", Toast.LENGTH_SHORT).show();

            } else {
                result = true;
            }
            return result;
        }

    }



