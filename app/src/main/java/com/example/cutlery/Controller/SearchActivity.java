package com.example.cutlery.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.cutlery.R;

public class SearchActivity extends AppCompatActivity {
    private TextView not_found;
   private  RecyclerView recyclerView_search;
   private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //find view
        searchView=findViewById(R.id.searchView);
        recyclerView_search=findViewById(R.id.recyclerview_search);
        not_found=findViewById(R.id.not_found);

        recyclerView_search.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView_search.setLayoutManager(linearLayoutManager);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}