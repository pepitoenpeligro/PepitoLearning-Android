
package com.dss.pepitolearning.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Course implements Parcelable {

    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("totalCourses")
    @Expose
    private String totalCourses;
    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("image")
    @Expose
    private String price;


    public Course(){
        categoryId      = "";
        categoryName    = "";
        totalCourses    = "";
        image           = "";
        price           = "";
    }

    protected Course(Parcel in) {
        categoryId = in.readString();
        categoryName = in.readString();
        totalCourses = in.readString();
        image = in.readString();
        price = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(String totalCourses) {
        this.totalCourses = totalCourses;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.categoryId);
        parcel.writeString(this.categoryName);
        parcel.writeString(this.totalCourses);
        parcel.writeString(this.image);
        parcel.writeString(this.price);
    }

    @Override
    public String toString() {
        return "Course{" +
                "price='" + price + '\'' +
                '}';
    }
}
