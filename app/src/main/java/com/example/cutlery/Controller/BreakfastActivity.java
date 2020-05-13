package com.example.cutlery.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cutlery.Controller.Fragment.AppetizerFragment;
import com.example.cutlery.Controller.Fragment.BreakfastFragment;
import com.example.cutlery.Controller.Fragment.DessertFragment;
import com.example.cutlery.Controller.Fragment.DinnerFragment;
import com.example.cutlery.Controller.Fragment.LunchFragment;
import com.example.cutlery.R;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import Model.MenuModel;

public class BreakfastActivity extends AppCompatActivity {
    private String title;
    FrameLayout mainframLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        //ajout du toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


//permet d'aller sur les differentes pages de menu(breakfas, lunch, dessert etc) enfonction de la valeur de stringextra
        mainframLayout=findViewById(R.id.mainframLayout);
        title=getIntent().getStringExtra("category");
        assert title != null;

        switch (title) {
            case "Breakfast":
                setFragment(new BreakfastFragment());
                getSupportActionBar().setTitle(title);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                break;
            case "Lunch":
                setFragment(new LunchFragment());
                getSupportActionBar().setTitle(title);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                break;
            case "Appetizers":
                setFragment(new AppetizerFragment());
                getSupportActionBar().setTitle(title);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                break;
            case "Dinner":
                setFragment(new DinnerFragment());
                getSupportActionBar().setTitle(title);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                break;
            case "Dessert":
                setFragment(new DessertFragment());
                getSupportActionBar().setTitle(title);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }
        else if(id==R.id.filter){
            return true;
        }
        else if(id==R.id.cart){
            return true;
        }

        return super.onOptionsItemSelected(item);
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
