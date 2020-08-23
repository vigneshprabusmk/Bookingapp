package com.example.bookingapp.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingapp.Adapter.ItemsAdapter;
import com.example.bookingapp.Model.Item;
import com.example.bookingapp.R;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.bookingapp.Screens.MainActivity.cartitems;
import static com.example.bookingapp.Screens.MainActivity.selected;

public class CartActivity extends AppCompatActivity {

    public ArrayList<Item> items;
    public RecyclerView mRecyclerView;
    public static TextView mPriceText, Show;
    ImageView back;
    LinearLayout order;
    public static int price = 0;
    int count = 0;
    public static boolean clicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        items = Objects.requireNonNull(this.getIntent().getExtras()).getParcelableArrayList("maincart");

        findviewbyid();

        initial();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(clicked =true ) {

                    ArrayList<Item> item1 = (ArrayList<Item>) items;
                    Intent intent = new Intent();
                    Bundle b = new Bundle();
                    b.putParcelableArrayList("cart", item1);
                    intent.putExtras(b);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {

                    onBackPressed();

                }
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

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(CartActivity.this, "Check out!!!", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void findviewbyid() {

        mRecyclerView = findViewById(R.id.RV_Itemslist);
        back = findViewById(R.id.IV_back);
        mPriceText = findViewById(R.id.TV_Price);
        Show = findViewById(R.id.TV_show);
        order = findViewById(R.id.LL_Cart);

    }

    public void initial() {

        if(clicked =true ) {
            for (Item i : items) {
                price += i.getPrice() * i.getCount();
            }
            mPriceText.setText("₹" + String.valueOf(price));
        }else {

          /*  for (Item i : items) {
                price = i.getPrice() * i.getCount();
            }
            mPriceText.setText("₹" + String.valueOf(price));*/

        }


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemsAdapter adapter = new ItemsAdapter(items, "Cart");
        mRecyclerView.setAdapter(adapter);
        // mRecyclerView.setLayoutMode(2);
        adapter.notifyDataSetChanged();

    }


        @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}