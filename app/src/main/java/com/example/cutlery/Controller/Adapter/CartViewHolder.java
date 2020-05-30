package com.example.cutlery.Controller.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cutlery.Controller.Interface.ItemClickListner;
import com.example.cutlery.R;



    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         public TextView textView_name, textView_price, textView_quantity;
        private ItemClickListner itemClickListner;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = (TextView) itemView.findViewById(R.id.menu_name);
            textView_price = (TextView) itemView.findViewById(R.id.menu_price);
            textView_quantity = (TextView) itemView.findViewById(R.id.menu_quantity);


        }
       /*public void setMenu(final String name, int price, String quantity) {
            textView_name.setText(name);
            textView_price.setText(String.valueOf(price)+"€");
            textView_quantity.setText(quantity);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }*/
       public void setItemClickListner(ItemClickListner itemClickListner)
       {
           this.itemClickListner = itemClickListner;
       }

        @Override
        public void onClick(View v) {
            itemClickListner.onClick(v, getAdapterPosition(), false);
        }
    }


