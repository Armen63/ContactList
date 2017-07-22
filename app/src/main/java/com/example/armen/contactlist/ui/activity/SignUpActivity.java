package com.example.armen.contactlist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.armen.contactlist.R;
import com.example.armen.contactlist.pojo.User;
import com.example.armen.contactlist.util.PreferancesHelper;
import com.example.armen.contactlist.util.user.FileUserStorage;
import com.example.armen.contactlist.util.user.UserStorage;


public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String LOG_TAG = SignUpActivity.class.getSimpleName();

    // ===========================================================
    // Fields
    // ===========================================================

    private EditText mEtUserEmail;
    private EditText mEtUserPassword;
    private TextInputLayout mTilUserEmail;
    private TextInputLayout mTilUserPassword;
    private Button mBtnSignUp;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        init();
        setListeners();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_sign_up;
    }

    // ===========================================================
    // Click Listeners
    // ===========================================================


    private void setListeners() {
        mBtnSignUp.setOnClickListener(this);
        mBtnSignUp.setOnClickListener(this);
    }

    // ===========================================================
    // Other Listeners, methods for/from Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    private void findViews() {
        mTilUserEmail = (TextInputLayout) findViewById(R.id.til_sign_up_email);
        mTilUserPassword = (TextInputLayout) findViewById(R.id.til_sign_up_user_password);
        mTilUserPassword = (TextInputLayout) findViewById(R.id.til_sign_up_user_password_confirm);
        mEtUserEmail = (EditText) findViewById(R.id.tiet_sign_in_email);
        mEtUserPassword = (EditText) findViewById(R.id.tiet_sign_up_password);
        mBtnSignUp = (Button) findViewById(R.id.btn_sign_up_register);
    }

    private void init() {
        userStorage = new FileUserStorage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up_register:
                String mail = mEtUserEmail.getText().toString();
                String pass = mEtUserPassword.getText().toString();
                registerUser(mail, pass);
                break;
        }
    }

    private void registerUser(String mail, String password) {

        if(!checkAllFieldsValue()){
            return;
        }

        mTilUserEmail.setErrorEnabled(false);
        mTilUserPassword.setErrorEnabled(false);

        String emailFromRegistration = mEtUserEmail.getText().toString();
        String passwordRegistration = mEtUserPassword.getText().toString();
        User user = new User(emailFromRegistration,passwordRegistration);
        userStorage.registerUser(user, new UserStorage.UserFoundListener() {
            @Override
            public void onUserFound(User user) {
                handelUserFound(user);
            }
        });

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void handelUserFound(User user) {
        if(user == null){
            Toast.makeText(this, R.string.msg_wrong_data, Toast.LENGTH_SHORT).show();
            return;
        }

        PreferancesHelper preferancesHelper = PreferancesHelper.getInstance(this);
        preferancesHelper.setLoggedIn(true);
        preferancesHelper.setUserId(user.getId());

        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    private boolean checkAllFieldsValue() {
        String email = mEtUserEmail.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {

            mTilUserEmail.setErrorEnabled(true);
            mTilUserEmail.setError(getString(R.string.msg_email_error));
            mEtUserEmail.setError(getString(R.string.msg_required_error));
            requestFocus(mEtUserEmail);
            return false;
        }
        if (mEtUserPassword.getText().toString().trim().isEmpty()) {
            mTilUserPassword.setError(getString(R.string.msg_password_error));
            requestFocus(mEtUserPassword);
            return false;
        }
        mTilUserEmail.setErrorEnabled(false);
        return true;
    }


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}