package com.example.bookingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{

    private String name;
    private String variant;
    private int price;
    private int count = 0;

    public Item(String name, String variant, int price, int count) {
        this.name = name;
        this.variant = variant;
        this.price = price;
        this.count = count;
    }

    protected Item(Parcel in) {
        name = in.readString();
        variant = in.readString();
        price = in.readInt();
        count = in.readInt();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(variant);
        parcel.writeInt(price);
        parcel.writeInt(count);
    }
}