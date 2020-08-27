package com.example.bookingapp.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingapp.Adapter.ItemsAdapter;
import com.example.bookingapp.Model.Dishes;
import com.example.bookingapp.R;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.bookingapp.Screens.MainActivity.selected;

public class CartActivity extends AppCompatActivity {

    public ArrayList<Dishes> dList;
    public RecyclerView mRecyclerView;
    public static TextView mPriceText, Show;
    public ImageView back;
    public LinearLayout order;
    public ItemsAdapter adapter;
    public static int price = 0;
    public static boolean clicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dList = Objects.requireNonNull(this.getIntent().getExtras()).getParcelableArrayList("maincart");

        findviewbyid();

      //  Show.setVisibility(View.GONE);

        initial();

    }


    public void findviewbyid() {

        mRecyclerView = findViewById(R.id.RV_Itemslist);
        back = findViewById(R.id.IV_back);
        mPriceText = findViewById(R.id.TV_Price);
        Show = findViewById(R.id.TV_show);
        order = findViewById(R.id.LL_Cart);

    }

    public void initial() {

        for (int i = 0; i <selected.size() ; i++) {
            if(selected.size()>2) {
               // Toast.makeText(this,String.valueOf(selected.size()), Toast.LENGTH_SHORT).show();
                Show.setVisibility(View.VISIBLE);
            }else {
                Show.setVisibility(View.GONE);
            }
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemsAdapter(dList, this,"Cart");
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        int cartcount = 0;
        for (Dishes i : dList) {
            cartcount += i.getCount();
            price += i.getPrice() * i.getCount();
        }
        mPriceText.setText("₹" + String.valueOf(price));

        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((adapter.num)*2 < dList.size()){
                    adapter.num = adapter.num + dList.size();

                }
                adapter.notifyDataSetChanged();
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(CartActivity.this, "Check out!!!", Toast.LENGTH_LONG).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(clicked =true ) {
                    price=0;
                    //cartcount=0;
                    ArrayList<Dishes> item1 = (ArrayList<Dishes>) dList;
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

    }


        @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}