package com.example.tony.gadgeothek.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

public class ReservationViewHolder extends RecyclerView.ViewHolder {
    public View parent;
    public CheckBox checkBox;

    public ReservationViewHolder(View parent, CheckBox checkBox) {
        super(parent);
        this.parent = parent;
        this.checkBox = checkBox;
    }
}
