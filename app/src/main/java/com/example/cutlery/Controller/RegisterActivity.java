package com.example.cutlery.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cutlery.R;


public class RegisterActivity extends AppCompatActivity {
    private EditText email, password, username, confirmPassword;
    private Button button_register;
    private TextView sign_up;
    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }
}
