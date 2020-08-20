package com.example.bookingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingapp.Model.Item;
import com.example.bookingapp.R;

import java.util.ArrayList;

import static com.example.bookingapp.Screens.CartActivity.mPriceText;
import static com.example.bookingapp.Screens.MainActivity.cartitems;
import static com.example.bookingapp.Screens.MainActivity.selected;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public ArrayList<Item> item;
    int price = 0;
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
        if (Tag.equalsIgnoreCase("Main")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);
        } else {
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


        if (Tag.equalsIgnoreCase("Main")) {

        if(item.size()!=0) {
        if(itm.getCount()!=0){

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
else {

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
        }
        }
    }

        else {

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
                }
                count++;
                for (Item i : item) {
                    price += i.getPrice()*count;
                }
                mPriceText.setText("₹ " + String.valueOf(price));

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
                    holder.content.setVisibility(View.GONE);
                }
                count--;
                for (Item i : item) {
                    price += -i.getPrice()*count;
                }
                mPriceText.setText("₹ " + String.valueOf(price));

            });

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
