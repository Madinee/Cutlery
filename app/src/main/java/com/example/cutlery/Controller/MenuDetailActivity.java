package com.example.cutlery.Controller;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.cutlery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class MenuDetailActivity extends AppCompatActivity {

    private String name, image;
    private int price;
    TextView  menu_price, menu_name;
    ImageView menu_image;
    private ElegantNumberButton menu_quantity;
    Button reservation, add_to_cart;
    final FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
//        final FirebaseAuth auth = FirebaseAuth.getInstance();
        //find view
        menu_image=findViewById(R.id.menu_image);
        menu_name=findViewById(R.id.menu_name);
        menu_price=findViewById(R.id.menu_price);
        menu_quantity=findViewById(R.id.menu_quantity);

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
                addingToCartList();
            }
        });

    }

    private void addingToCartList() {

//        final FirebaseUser user =  auth.currentUser();
        final FirebaseUser user =  auth.getCurrentUser();
        String uid = user.getUid();
        System.out.println("uid:"+uid);

        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        saveCurrentDate = currentDate.format(calForDate.getTime());
        System.out.println("date:"+saveCurrentDate);
        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference("CART");

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("name", name);
        cartMap.put("price", price);
        cartMap.put("quantity", menu_quantity.getNumber());

        cartListRef.child(uid)
                .child(saveCurrentDate)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MenuDetailActivity.this, "Your menu is add to cart, check your cart", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

//    Future<String> getUid() async {
//        ExecutorService asyncExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());        final FirebaseUser user = await FirebaseAuth.instance.currentUser();
//
//        final FirebaseUser user = FirebaseAuth.instance.currentUser();
//        final String uid = user.uid.toString();
//        return uid;
//    }


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
