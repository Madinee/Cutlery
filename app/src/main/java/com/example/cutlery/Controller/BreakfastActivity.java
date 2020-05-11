package com.example.cutlery.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cutlery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import Model.MenuModel;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class BreakfastActivity extends AppCompatActivity {
    private RecyclerView recyclerView_breakfast;
    private List<MenuModel> breakfastList = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore;
    private String title;
    BreakfastAdapter breakfastAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        //ajout du toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);



        recyclerView_breakfast = findViewById(R.id.recyclerview_breakfast);
        GridLayoutManager gridlayout=new GridLayoutManager(this,2);
        recyclerView_breakfast.setLayoutManager(gridlayout);

        breakfastAdapter=new BreakfastAdapter(breakfastList);
        recyclerView_breakfast.setAdapter(breakfastAdapter);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("MENU").orderBy("name").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                breakfastList.add(new MenuModel(document.get("image").toString(), document.get("name").toString(), ((Long)document.get("price")).intValue()));
                            }
                            breakfastAdapter.notifyDataSetChanged();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

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
}
