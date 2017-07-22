package com.example.armen.contactlist.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.armen.contactlist.R;
import com.example.armen.contactlist.io.bus.BusProvider;
import com.example.armen.contactlist.io.rest.HttpRequestManager;
import com.example.armen.contactlist.io.service.ContactListIntentService;
import com.example.armen.contactlist.pojo.Contact;
import com.example.armen.contactlist.ui.activity.ContactActivity;
import com.example.armen.contactlist.ui.adapter.ContactAdapter;
import com.example.armen.contactlist.util.Constant;
import com.example.armen.contactlist.util.NetworkUtil;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;

public class ContactListFragment extends BaseFragment implements View.OnClickListener,
        ContactAdapter.OnItemClickListener {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String LOG_TAG = ContactListFragment.class.getSimpleName();

    // ===========================================================
    // Fields
    // ===========================================================
    private Bundle mArgumentData;
    private RecyclerView mRv;
    private ContactAdapter mRecyclerViewAdapter;
    private LinearLayoutManager mLlm;
    private ArrayList<Contact> mContactArrayList;

    // ===========================================================
    // Constructors
    // ===========================================================

    public static ContactListFragment newInstance() {
        return new ContactListFragment();
    }

    public static ContactListFragment newInstance(Bundle args) {
        ContactListFragment fragment = new ContactListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass
    // ===========================================================

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        BusProvider.register(this);
        findViews(view);
        init();
        setListeners();
        getData();
        customizeActionBar();

        loadContacts();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BusProvider.unregister(this);
    }

    // ===========================================================
    // Click Listeners
    // ===========================================================

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    @Override
    public void onItemClick(Contact contact, int position) {
        Intent intent = new Intent(getContext(), ContactActivity.class);
        Log.d(LOG_TAG, "hayeer"+ contact.getId());
        intent.putExtra(Constant.Extra.EXTRA_CONTACT_ID, contact.getId());
        Log.d(LOG_TAG, contact.getfName());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(Contact contact, int position) {
    }

    // ===========================================================
    // Other Listeners, methods for/from Interfaces
    // ===========================================================

    @Subscribe
    public void onEventReceived(ArrayList<Contact> contacts) {
        mContactArrayList.clear();
        mContactArrayList.addAll(contacts);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private void setListeners() {

    }

    private void findViews(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv_contact_list);
    }

    private void init() {
        mRv.setHasFixedSize(true);
        mLlm = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(mLlm);
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mContactArrayList = new ArrayList<>();
        mRecyclerViewAdapter = new ContactAdapter(mContactArrayList, this);
        mRv.setAdapter(mRecyclerViewAdapter);
    }

    public void getData() {
        if (getArguments() != null) {
            mArgumentData = getArguments().getBundle(Constant.Argument.ARGUMENT_DATA);
        }
    }

    private void customizeActionBar() {

    }

    private void loadContacts() {
        if (NetworkUtil.getInstance().isConnected(getActivity())) {
            ContactListIntentService.start(
                    getActivity(),
                    Constant.API.CONTACT_LIST,
                    HttpRequestManager.RequestType.CONTACT_LIST
            );
        } else {
            Toast.makeText(getContext(),"internet chka", Toast.LENGTH_SHORT).show();
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}