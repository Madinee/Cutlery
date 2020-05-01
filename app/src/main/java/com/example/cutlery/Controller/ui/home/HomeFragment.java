package com.example.cutlery.Controller.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cutlery.Controller.MenuAdapter;
import com.example.cutlery.R;

import java.util.ArrayList;
import java.util.List;

import Model.MenuModel;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private List<MenuModel> menuList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_nav_home_menu, container, false);
        recyclerView = root.findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        menuList.add(new MenuModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fnewyork.seriouseats.com%2Fimages%2F20120221-baobq-specials-1.jpg&f=1&nofb=1", "dessert"));
        menuList.add(new MenuModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fnewyork.seriouseats.com%2Fimages%2F20120221-baobq-specials-1.jpg&f=1&nofb=1", "dessert"));
        menuList.add(new MenuModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fnewyork.seriouseats.com%2Fimages%2F20120221-baobq-specials-1.jpg&f=1&nofb=1", "dessert"));
        menuList.add(new MenuModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fnewyork.seriouseats.com%2Fimages%2F20120221-baobq-specials-1.jpg&f=1&nofb=1", "dessert"));
        menuList.add(new MenuModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fnewyork.seriouseats.com%2Fimages%2F20120221-baobq-specials-1.jpg&f=1&nofb=1", "dessert"));


        MenuAdapter menuAdapter=new MenuAdapter(menuList);
        recyclerView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
        return root;
    }
}
