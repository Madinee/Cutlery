package com.example.cutlery.Controller.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cutlery.Controller.BreakfastAdapter;
import com.example.cutlery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Model.MenuModel;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class DinnerFragment extends Fragment {
    private RecyclerView recyclerView_breakfast;
    private List<MenuModel> breakfastList = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore;
    BreakfastAdapter breakfastAdapter;

    public DinnerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_dinner, container, false);
        recyclerView_breakfast = root.findViewById(R.id.recyclerview_breakfast);
        GridLayoutManager gridlayout=new GridLayoutManager(getContext(),2);
        recyclerView_breakfast.setLayoutManager(gridlayout);

        breakfastAdapter=new BreakfastAdapter(breakfastList);
        recyclerView_breakfast.setAdapter(breakfastAdapter);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("MENU")
                .whereEqualTo("category", "Dinner")
                .orderBy("name").get()
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
        return root;
    }
}
