package com.example.bookingapp.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingapp.Adapter.ItemsAdapter;
import com.example.bookingapp.Model.Item;
import com.example.bookingapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

import static com.example.bookingapp.Screens.CartActivity.mPriceText;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> mList = new ArrayList<>();
    public static ArrayList<Item> selected = new ArrayList<>();
    private RecyclerView mRecyclerView;
    public  static TextView cartitems;
    public static int sum = 0;
    LinearLayout cart;
    public static boolean selectitem=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviewbyid();
        initial();

    }

    public void initial() {

        mList = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        for(int i=0;i<5;i++){

            mList.add(new Item("barbecue", "Very professional", 7,0));

        }
        ItemsAdapter adapter = new ItemsAdapter(mList,"Main");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Item> item1 = (ArrayList<Item>) mList;
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                Bundle b = new Bundle();
                b.putParcelableArrayList("maincart",item1);
                intent.putExtras(b);
                startActivityForResult(intent,1);

            }
        });

    }


    public void findviewbyid()
    {
        cart = (LinearLayout) findViewById(R.id.LL_Cart);
        cartitems = (TextView) findViewById(R.id.TV_CItems);
        mRecyclerView = (RecyclerView) findViewById(R.id.RV_Items);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                mList = new ArrayList<>();
                mRecyclerView.setHasFixedSize(true);
                mList = data.getExtras().getParcelableArrayList("cart");

                for(Item map : mList) {
                    sum +=(map.getCount());
                }
                System.out.println("iteml"+sum);
                cartitems.setText("("+String.valueOf(sum)+" ITEMS)");

              ItemsAdapter adapter = new ItemsAdapter(mList,"Main2");
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
    }

    }

}

