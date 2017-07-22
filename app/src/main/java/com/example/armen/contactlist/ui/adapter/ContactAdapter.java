package com.example.armen.contactlist.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.armen.contactlist.R;
import com.example.armen.contactlist.pojo.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String LOG_TAG = ContactAdapter.class.getSimpleName();

    // ===========================================================
    // Fields
    // ===========================================================

    private ArrayList<Contact> mContactArrayList;
    private OnItemClickListener mOnItemClickListener;

    // ===========================================================
    // Constructors
    // ===========================================================

    public ContactAdapter(ArrayList<Contact> contactArrayList, OnItemClickListener onItemClickListener) {
        mContactArrayList = contactArrayList;
        mOnItemClickListener = onItemClickListener;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass
    // ===========================================================

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_item_contact, viewGroup, false);
        return new ViewHolder(view, mContactArrayList, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return mContactArrayList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
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

    static class ViewHolder extends RecyclerView.ViewHolder {

        Context context;
        TextView tvContactTitle;
        TextView tvContactPrice;
        ImageView ivContactImage;
        LinearLayout llItemContainer;
        OnItemClickListener onItemClickListener;
        ArrayList<Contact> contactArrayList;

        ViewHolder(View itemView, ArrayList<Contact> contactArrayList, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.context = itemView.getContext();
            this.contactArrayList = contactArrayList;
            this.onItemClickListener = onItemClickListener;
            findViews(itemView);
        }

        void findViews(View view) {
            llItemContainer = (LinearLayout) view.findViewById(R.id.ll_contact_item_container);
            tvContactTitle = (TextView) view.findViewById(R.id.tv_contact_item_first_name);
            tvContactPrice = (TextView) view.findViewById(R.id.tv_contact_item_phone);
            ivContactImage = (ImageView) view.findViewById(R.id.iv_contact_item_logo);
        }

        void bindData() {

            Glide.with(itemView.getContext())
                    .load(contactArrayList.get(getAdapterPosition()).getImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivContactImage);

            tvContactTitle.setText(contactArrayList.get(getAdapterPosition()).getfName());

            tvContactPrice.setText(contactArrayList.get(getAdapterPosition()).getPhone());

            llItemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(contactArrayList.get(getAdapterPosition()),
                            getAdapterPosition());
                }
            });

            llItemContainer.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(contactArrayList.get(getAdapterPosition()),
                            getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public interface OnItemClickListener {

        void onItemClick(Contact contact, int position);

        void onItemLongClick(Contact contact, int position);

    }
}
