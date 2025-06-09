package com.example.ecommerce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class StaggeredProductAdapter extends RecyclerView.Adapter<StaggeredProductAdapter.StaggeredProductViewHolder> {

    private List<Product> productList;
    private Random random;

    public StaggeredProductAdapter(List<Product> productList) {
        this.productList = productList;
        this.random = new Random();
    }

    @NonNull
    @Override
    public StaggeredProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staggered_product, parent, false);
        return new StaggeredProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.imageView.setImageResource(product.getImageResId());
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(product.getPrice());

        // 随机高度，形成瀑布流效果
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = 300 + random.nextInt(200);
        holder.itemView.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class StaggeredProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView priceTextView;

        public StaggeredProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.staggered_product_image);
            nameTextView = itemView.findViewById(R.id.staggered_product_name);
            priceTextView = itemView.findViewById(R.id.staggered_product_price);
        }
    }
}