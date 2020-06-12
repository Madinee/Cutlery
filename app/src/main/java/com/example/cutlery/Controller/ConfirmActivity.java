package com.example.cutlery.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cutlery.Controller.Adapter.ConfirmViewHolder;
import com.example.cutlery.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

import Model.CartModel;

public class ConfirmActivity extends AppCompatActivity {

    private  String name_v, phone_number_v, address_v, numberpeople_v, date_v, time_v;
    final FirebaseAuth auth = FirebaseAuth.getInstance();
    private RecyclerView recyclerView_cart;
    private TextView name, number_people, phone, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        //ajout du toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Congratulation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //find view
        name=findViewById(R.id.name);
        number_people=findViewById(R.id.number_people);
        phone=findViewById(R.id.phone);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);

        //get extra

        name_v=getIntent().getStringExtra("name");
        phone_number_v=getIntent().getStringExtra("phone");
        address_v=getIntent().getStringExtra("email");
        numberpeople_v=getIntent().getStringExtra("numberPeople");
        date_v=getIntent().getStringExtra("date");
        time_v=getIntent().getStringExtra("time");



        System.out.println("namev"+name_v);

        //set text

        name.setText("Name: "+name_v);
        phone.setText("Phone number: "+phone_number_v);
        number_people.setText("Number of people: "+numberpeople_v);
        date.setText("Date: "+date_v);
        time.setText("Time: "+time_v);

        recyclerView_cart = findViewById(R.id.recyclerview_cart_c);
        recyclerView_cart.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView_cart.setLayoutManager(linearLayoutManager);


        //get current user uid

        final FirebaseUser user =  auth.getCurrentUser();
        final String uid = user.getUid();

        //firebase data
        final DatabaseReference cartListRef = MyDatabaseUtil.getDatabase().getReference();

        FirebaseRecyclerOptions<CartModel> options =new FirebaseRecyclerOptions.Builder<CartModel>()
                .setQuery(cartListRef.child("CART")
                        .child(uid), CartModel.class)
                .build();
        FirebaseRecyclerAdapter<CartModel, ConfirmViewHolder> adapter
                =new FirebaseRecyclerAdapter<CartModel, ConfirmViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ConfirmViewHolder holder, int position, @NonNull final CartModel model) {
                holder.textView_name_c.setText(model.getName());
                holder.textView_price_c.setText(String.valueOf(model.getPrice()));
                holder.textView_quantity_c.setText(model.getQuantity());

            }

            @NonNull
            @Override
            public ConfirmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.confirm_item, parent, false);
                ConfirmViewHolder holder = new ConfirmViewHolder(view);
                return holder;
            }
        };
        recyclerView_cart.setAdapter(adapter);
        adapter.startListening();



    }
    @Override
    protected void onStart() {
        super.onStart();



    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}