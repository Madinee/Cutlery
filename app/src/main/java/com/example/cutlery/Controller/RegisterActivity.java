package com.example.cutlery.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cutlery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {
    private EditText userEmail, userPassword, userName;
    private Button button_register;

    private TextView sign_in;
    private FirebaseAuth firebaseAuth;
    //String name, email, password;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        userName = findViewById(R.id.username);
        button_register = findViewById(R.id.button_register);
        sign_in = findViewById(R.id.sign_in);

        progressDialog = new ProgressDialog(this);
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
                Register();

            }
        });
    }

    private void Register() {

        String user_email = userEmail.getText().toString().trim();
        String user_password = userPassword.getText().toString().trim();

        if (userName.getText().toString().isEmpty() || user_email.isEmpty() || user_password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter all the details", Toast.LENGTH_SHORT).show();

        } else {

            progressDialog.setMessage("Registering Please Wait...");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        //sendEmailVerification();
                        Toast.makeText(RegisterActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                    } else {

                        Toast.makeText(getApplicationContext(), "registration failed", Toast.LENGTH_SHORT).show();

                    }
                    progressDialog.dismiss();

                }
            });
        }

    }
}
