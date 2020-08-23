package com.example.bookingapp.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingapp.Adapter.ItemsAdapter;
import com.example.bookingapp.Model.Dishes;
import com.example.bookingapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Dishes> dList = new ArrayList<>();
    public static ArrayList<Dishes> selected = new ArrayList<>();
    private RecyclerView mRecyclerView;
    public  static TextView cartitems;
    public static int sum = 0;
    public LinearLayout cart;
    public static boolean selectitem=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviewbyid();
        initial();


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Dishes> item1 = (ArrayList<Dishes>) dList;
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                Bundle b = new Bundle();
                b.putParcelableArrayList("maincart",item1);
                intent.putExtras(b);
                startActivityForResult(intent,1);

            }
        });

    }

    public void initial() {

        dList = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        for(int i=0;i<5;i++){

            dList.add(new Dishes("barbecue", "Very professional", 7,0));

        }
        ItemsAdapter adapter = new ItemsAdapter(dList,"Main");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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

                dList = new ArrayList<>();
                mRecyclerView.setHasFixedSize(true);
                dList = data.getExtras().getParcelableArrayList("cart");

                for(Dishes map : dList) {
                    sum +=(map.getCount());
                }
                System.out.println("iteml"+sum);
                cartitems.setText("("+String.valueOf(sum)+" ITEMS)");

              ItemsAdapter adapter = new ItemsAdapter(dList,"Main2");
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
    }

    }

}

