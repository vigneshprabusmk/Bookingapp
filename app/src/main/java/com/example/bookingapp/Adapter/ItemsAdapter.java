package com.example.bookingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingapp.Model.Item;
import com.example.bookingapp.R;
import com.example.bookingapp.Screens.CartActivity;

import java.util.ArrayList;

import static com.example.bookingapp.Screens.CartActivity.Show;
import static com.example.bookingapp.Screens.CartActivity.clicked;
import static com.example.bookingapp.Screens.CartActivity.mPriceText;
import static com.example.bookingapp.Screens.CartActivity.price;
import static com.example.bookingapp.Screens.MainActivity.cartitems;
import static com.example.bookingapp.Screens.MainActivity.selected;
import static com.example.bookingapp.Screens.MainActivity.selectitem;
import static com.example.bookingapp.Screens.MainActivity.sum;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public ArrayList<Item> item;
    int pricelist = 0;
    int count = 0;
    String Tag;

   public ItemsAdapter(ArrayList<Item> itemList,String tag) {
        item = new ArrayList<>();
        selected = new ArrayList<>();
        this.item = itemList;
        this.Tag =tag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;
        if (Tag.equalsIgnoreCase("Main")|| Tag.equalsIgnoreCase("Main2")){
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);
        }
         else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemsAdapter.ViewHolder holder, int position) {

        final Item itm = item.get(position);
        holder.name.setText(itm.getName());
        holder.price.setText("₹ " + String.valueOf(itm.getPrice()));
        holder.quantity.setText(itm.getVariant());
        holder.count.setText(String.valueOf(itm.getCount()));


      //  if (Tag.equalsIgnoreCase("Main")) {

        if(item.size()!=0) {

        if(itm.getCount()==0){

            holder.tadd.setOnClickListener((v) -> {
                holder.tadd.setVisibility(View.GONE);
                holder.LLplusminus.setVisibility(View.VISIBLE);

                itm.setCount(itm.getCount() + 1);
                holder.count.setText(String.valueOf(itm.getCount()));
                if (!selected.contains(itm)) {
                    selected.add(itm);
                }
                count++;
                cartitems.setText("("+String.valueOf(count)+" ITEMS)");

            });

            holder.add.setOnClickListener((v) -> {

                if (itm.getCount() != 20) {

                    itm.setCount(itm.getCount() + 1);
                    holder.count.setText(String.valueOf(itm.getCount()));
                    if (!selected.contains(itm)) {
                        selected.add(itm);
                    }
                    count++;
                    cartitems.setText("("+String.valueOf(count)+" ITEMS)");
                }

            });

            holder.remove.setOnClickListener(v -> {

                if (!(itm.getCount() <= 0))

                    itm.setCount(itm.getCount() - 1);
                holder.count.setText(String.valueOf(itm.getCount()));
                if (itm.getCount() == 0) {

                    if (selected.contains(itm)) {
                        selected.remove(itm);
                        holder.count.setText(String.valueOf(itm.getCount()));
                    }
                    holder.tadd.setVisibility(View.VISIBLE);
                    holder.LLplusminus.setVisibility(View.GONE);
                }
                count--;
                cartitems.setText("("+String.valueOf(count)+" ITEMS)");
            });

        } else {

            holder.tadd.setVisibility(View.GONE);
            holder.LLplusminus.setVisibility(View.VISIBLE);

            holder.add.setOnClickListener((v) -> {
                if(itm.getCount()!=20){

                    itm.setCount(itm.getCount() + 1);
                    holder.count.setText(String.valueOf(itm.getCount()));
                    if (!selected.contains(itm)){
                        selected.add(itm);
                    }
                }
            });

            holder.remove.setOnClickListener(v -> {

                if (!(itm.getCount() <= 0))

                    itm.setCount(itm.getCount() - 1);
                holder.count.setText(String.valueOf(itm.getCount()));

                if(itm.getCount()==0){

                    if (selected.contains(itm)){
                        selected.remove(itm);
                        holder.count.setText(String.valueOf(itm.getCount()));

                    }
                }
            });


        }
        }
        else {

            holder.tadd.setVisibility(View.VISIBLE);
            holder.LLplusminus.setVisibility(View.GONE);

        }


        if (Tag.equalsIgnoreCase("Main2")) {

            {
                if(itm.getCount()==0){

                    holder.tadd.setVisibility(View.VISIBLE);
                    holder.LLplusminus.setVisibility(View.GONE);

                }else {

                    holder.tadd.setOnClickListener((v) -> {
                        selectitem=true;
                        holder.tadd.setVisibility(View.GONE);
                        holder.LLplusminus.setVisibility(View.VISIBLE);

                        itm.setCount(itm.getCount() + 1);
                        holder.count.setText(String.valueOf(itm.getCount()));
                        if (!selected.contains(itm)) {
                            selected.add(itm);
                        }
                        count++;
                        cartitems.setText("("+String.valueOf(itm.getCount()+count)+" ITEMS)");

                    });

                    holder.add.setOnClickListener((v) -> {
                        selectitem=true;
                        if (itm.getCount() != 20) {

                            itm.setCount(itm.getCount() + 1);
                            holder.count.setText(String.valueOf(itm.getCount()));
                            if (!selected.contains(itm)) {
                                selected.add(itm);
                            }
                            count++;
                            cartitems.setText("("+String.valueOf(count)+" ITEMS)");
                        }

                    });

                    holder.remove.setOnClickListener(v -> {
                        selectitem=true;
                        if (!(itm.getCount() <= 0))

                            itm.setCount(itm.getCount() - 1);
                        holder.count.setText(String.valueOf(itm.getCount()));
                        if (itm.getCount() == 0) {

                            if (selected.contains(itm)) {
                                selected.remove(itm);
                                holder.count.setText(String.valueOf(itm.getCount()));
                            }
                            holder.tadd.setVisibility(View.VISIBLE);
                            holder.LLplusminus.setVisibility(View.GONE);
                            selectitem=false;
                        }
                        count--;
                        cartitems.setText("("+String.valueOf(count)+" ITEMS)");
                    });

                }
            }
        }

        else if (Tag.equalsIgnoreCase("Cart")) {
            {
                //  mPriceText.setText("₹ " + String.valueOf(price));

                if(itm.getCount()==0){
                    holder.tadd.setVisibility(View.VISIBLE);
                    holder.content.setVisibility(View.GONE);
                    // Toast.makeText(CartActivity.this, "No Cart added!", Toast.LENGTH_SHORT).show();
                }else {

                    for (Item i : item) {


                        if (i.getCount() == 1) {

                            Show.setVisibility(View.VISIBLE);
                           //x Toast.makeText(CartActivity.this, "Show out!!!", Toast.LENGTH_LONG).show();


                        } else {
                            Show.setVisibility(View.GONE);
                        }}


                    mPriceText.setText("₹" + String.valueOf(price + pricelist));


                    holder.tadd.setVisibility(View.GONE);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.LLplusminus.setVisibility(View.VISIBLE);

                    holder.add.setOnClickListener((v) -> {

                        clicked=true;
                        if (itm.getCount() != 20) {

                            itm.setCount(itm.getCount() + 1);
                            holder.count.setText(String.valueOf(itm.getCount()));
                            if (!selected.contains(itm)) {
                                selected.add(itm);
                            }
                        }
                        count++;
                        for (Item i : item) {
                            pricelist = i.getPrice() * count;
                        }
                        mPriceText.setText("₹" + String.valueOf(price + pricelist));

                    });

                    holder.remove.setOnClickListener(v -> {
                        clicked=true;
                        if (!(itm.getCount() <= 0))

                            itm.setCount(itm.getCount() - 1);
                        holder.count.setText(String.valueOf(itm.getCount()));
                        if (itm.getCount() == 0) {

                            if (selected.contains(itm)) {
                                selected.remove(itm);
                                holder.count.setText(String.valueOf(itm.getCount()));
                            }
                            holder.content.setVisibility(View.GONE);
                            clicked=false;

                        }
                        count--;
                        for (Item i : item) {
                            pricelist = -i.getPrice() * count;
                        }
                        mPriceText.setText("₹" + String.valueOf(price - pricelist));

                    });
                }
            }

        }

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
            name = itemView.findViewById(R.id.TV_Hname);
            price = itemView.findViewById(R.id.TV_Amount);
            quantity = itemView.findViewById(R.id.TV_Hcontent);
            tadd = itemView.findViewById(R.id.BTN_Add);
            content = itemView.findViewById(R.id.RL_content);
            LLplusminus = itemView.findViewById(R.id.LL_cart_plus_minus_layout);
            count = itemView.findViewById(R.id.TV_cart_product_quantity);
            add = itemView.findViewById(R.id.IV_cart_plus_img);
            remove = itemView.findViewById(R.id.IV_cart_minus_img);
        }
    }
}
