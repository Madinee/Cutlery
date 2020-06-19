package com.example.cutlery.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cutlery.Controller.Adapter.BreakfastAdapter;
import com.example.cutlery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Model.MenuModel;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SearchActivity extends AppCompatActivity {
    private TextView not_found;
   private  RecyclerView recyclerView_search;
   private SearchView searchView;

   //ici j'utilise le meme model et adapter aue celui de l'affichage du breakfast
    private List<MenuModel> searchList = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore;
    BreakfastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //find view
        searchView=findViewById(R.id.searchView);
        recyclerView_search=findViewById(R.id.recyclerview_search);

        recyclerView_search.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView_search.setLayoutManager(linearLayoutManager);

        adapter =  new BreakfastAdapter(searchList);
        recyclerView_search.setAdapter(adapter);


                firebaseFirestore=FirebaseFirestore.getInstance();
                firebaseFirestore.collection("MENU")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){

                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        searchList.add(new MenuModel(document.get("image").toString(), document.get("name").toString(), ((Long)document.get("price")).intValue()));
                                    }
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Log.w(TAG, "Error getting documents.", task.getException());
                                }
                            }
                        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMenu(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchMenu(newText);
                return false;
            }
        });



    }

    private void searchMenu(String recherche) {
        if (recherche.length() > 0)
            recherche = recherche.substring(0, 1).toUpperCase() + recherche.substring(1).toLowerCase();

        ArrayList<MenuModel> results = new ArrayList<>();
        for(MenuModel model : searchList){
            if(model.getName() != null && model.getName().contains(recherche)){
                results.add(model);
            }
            adapter =  new BreakfastAdapter(results);
            recyclerView_search.setAdapter(adapter);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}