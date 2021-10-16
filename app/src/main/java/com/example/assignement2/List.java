package com.example.assignement2;

import android.os.Parcel;
import android.os.Parcelable;


public class List implements Parcelable {
    public String name;
    public String price;
    public String q;

    public List(String name, String price, String q) {
        this.name = name;
        this.price = price;
        this.q = q;
    }

    protected List(Parcel in) {
        name = in.readString();
        price = in.readString();
        q = in.readString();
    }

    public static final Creator<List> CREATOR = new Creator<List>() {
        @Override
        public List createFromParcel(Parcel in) {
            return new List(in);
        }

        @Override
        public List[] newArray(int size) {
            return new List[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(q);
    }
}
