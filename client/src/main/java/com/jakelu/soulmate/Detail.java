package com.jakelu.soulmate;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 勸君惜取少年時&莫待無花空折枝
 * ---------------------------------
 * Created by Jake on 2018/5/3.
 */

public class Detail implements Parcelable {

    public String name;
    public String type;
    public String contentid;

    public Detail() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(contentid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Detail> CREATOR = new Creator<Detail>() {
        @Override
        public Detail createFromParcel(Parcel in) {
            String name = in.readString();
            String type = in.readString();
            String contentid = in.readString();
            Detail detail = new Detail();
            detail.name = name;
            detail.type = type;
            detail.contentid = contentid;

            return detail;
        }

        @Override
        public Detail[] newArray(int size) {
            return new Detail[size];
        }
    };
}
