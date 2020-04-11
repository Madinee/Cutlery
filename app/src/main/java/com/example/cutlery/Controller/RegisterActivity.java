package com.example.cutlery.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cutlery.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {
    private EditText email, password, username, confirmPassword;
    private Button button_register;
    private TextView sign_in;
    private ProgressBar loading;
    private static String URL_REGIST="https://127.0.0.1/cultery/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        username=findViewById(R.id.username);
        button_register=findViewById(R.id.button_register);
        loading=findViewById(R.id.loading);
        sign_in=findViewById(R.id.sign_in);
        confirmPassword=findViewById(R.id.confirmPassword);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Regist();
            }
        });
    }
    private  void Regist(){
        loading.setVisibility(View.VISIBLE);
        button_register.setVisibility(View.GONE);

        final String username=this.username.getText().toString().trim();
        final String email=this.email.getText().toString().trim();
        final String password=this.password.getText().toString().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject= new JSONObject(response);
                        String success=jsonObject.getString("success");
                        if(success.equals("1")){
                            Toast.makeText(RegisterActivity.this, "register succes", Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this, "register error " +e.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        button_register.setVisibility(View.VISIBLE);
                        }
                }
            },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(RegisterActivity.this, "register error" +error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        button_register.setVisibility(View.VISIBLE);
                    }
                })

    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError{
            Map<String, String> params=new HashMap<>();
            params.put("username", username);
            params.put("email", email);
            params.put("password", password);
            return super.getParams();
        }
    };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
