package com.example.ecommerce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {

    private List<MarketItem> marketItems;

    public MarketAdapter(List<MarketItem> marketItems) {
        this.marketItems = marketItems;
    }

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market, parent, false);
        return new MarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {
        MarketItem item = marketItems.get(position);
        holder.imageView.setImageResource(item.getImageResId());
        holder.textView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return marketItems.size();
    }

    public static class MarketViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MarketViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.market_image);
            textView = itemView.findViewById(R.id.market_text);
        }
    }
}