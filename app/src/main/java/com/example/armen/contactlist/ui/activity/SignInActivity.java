package com.example.armen.contactlist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.armen.contactlist.R;
import com.example.armen.contactlist.io.bus.BusProvider;
import com.example.armen.contactlist.pojo.User;
import com.example.armen.contactlist.util.PreferancesHelper;
import com.example.armen.contactlist.util.user.FileUserStorage;
import com.example.armen.contactlist.util.user.UserStorage;

/**
 * Created by Armen on 7/16/2017.
 */

public class SignInActivity extends BaseActivity implements View.OnClickListener {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String LOG_TAG = SignInActivity.class.getSimpleName();

    // ===========================================================
    // Fields
    // ===========================================================

    private Button mBtnSignIn;
    private Button mBtnSignUp;
    private TextInputLayout mTilEmail;
    private TextInputLayout mTilPass;
    private TextInputEditText mTietEmail;
    private TextInputEditText mTietPass;
    private UserStorage userStorage;

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
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.unregister(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.register(this);
        findViews();
        setListeners();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_sign_in;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ===========================================================
    // Other Listeners, methods for/from Interfaces
    // ===========================================================

    // ===========================================================
    // Click Listeners
    // ===========================================================

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in_sign_in:
                String mail = mTietEmail.getText().toString();
                String pass = mTietPass.getText().toString();
                grabDataAndSingIn(mail, pass);

                break;
            case R.id.btn_sign_in_sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private void setListeners() {
        mBtnSignIn.setOnClickListener(this);
        mBtnSignUp.setOnClickListener(this);
    }

    private void findViews() {
        mTilEmail = (TextInputLayout) findViewById(R.id.til_sign_in_email);
        mTietEmail = (TextInputEditText) findViewById(R.id.tiet_sign_in_email);
        mTilPass = (TextInputLayout) findViewById(R.id.til_sign_in_pass);
        mTietPass = (TextInputEditText) findViewById(R.id.tiet_sign_in_pass);
        mBtnSignIn = (Button) findViewById(R.id.btn_sign_in_sign_in);
        mBtnSignUp = (Button) findViewById(R.id.btn_sign_in_sign_up);
        userStorage = new FileUserStorage();
    }

    private void grabDataAndSingIn(String mail, String pass) {

        userStorage.checkAndGetUser(mail, pass, new UserStorage.UserFoundListener() {
            @Override
            public void onUserFound(User user) {
                handelUserFound(user);
            }
        });
    }

    private void handelUserFound(User user) {
        if (user == null) {
            Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferancesHelper preferancesHelper = PreferancesHelper.getInstance(this);
        preferancesHelper.setLoggedIn(true);
        preferancesHelper.setUserId(user.getId());

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

