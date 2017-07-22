package com.example.armen.contactlist.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.armen.contactlist.R;
import com.example.armen.contactlist.io.bus.BusProvider;
import com.example.armen.contactlist.io.rest.HttpRequestManager;
import com.example.armen.contactlist.io.service.ContactListIntentService;
import com.example.armen.contactlist.pojo.Contact;
import com.example.armen.contactlist.util.Constant;
import com.example.armen.contactlist.util.NetworkUtil;
import com.google.common.eventbus.Subscribe;
public class ContactActivity extends BaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String LOG_TAG = ContactActivity.class.getSimpleName();

    // ===========================================================
    // Fields
    // ===========================================================

    private TextView mTvContactFullName;
    private TextView mTvContactEmail;
    private TextView mTvContactPhone;
    private TextView mTvContactNotes;
    private ImageView mIvContactImage;
    private LinearLayout mLlContactView;
    private Contact mContact;
    private long contactId;

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
        BusProvider.register(this);
        findViews();
        init();
        getData();
        //customizeActionBar();

        loadContact();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.unregister(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_contact;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    // ===========================================================
    // Click Listeners
    // ===========================================================

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

    @Subscribe
    public void onEventReceived(Contact contact) {
        mContact = contact;
        openViewLayout(mContact);
    }


    // ===========================================================
    // Methods
    // ===========================================================

    private void getData() {
        contactId = getIntent().getLongExtra(Constant.Extra.EXTRA_CONTACT_ID, 0);
    }

    private void findViews() {
        mIvContactImage = (ImageView) findViewById(R.id.iv_contact_image);
        mTvContactFullName = (TextView) findViewById(R.id.tv_contact_full_name);
        mTvContactEmail = (TextView) findViewById(R.id.tv_contact_email);
        mTvContactPhone = (TextView) findViewById(R.id.tv_contact_phone);
        mTvContactNotes = (TextView) findViewById(R.id.tv_contact_notes);
    }

    private void init() {

    }

    private void customizeActionBar() {
        setActionBarTitle(mContact.getfName());
    }

    private void loadContact() {
        if (NetworkUtil.getInstance().isConnected(this)) {
            ContactListIntentService.start(
                    this,
                    Constant.API.CONTACT_ITEM + contactId + Constant.API.CONTACT_ITEM_POSTFIX,
                    HttpRequestManager.RequestType.CONTACT_ITEM
            );

        } else {
        }
    }

    private void openViewLayout(Contact contact) {
        Glide.with(this)
                .load(contact.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mIvContactImage);
        mTvContactFullName.setText(contact.getfName() + Constant.Symbol.SPACE + contact.getlName());
        mTvContactEmail.setText(contact.getEmail());
        mTvContactPhone.setText(contact.getPhone());
        mTvContactNotes.setText(contact.getNotes());
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}