package com.example.assignement2;

import android.os.Parcel;
import android.os.Parcelable;


public class History implements Parcelable {
    public String nam;
    public String pric;
    public String q;
    public String date;

    public History(String nam, String pric, String q, String date) {
        this.nam = nam;
        this.pric = pric;
        this.q = q;
        this.date = date;
    }

    protected History(Parcel in) {
        nam = in.readString();
        pric = in.readString();
        q = in.readString();
        date = in.readString();
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nam);
        parcel.writeString(pric);
        parcel.writeString(q);
        parcel.writeString(date);
    }
}
