package com.example.cutlery.Controller.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cutlery.Controller.ForgotPassword;
import com.example.cutlery.Controller.LoginActivity;
import com.example.cutlery.Controller.RegisterActivity;
import com.example.cutlery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;


public class MyAccountFragment extends Fragment {

    private EditText email_account, password_account;
    private Button delete_account;
    String userEmail;

    public MyAccountFragment() {
        // Required empty public constructor
    }


    public static MyAccountFragment newInstance(String param1, String param2) {
        MyAccountFragment fragment = new MyAccountFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root= inflater.inflate(R.layout.fragment_my_account, container, false);

        email_account=root.findViewById(R.id.email_account);
        password_account=root.findViewById(R.id.password_account);
        delete_account=root.findViewById(R.id.delete_account);

        //display user email
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
             userEmail = user.getEmail();
        }
        email_account.setText(userEmail, TextView.BufferType.EDITABLE);

        //change password
        password_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CharSequence[] options = new CharSequence[]
                        {
                                "Change password"
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        getActivity().startActivity(new Intent(getActivity(), ForgotPassword.class));

                    }
                });
                builder.show();

            }
        });

        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user != null) {
                 user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Your account has been successfully deleted.", Toast.LENGTH_LONG).show();
                                    getActivity().startActivity(new Intent(getActivity(), RegisterActivity.class));
                                    getActivity().finish();
                                }
                                else {
                                    Toast.makeText(getActivity(), "Account could not be deactivated", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
            }
        });

        return root;
    }
}