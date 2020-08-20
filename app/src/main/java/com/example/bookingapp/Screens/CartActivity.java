package com.example.bookingapp.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingapp.Adapter.ItemsAdapter;
import com.example.bookingapp.Model.Item;
import com.example.bookingapp.R;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.bookingapp.Screens.MainActivity.selected;

public class CartActivity extends AppCompatActivity {

    public ArrayList<Item> items;
    public RecyclerView mRecyclerView;
    public static TextView mPriceText, Show;
    ImageView back;
    LinearLayout order;
    int price = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //Objects.requireNonNull(getSupportActionBar()).setTitle("My Cart");
        items = Objects.requireNonNull(this.getIntent().getExtras()).getParcelableArrayList("maincart");

        findviewbyid();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Item> item1 = (ArrayList<Item>) items;
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putParcelableArrayList("cart", item1);
                intent.putExtras(b);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });

        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        if (items.size() == 0) {

            Toast.makeText(CartActivity.this, "No Cart added!", Toast.LENGTH_SHORT).show();
        }


        for (Item i : items) {
            price += i.getPrice() * i.getCount();
        }
        mPriceText.setText("₹" + String.valueOf(price));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemsAdapter adapter = new ItemsAdapter(items, "Cart");
        mRecyclerView.setAdapter(adapter);
       // mRecyclerView.setLayoutMode(2);
        adapter.notifyDataSetChanged();

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(CartActivity.this, "Check out!!!", Toast.LENGTH_LONG).show();
            }
        });


        if (items.size() <= 3) {
            Show.setVisibility(View.VISIBLE);

        } else {
            Show.setVisibility(View.GONE);
        }

    }

    public void findviewbyid() {

        mRecyclerView = findViewById(R.id.cart_list);
        back = findViewById(R.id.IV_back);
        mPriceText = findViewById(R.id.price);
        Show = findViewById(R.id.TV_show);
        order = findViewById(R.id.LL_Cart);

    }


    @Override
    public void onBackPressed() {

        ArrayList<Item> item1 = (ArrayList<Item>) items;
        Intent intent = new Intent();
        Bundle b = new Bundle();
        b.putParcelableArrayList("cart", item1);
        intent.putExtras(b);
        setResult(Activity.RESULT_OK, intent);

        finish();
    }

}