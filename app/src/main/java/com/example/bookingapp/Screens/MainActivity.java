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

import com.example.bookingapp.Adapter.ItemsAdapter;
import com.example.bookingapp.Model.Item;
import com.example.bookingapp.R;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> mList = new ArrayList<>();
    public static ArrayList<Item> selected = new ArrayList<>();
    private RecyclerView mRecyclerView;
    public  static TextView cartitems;
    LinearLayout cart;
    ImageButton back;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int GET_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mList = Objects.requireNonNull(this.getIntent().getExtras()).getParcelableArrayList("Cart");
        /*  Intent intent = new Intent(this, CartActivity.class);
        startActivityForResult(intent, GET_REQUEST_CODE);*/

        findviewbyid();
        initial();

    }

    @Override
    protected void onResume() {
        try {
            super.onResume();

           /* Intent intent = new Intent(this, CartActivity.class);
            startActivityForResult(intent, GET_REQUEST_CODE);*/

            mList = Objects.requireNonNull(this.getIntent().getExtras()).getParcelableArrayList("Cart");
            ItemsAdapter adapter = new ItemsAdapter(mList,"Main");;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            mRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void initial() {

        mList = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        for(int i=0;i<4;i++){

            mList.add(new Item("barbecue", "Very professional", 7,0));

        }

        ItemsAdapter adapter = new ItemsAdapter(mList,"Main");;
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
                startActivity(intent);

            }
        });

    }


    public void findviewbyid()
    {
        cart = (LinearLayout) findViewById(R.id.LL_Cart);
        cartitems = (TextView) findViewById(R.id.TV_CItems);
        mRecyclerView = (RecyclerView) findViewById(R.id.PV_recent);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GET_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                mList = Objects.requireNonNull(this.getIntent().getExtras()).getParcelableArrayList("Cart");
                ItemsAdapter adapter = new ItemsAdapter(mList,"Main");;
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
    }

    }


}

