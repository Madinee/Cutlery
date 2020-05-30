package com.example.cutlery.Controller.Adapter;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cutlery.Controller.BreakfastActivity;
import com.example.cutlery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.CartModel;
import Model.MenuModel;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<CartModel> list;


    public CartAdapter(List<CartModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);

        return new CartAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder viewHolder, int position) {
       // String category = list.get(position).getCategory();
        String name=list.get(position).getName();
        int price=list.get(position).getPrice();
        int quantity=list.get(position).getQuantity();
       // viewHolder.setMenu(category);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView m_name, price, quantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            m_name = (TextView) itemView.findViewById(R.id.menu_name);
            price = (TextView) itemView.findViewById(R.id.menu_price);
            quantity = (TextView) itemView.findViewById(R.id.menu_quantity);





        }
        public void setMenu(final String category, String image) {
            m_name.setText(category);
            price.setText(category);
            quantity.setText(category);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }
}
