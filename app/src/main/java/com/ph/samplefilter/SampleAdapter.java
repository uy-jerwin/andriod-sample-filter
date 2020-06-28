package com.ph.samplefilter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SampleAdapter extends RecyclerView.Adapter<SampleViewHolder> implements Filterable {

    private List<SampleItem> items;
    private List<SampleItem> itemsOriginal;

    public SampleAdapter(final List<SampleItem> items) {
        this.items = items;
        this.itemsOriginal = new ArrayList<>(items);
    }

    @Override
    public Filter getFilter() {
        // static instance of the filter to be used outside in order to filter out the contents
        return exampleFilter;
    }

    @NonNull
    @Override
    public SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleViewHolder holder, int position) {
        final TextView view = holder.getView();
        view.setText(items.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Simple Filter implementation using contains method of String
     * We can change the implementation to use some more advance like database call etc
     */
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final List<SampleItem> results = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                results.addAll(itemsOriginal);
            } else {
                final String query = constraint.toString().trim();
                for (int i = 0 ; i < itemsOriginal.size(); i++)
                {
                    final SampleItem item = itemsOriginal.get(i);
                    if (item.getText().contains(query))
                    {
                        results.add(item);
                    }
                }
            }
            final FilterResults filterResults = new FilterResults();
            filterResults.values = results;
            return filterResults;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            items.clear();
            items.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}

