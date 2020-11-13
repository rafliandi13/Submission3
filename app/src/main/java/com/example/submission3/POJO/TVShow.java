package com.example.submission3.POJO;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShow implements Parcelable {
    private String Image;
    private String Name;
    private String Description;
    private String Gendre;
    private String Duration;
    private String Revenue;
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getGendre() {
        return Gendre;
    }

    public void setGendre(String gendre) {
        Gendre = gendre;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getRevenue() {
        return Revenue;
    }

    public void setRevenue(String revenue) {
        Revenue = revenue;
    }

    public TVShow(){

    }
    protected TVShow(Parcel in) {
        this.Image = in.readString();
        this.Name = in.readString();
        this.Description = in.readString();
        this.Gendre = in.readString();
        this.Duration = in.readString();
        this.Revenue = in.readString();
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.Image);
        dest.writeString(this.Name);
        dest.writeString(this.Description);
        dest.writeString(this.Gendre);
        dest.writeString(this.Duration);
        dest.writeString(this.Revenue);
    }
}
