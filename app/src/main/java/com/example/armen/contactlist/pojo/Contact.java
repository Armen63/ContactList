package com.example.armen.contactlist.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Armen on 7/22/2017.
 */


public class Contact implements Parcelable {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @SerializedName("contact_id")
    private long id;

    @SerializedName("first_name")
    private String fName;

    @SerializedName("last_name")
    private String lName;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("image")
    private String image;

    @SerializedName("note")
    private String notes;

    // ===========================================================
    // Constructors
    // ===========================================================

    protected Contact(Parcel in) {
        id = in.readLong();
        fName = in.readString();
        lName = in.readString();
        email = in.readString();
        phone = in.readString();
        image = in.readString();
        notes = in.readString();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // ===========================================================
    // Methods for/from SuperClass
    // ===========================================================

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(fName);
        parcel.writeString(lName);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(image);
        parcel.writeString(notes);
    }

    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
