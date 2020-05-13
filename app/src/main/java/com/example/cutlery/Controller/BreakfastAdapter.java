package com.example.cutlery.Controller;

import android.content.Intent;
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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastAdapter.ViewHolder viewHolder, int position) {
        String name = list.get(position).getName();
        String image = list.get(position).getImage();
        int price = list.get(position).getPrice();
        viewHolder.setMenu(image, name, price);
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private TextView textViewprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.name);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textViewprice = itemView.findViewById(R.id.price);

        }

        public void setMenu(final String image, final String name, final int price) {
            Picasso.get().load(image).centerCrop().fit().into(imageView);
            textViewprice.setText(String.valueOf(price)+"€");
            textView.setText(name);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("image", image);
                    intent.putExtra("price", price);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}