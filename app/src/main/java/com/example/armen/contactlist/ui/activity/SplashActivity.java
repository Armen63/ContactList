package com.example.armen.contactlist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.armen.contactlist.util.helper.PreferancesHelper;

public class SplashActivity extends AppCompatActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String LOG_TAG = SplashActivity.class.getSimpleName();

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass
    // ===========================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // if user isn't authorized then suggest him to authorize
        if (!PreferancesHelper.getInstance(this).isLoggedIn()) {
            startActivity(new  Intent(this, SignInActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

    // ===========================================================
    // Other Listeners, methods for/from Interfaces
    // ===========================================================

    // ===========================================================
    // Click Listeners
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}