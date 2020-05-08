package com.example.cutlery.Controller.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cutlery.Controller.BreakfastAdapter;
import com.example.cutlery.R;

import java.util.ArrayList;
import java.util.List;

import Model.MenuModel;


public class BreakfastFragment extends Fragment {
    private RecyclerView recyclerView_breakfast;
    private List<MenuModel> breakfastList = new ArrayList<>();
  /*  @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View root= inflater.inflate(R.layout.fragment_breakfast, container, false);
        recyclerView_breakfast = root.findViewById(R.id.recyclerview_breakfast);
        GridLayoutManager gridlayout=new GridLayoutManager(getActivity(),2);
        recyclerView_breakfast.setLayoutManager(gridlayout);

        breakfastList.add(new MenuModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fnewyork.seriouseats.com%2Fimages%2F20120221-baobq-specials-1.jpg&f=1&nofb=1", "dessert", 22));
        breakfastList.add(new MenuModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fnewyork.seriouseats.com%2Fimages%2F20120221-baobq-specials-1.jpg&f=1&nofb=1", "dessert", 12));
        breakfastList.add(new MenuModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fnewyork.seriouseats.com%2Fimages%2F20120221-baobq-specials-1.jpg&f=1&nofb=1", "dessert", 22));
        breakfastList.add(new MenuModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fnewyork.seriouseats.com%2Fimages%2F20120221-baobq-specials-1.jpg&f=1&nofb=1", "dessert", 22));
        breakfastList.add(new MenuModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fnewyork.seriouseats.com%2Fimages%2F20120221-baobq-specials-1.jpg&f=1&nofb=1", "dessert", 22));


        BreakfastAdapter breakfastAdapter=new BreakfastAdapter(breakfastList);
        recyclerView_breakfast.setAdapter(breakfastAdapter);
        breakfastAdapter.notifyDataSetChanged();
        return root;
    }
}
