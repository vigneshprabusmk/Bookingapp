package com.example.bookingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable{

    private String name;
    private String variant;
    private String price;
    private String count ;
    String subTotal;

    public Cart(String name, String variant, String price, String count,String subTotal) {
        this.name = name;
        this.variant = variant;
        this.price = price;
        this.count = count;
        this.subTotal = subTotal;
    }

    protected Cart(Parcel in) {
        name = in.readString();
        variant = in.readString();
        price = in.readString();
        count = in.readString();
    }

    public static final Parcelable.Creator<Cart> CREATOR = new Parcelable.Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public static Creator<Cart> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(variant);
        parcel.writeString(price);
        parcel.writeString(count);
    }
}