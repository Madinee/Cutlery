package com.example.cutlery.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cutlery.R;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuDetailActivity extends AppCompatActivity {

    private String name, image;
    private int price;
    TextView  menu_price, menu_name;
    ImageView menu_image;

    Button reservation, add_to_cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        //find view
        menu_image=findViewById(R.id.menu_image);
        menu_name=findViewById(R.id.menu_name);
        menu_price=findViewById(R.id.menu_price);

        reservation=findViewById(R.id.reservation);
        add_to_cart=findViewById(R.id.add_to_cart);

        //ajout du toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        //recuperation et affichage des élements
        name=getIntent().getStringExtra("name");
        menu_name.setText(name);

        price=getIntent().getIntExtra("price", 22);
        menu_price.setText(price + "€");

        image=getIntent().getStringExtra("image");
        menu_image.setImageURI(Uri.parse(image));
        Picasso.get().load(image).centerCrop().fit().into(menu_image);

        //set toolbar
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //go to cart and reservation page
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuDetailActivity.this, "Your menu is add to cart, check your cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.filter).setVisible(false);
        menu.findItem(R.id.search).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.cart) {
            Intent intent = new Intent(MenuDetailActivity.this, CartActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("price", price);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
