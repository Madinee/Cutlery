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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button button_login;
    private TextView sign_up, forgotPassword;
    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sign_up=findViewById(R.id.sign_up);
        button_login=findViewById(R.id.button_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        forgotPassword=findViewById(R.id.forgotPassword);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
       /*if(user!=null){
            finish();ss
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }*/

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();

            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


    private void Login(){

        String user_email = email.getText().toString().trim();
        String user_password = password.getText().toString().trim();

        if (user_email.isEmpty() || user_password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter all the details", Toast.LENGTH_SHORT).show();

        } else {
            progressDialog.setMessage("Login Please Wait...");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        checkEmail();
                    } else {

                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();

                    }
                    progressDialog.dismiss();

                }
            });
        }


    }
    /*
    verification de l'email si l'utilisateur existe deja
     */
    private void checkEmail(){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

       if(emailflag){
           finish();
           startActivity(new Intent(LoginActivity.this, MainActivity.class));
       }else{
           Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
           firebaseAuth.signOut();
       }
    }
}
