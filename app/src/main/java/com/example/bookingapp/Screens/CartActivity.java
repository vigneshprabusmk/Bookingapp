package com.example.bookingapp.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    public static TextView mPriceText,Show;
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
                b.putParcelableArrayList("cart",item1);
                intent.putExtras(b);
                setResult(RESULT_OK, intent);
               // startActivity(intent);
                finish();
            }
        });

        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



        if(items.size()==0){

            Toast.makeText(CartActivity.this,"No Cart added!",Toast.LENGTH_SHORT).show();
        }


        for (Item i : items) {
            price += i.getPrice() * i.getCount();
        }
        mPriceText.setText("₹" + String.valueOf(price));

                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                ItemsAdapter adapter = new ItemsAdapter(items,"Cart");
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutMode(2);
                adapter.notifyDataSetChanged();

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(CartActivity.this,"Check out!!!",Toast.LENGTH_LONG).show();
            }
        });



        if(items.size()<=3){
Show.setVisibility(View.VISIBLE);

        }else {
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

        ArrayList<Item> item1 = (ArrayList<Item>) selected;
        Intent intent = new Intent();
        Bundle b = new Bundle();
        b.putParcelableArrayList("cart",item1);
        //intent.putExtra("keyName", item1);
        intent.putExtras(b);
        setResult(RESULT_OK, intent);
        //startActivity(intent);
        finish();
    }

/*
    public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

        public ArrayList<Item> item;

        ItemsAdapter(ArrayList<Item> itemList) {
            item = new ArrayList<>();
            selected = new ArrayList<>();
            this.item = itemList;
        }

        @NonNull
        @Override
        public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final ItemsAdapter.ViewHolder holder, int position) {
            final Item itm = item.get(position);
            holder.name.setText(itm.getName());
            holder.price.setText("₹ " + String.valueOf(itm.getPrice()));
            holder.quantity.setText(itm.getVariant());
            holder.count.setText(String.valueOf(itm.getCount()));

        if(itm.getCount()==0){
            holder.content.setVisibility(View.GONE);
        }else {
            holder.content.setVisibility(View.VISIBLE);
        }

            holder.tadd.setVisibility(View.GONE);
            holder.LLplusminus.setVisibility(View.VISIBLE);

            holder.add.setOnClickListener((v) -> {

                if(itm.getCount()!=20){

                    itm.setCount(itm.getCount() + 1);
                    holder.count.setText(String.valueOf(itm.getCount()));
                    if (!selected.contains(itm)){
                        selected.add(itm);
                    }
                    // cartitems.setText("("+String.valueOf(itm.getCount())+" ITEMS)");
                   // totalcart();

                }

                count++;
               // cartitems.setText("("+String.valueOf(count)+" ITEMS)");
                for (Item i : items) {
                    price = i.getPrice()*count;
                }
                mPriceText.setText("₹ " + String.valueOf(price));

            });

            holder.remove.setOnClickListener(v -> {


                for (Item i : items) {
                    price = i.getPrice() * i.getCount()-1;
                }
                mPriceText.setText("₹ " + String.valueOf(price));

                if (!(itm.getCount() <= 0))

                    itm.setCount(itm.getCount() - 1);
                holder.count.setText(String.valueOf(itm.getCount()));
                if(itm.getCount()==0){

                    if (selected.contains(itm)){
                        selected.remove(itm);
                        holder.count.setText(String.valueOf(itm.getCount()));
                    }
                    holder.content.setVisibility(View.GONE);

                  //  holder.tadd.setVisibility(View.VISIBLE);
                  //  holder.LLplusminus.setVisibility(View.GONE);
                }
                //   cartitems.setText("("+String.valueOf(itm.getCount())+" ITEMS)");
               // totalcart();
                */
/*for (Item i : items) {
                    price = i.getPrice() / i.getCount();
                }
                mPriceText.setText("₹ " + String.valueOf(price));*//*

            });

       */
/* List<Item> items = new ArrayList<>();
        int total = 0;
        for(int i = 0; i < items.size(); i++){
            total = total + items.get(i).getCount();
            cartitems.setText("("+String.valueOf(total)+" ITEMS)");
        }*//*




        }

        private void totalcart() {

            cartitems.setText("("+String.valueOf(selected.size())+" ITEMS)");

        }

        @Override
        public int getItemCount() {
            return item.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView name, price, quantity, count,tadd;
            private ImageView add, remove;
            LinearLayout LLplusminus;
            RelativeLayout content;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.from_name);
                price = itemView.findViewById(R.id.price_text);
                quantity = itemView.findViewById(R.id.weight_text);
                tadd = itemView.findViewById(R.id.TV_Add);

                content = itemView.findViewById(R.id.RL_content);
                LLplusminus = itemView.findViewById(R.id.cart_plus_minus_layout);
                count = itemView.findViewById(R.id.cart_product_quantity_tv);
                add = itemView.findViewById(R.id.cart_plus_img);
                remove = itemView.findViewById(R.id.cart_minus_img);
            }
        }
    }
*/

/*
    private class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
        private List<Item> item;

        ItemsAdapter(List<Item> itemList) {
            item = new ArrayList<>();
            this.item = itemList;
        }

        @NonNull
        @Override
        public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);
            return new ItemsAdapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final ItemsAdapter.ViewHolder holder, final int position) {
            final Item itm = item.get(position);
            holder.name.setText(itm.getName());
            holder.price.setText(String.valueOf(itm.getPrice()));
            holder.quantity.setText(itm.getVariant());
            holder.count.setText(String.valueOf(itm.getCount()));

            holder.add.setOnClickListener(v -> {
                itm.setCount(itm.getCount() + 1);
                holder.count.setText(String.valueOf(itm.getCount()));
            });

            holder.remove.setOnClickListener(v -> {
                if (!(itm.getCount() <= 0))
                    itm.setCount(itm.getCount() - 1);
                if (itm.getCount() == 0) {
                    item.remove(holder.getAdapterPosition());
                    notifyDataSetChanged();
                } else holder.count.setText(String.valueOf(itm.getCount()));
            });

        }

        @Override
        public int getItemCount() {
            return item.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView name, price, quantity, count;
            private ImageView add, remove;


            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.from_name);
                price = itemView.findViewById(R.id.price_text);
                quantity = itemView.findViewById(R.id.weight_text);
                count = itemView.findViewById(R.id.cart_product_quantity_tv);
                add = itemView.findViewById(R.id.cart_plus_img);
                remove = itemView.findViewById(R.id.cart_minus_img);
            }
        }
    }
*/

}

