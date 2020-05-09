package com.example.cutlery.Controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cutlery.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.List;

import Model.MenuModel;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<MenuModel> list;


    public MenuAdapter(List<MenuModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_card,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder viewHolder, int position) {
        String category = list.get(position).getCategory();
        String image=list.get(position).getImage();
        viewHolder.setMenu(category, image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            textView.setTypeface(null, Typeface.BOLD);
            imageView = (ImageView) itemView.findViewById(R.id.image);

            //framlatout


        }
        public void setMenu(final String category, String image) {
            textView.setText(category);
            if (!image.equals("null")) {
                Picasso.get().load(image).centerCrop().fit().into(imageView);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (category.equals("Breakfast")) {
                        Intent intent = new Intent(itemView.getContext(), BreakfastActivity.class);
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }

    }
}


