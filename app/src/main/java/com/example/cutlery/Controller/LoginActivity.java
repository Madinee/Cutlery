package com.example.cutlery.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cutlery.R;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button button_login;
    private TextView sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sign_up=findViewById(R.id.sign_up);
        button_login=findViewById(R.id.button_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
