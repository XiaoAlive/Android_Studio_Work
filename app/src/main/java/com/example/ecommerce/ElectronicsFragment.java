package com.example.ecommerce;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class ElectronicsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_electronics, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        recyclerView = view.findViewById(R.id.recycler_view);

        // 设置下拉刷新监听
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // 初始化RecyclerView
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        // 准备商品数据
        initProducts();

        // 设置适配器
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initProducts() {
        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.electronic1, "高端电视", "¥5999.00"));
        productList.add(new Product(R.drawable.electronic2, "智能冰箱", "¥6999.00"));
        productList.add(new Product(R.drawable.electronic3, "洗衣机", "¥3999.00"));
        productList.add(new Product(R.drawable.electronic4, "空调", "¥4999.00"));
        productList.add(new Product(R.drawable.electronic5, "笔记本电脑", "¥7999.00"));
        productList.add(new Product(R.drawable.electronic6, "平板电脑", "¥2999.00"));
    }

    @Override
    public void onRefresh() {
        // 模拟刷新数据
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 清空原有数据
                productList.clear();

                // 添加新数据
                productList.add(new Product(R.drawable.electronic7, "新款电视", "¥6499.00"));
                productList.add(new Product(R.drawable.electronic8, "节能冰箱", "¥7499.00"));
                productList.add(new Product(R.drawable.electronic9, "静音洗衣机", "¥4499.00"));
                productList.add(new Product(R.drawable.electronic10, "变频空调", "¥5499.00"));

                // 通知适配器数据已更新
                adapter.notifyDataSetChanged();

                // 停止刷新动画
                swipeRefreshLayout.setRefreshing(false);

                // 显示刷新完成提示
                Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}