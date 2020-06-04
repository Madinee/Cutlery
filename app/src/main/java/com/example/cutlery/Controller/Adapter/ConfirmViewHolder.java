package com.example.cutlery.Controller.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cutlery.Controller.Interface.ItemClickListner;
import com.example.cutlery.R;

public class ConfirmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textView_name_c, textView_price_c, textView_quantity_c;
    private ItemClickListner itemClickListner;


    public ConfirmViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_name_c = (TextView) itemView.findViewById(R.id.menu_name_c);
        textView_price_c = (TextView) itemView.findViewById(R.id.menu_price_c);
        textView_quantity_c = (TextView) itemView.findViewById(R.id.menu_quantity_c);
    }
    public void setItemClickListner(ItemClickListner itemClickListner)
    {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v, getAdapterPosition(), false);
    }
}