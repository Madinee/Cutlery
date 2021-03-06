package com.example.cutlery.Controller.Fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cutlery.Controller.Adapter.CartViewHolder;
import com.example.cutlery.Controller.BreakfastActivity;
import com.example.cutlery.Controller.CartActivity;
import com.example.cutlery.Controller.LoginActivity;
import com.example.cutlery.Controller.MainActivity;
import com.example.cutlery.Controller.MenuDetailActivity;
import com.example.cutlery.Controller.MyDatabaseUtil;
import com.example.cutlery.Controller.ReservationActivity;
import com.example.cutlery.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Model.CartModel;

import static android.content.ContentValues.TAG;


public class CartFragment extends Fragment {
    private RecyclerView recyclerView_cart;
    private List<CartModel> cartList = new ArrayList<>();
    private TextView totalAmount;
    private Button reservation;
    final FirebaseAuth auth = FirebaseAuth.getInstance();
    Intent intent;
    private int overTotalPrice = 0;


    public CartFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_cart, container, false);


        recyclerView_cart = root.findViewById(R.id.recyclerview_cart);
        recyclerView_cart.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView_cart.setLayoutManager(linearLayoutManager);

      //find reservation view
        totalAmount=root.findViewById(R.id.menu_total);


        //get current user uid

        final FirebaseUser user =  auth.getCurrentUser();
        final String uid = user.getUid();

        //firebase data
        final DatabaseReference cartListRef = MyDatabaseUtil.getDatabase().getReference();

        FirebaseRecyclerOptions<CartModel> options =new FirebaseRecyclerOptions.Builder<CartModel>()
                .setQuery(cartListRef.child("CART")
                        .child(uid), CartModel.class)
                        .build();
        FirebaseRecyclerAdapter<CartModel, CartViewHolder>adapter
                =new FirebaseRecyclerAdapter<CartModel, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final CartModel model) {
                holder.textView_name.setText(model.getName());
                holder.textView_price.setText(String.valueOf(model.getPrice()));
                holder.textView_quantity.setText(model.getQuantity());

                //calculate price
                int oneTyprProductTPrice = ((model.getPrice())) * Integer.parseInt(model.getQuantity());
                overTotalPrice = overTotalPrice + oneTyprProductTPrice;

                //set totalamount
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        CharSequence[] options = new CharSequence[]
                                {
                                        "Remove"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                                    cartListRef.child("CART")
                                            .child(uid)
                                            .child(model.getName())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful())
                                                    {
                                                        Toast.makeText(getActivity(), "Item removed successfully.", Toast.LENGTH_SHORT).show();
                                                        intent = new Intent(getActivity(), CartActivity.class);
                                                        getActivity().startActivity(intent);
                                                        ((Activity) getActivity()).overridePendingTransition(0, 0);
                                                        getActivity().finish();
                                                    }
                                                }


                                            });

                            }
                        });
                        builder.show();
                    }
                });
                totalAmount.setText("Total = "+ overTotalPrice+ "€");

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };
        recyclerView_cart.setAdapter(adapter);
        adapter.startListening();



        reservation=root.findViewById(R.id.btn_reservation);
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(),ReservationActivity.class);
                //intent.putExtra("Hello","Nisar");
                getActivity().startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        return root;
    }


}

