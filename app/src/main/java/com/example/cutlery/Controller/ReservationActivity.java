package com.example.cutlery.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cutlery.Controller.Fragment.CartFragment;
import com.example.cutlery.Controller.Fragment.ConfirmationFragment;
import com.example.cutlery.Controller.Fragment.ReservationFragment;
import com.example.cutlery.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class ReservationActivity extends AppCompatActivity {
    FrameLayout mainframLayout;
    Button confirm_reservation_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);


        //ajout du toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        mainframLayout=findViewById(R.id.mainframLayout);

        setFragment(new ReservationFragment());
        getSupportActionBar().setTitle("Reservation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //find confirmation btn
       // confirm_reservation_btn = findViewById(R.id.confirm_reservation_btn);
//        confirm_reservation_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setFragment(new ConfirmationFragment());
//                getSupportActionBar().setTitle("Congratulation");
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            }
//        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(mainframLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

}