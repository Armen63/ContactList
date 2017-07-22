package com.example.armen.contactlist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.armen.contactlist.R;


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
    private TextInputLayout mTilUserPasswordConfirm;
    private Button mBtnSignUp;

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_in_sign_in:
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                finish();
                break;
            case R.id.btn_sign_in_sign_up:
                registerUser();
                break;
        }
    }

    private void findViews() {
        mTilUserEmail = (TextInputLayout) findViewById(R.id.til_sign_up_email);
        mTilUserPassword = (TextInputLayout) findViewById(R.id.til_sign_up_user_password);
        mTilUserPassword = (TextInputLayout) findViewById(R.id.til_sign_up_user_password_confirm);

        mEtUserEmail = (EditText) findViewById(R.id.et_sign_in_email);
        mEtUserPassword = (EditText) findViewById(R.id.et_sign_up_password);

        mBtnSignUp = (Button) findViewById(R.id.btn_sign_up_register);
    }

    private void init(){
    }

    /**
     * Validating form
     */
    private void registerUser() {

        if (!checkEmail()) {
            return;
        }

        if (!checkPassword()) {
            return;
        }

        mTilUserEmail.setErrorEnabled(false);
        mTilUserPassword.setErrorEnabled(false);


    }

    private boolean checkEmail() {
        String email = mEtUserEmail.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {

            mTilUserEmail.setErrorEnabled(true);
            mTilUserEmail.setError(getString(R.string.msg_email_error));
            mEtUserEmail.setError(getString(R.string.msg_required_error));
            requestFocus(mEtUserEmail);
            return false;
        }
        mTilUserEmail.setErrorEnabled(false);
        return true;
    }


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    private boolean checkPassword() {
        if (mEtUserPassword.getText().toString().trim().isEmpty()) {

            mTilUserPassword.setError(getString(R.string.msg_password_error));
            requestFocus(mEtUserPassword);
            return false;
        }
        mTilUserPassword.setErrorEnabled(false);
        return true;
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