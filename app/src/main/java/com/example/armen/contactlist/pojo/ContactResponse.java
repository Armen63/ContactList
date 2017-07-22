package com.example.armen.contactlist.pojo;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactResponse {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @SerializedName("contacts")
    private ArrayList<Contact> contacts;

    // ===========================================================
    // Constructors
    // ===========================================================

    public ContactResponse() {
    }

    public ContactResponse(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    // ===========================================================
    // Methods for/from SuperClass
    // ===========================================================

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
