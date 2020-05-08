package com.example.cutlery.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cutlery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.MenuModel;

public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.ViewHolder> {
    private List<MenuModel> list;

    public BreakfastAdapter(List<MenuModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BreakfastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breakfast_card, parent, false);
        return new BreakfastAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastAdapter.ViewHolder viewHolder, int position) {
        String name = list.get(position).getName();
        String image = list.get(position).getImage();
        int price = list.get(position).getPrice();
        viewHolder.setMenu(name, image, price);
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            price = itemView.findViewById(R.id.price);

        }

        public void setMenu(String name, String image, int price) {
            textView.setText(name);
            Picasso.get().load(image).centerCrop().fit().into(imageView);
            textView.setText(price);
        }
    }
}