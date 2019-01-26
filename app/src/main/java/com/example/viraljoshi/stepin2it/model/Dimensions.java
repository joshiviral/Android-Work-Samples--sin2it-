
package com.example.viraljoshi.stepin2it.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Dimensions implements Parcelable {

    @SerializedName("length")
    @Expose
    private Double length;
    @SerializedName("width")
    @Expose
    private Double width;
    @SerializedName("height")
    @Expose
    private Double height;

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.length);
        dest.writeValue(this.width);
        dest.writeValue(this.height);
    }

    public Dimensions() {
    }

    protected Dimensions(Parcel in) {
        this.length = (Double) in.readValue(Double.class.getClassLoader());
        this.width = (Double) in.readValue(Double.class.getClassLoader());
        this.height = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Dimensions> CREATOR = new Parcelable.Creator<Dimensions>() {
        @Override
        public Dimensions createFromParcel(Parcel source) {
            return new Dimensions(source);
        }

        @Override
        public Dimensions[] newArray(int size) {
            return new Dimensions[size];
        }
    };
}
