package com.example.armen.contactlist.pojo;

import java.io.Serializable;

/**
 * Created by Armen on 7/21/2017.
 */

public class User implements Serializable{

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private long id;
    private String email;
    private String password;

    // ===========================================================
    // Constructors
    // ===========================================================

    public User(String email, String password) {
        this.id = System.currentTimeMillis();
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
