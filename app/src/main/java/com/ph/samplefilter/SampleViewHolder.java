package com.ph.samplefilter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SampleViewHolder extends RecyclerView.ViewHolder {

    private TextView view;

    public SampleViewHolder(@NonNull View view) {
        super(view);
        this.view = (TextView) itemView.findViewById(R.id.textView1);
    }

    public TextView getView() {
        return view;
    }

    public void setView(TextView view) {
        this.view = view;
    }
}

