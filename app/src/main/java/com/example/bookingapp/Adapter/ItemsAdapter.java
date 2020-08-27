package com.example.bookingapp.Adapter;

import android.content.Context;
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

import com.example.bookingapp.Model.Dishes;
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

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public ArrayList<Dishes> dList;
    public int pricelist = 0;
    public int count = 0;
    public String Tag;
    public static int num = 1;
    Context context;

   public ItemsAdapter(ArrayList<Dishes> itemList,Context context,String tag) {
        dList = new ArrayList<>();
        selected = new ArrayList<>();
        this.dList = itemList;
       this.context = context;
        this.Tag =tag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;
        if (Tag.equalsIgnoreCase("Main")|| Tag.equalsIgnoreCase("Main2")){
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_list, parent, false);
        }
         else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cart_list, parent, false);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemsAdapter.ViewHolder holder, int position) {

        final Dishes itm = dList.get(position);
        holder.name.setText(itm.getName());
        holder.price.setText("₹ " + String.valueOf(itm.getPrice()));
        holder.quantity.setText(itm.getVariant());
        holder.count.setText(String.valueOf(itm.getCount()));


      //  if (Tag.equalsIgnoreCase("Main")) {

        if(dList.size()!=0) {

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

        }

        else {

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
                       // cartitems.setText("("+String.valueOf(count)+" ITEMS)");

                        int addcartcount=0;
                        for (Dishes i : dList) {
                            addcartcount += i.getCount();
                        }
                        cartitems.setText("("+String.valueOf(addcartcount)+" ITEMS)");
                       // Toast.makeText(context,(String.valueOf(addcartcount)),Toast.LENGTH_SHORT).show();

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
                            //cartitems.setText("("+String.valueOf(count)+" ITEMS)");

                            int cartcountadd=0;
                            for (Dishes i : dList) {
                                cartcountadd += i.getCount();
                            }
                            cartitems.setText("("+String.valueOf(cartcountadd)+" ITEMS)");
                           // Toast.makeText(context,(String.valueOf(cartcountadd)),Toast.LENGTH_SHORT).show();
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
                            selectitem=false;
                            holder.tadd.setVisibility(View.VISIBLE);
                            holder.LLplusminus.setVisibility(View.GONE);

                        }
                        count--;
                       // cartitems.setText("("+String.valueOf(count)+" ITEMS)");

                        int cartcountremove=0;
                        for (Dishes i : dList) {
                            cartcountremove += i.getCount();
                        }
                        cartitems.setText("("+String.valueOf(cartcountremove)+" ITEMS)");
                       // Toast.makeText(context,(String.valueOf(cartcountremove)),Toast.LENGTH_SHORT).show();

                    });

                }
            }
        }

        else if (Tag.equalsIgnoreCase("Cart")) {
            {

                if(itm.getCount()==0){
                    holder.tadd.setVisibility(View.VISIBLE);
                    holder.content.setVisibility(View.GONE);
                }else {

                    holder.tadd.setVisibility(View.GONE);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.LLplusminus.setVisibility(View.VISIBLE);

                   /* for (int i = 0; i <selected.size() ; i++) {
                        if(selected.size()>2) {
                            Show.setVisibility(View.VISIBLE);
                        }else {
                            Show.setVisibility(View.GONE);
                        }
                    }*/

                    holder.add.setOnClickListener((v) -> {

                        clicked=true;
                        if (itm.getCount() != 20) {

                            itm.setCount(itm.getCount() + 1);
                            holder.count.setText(String.valueOf(itm.getCount()));
                            if (!selected.contains(itm)) {
                                selected.add(itm);
                            }
                            count++;
                            int cartcount = 0;
                            for (Dishes i : dList) {
                                pricelist = i.getPrice() * count;
                            }
                            mPriceText.setText("₹" + String.valueOf(price + pricelist));
                        }


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
                            if(itm.getCount()>2) {
                                Show.setVisibility(View.VISIBLE);
                            }else {
                                Show.setVisibility(View.GONE);
                            }
                           // Show.setVisibility(View.GONE);
                        }

                        count--;
                        int cartcount = 0;
                        for (Dishes i : dList) {
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

        if (Tag.equalsIgnoreCase("Cart")){
            if(num*2 > dList.size()){
                return dList.size();
            }else{
                return num*2;
            }

        } else {
           // (Tag.equalsIgnoreCase("Main")|| Tag.equalsIgnoreCase("Main2")){
            return dList.size();
        }

    }

   public class ViewHolder extends RecyclerView.ViewHolder {
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
