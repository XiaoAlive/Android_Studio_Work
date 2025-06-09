package com.example.ecommerce;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private List<Integer> bannerImages;
    private Handler handler;
    private Runnable runnable;
    private RecyclerView marketRecyclerView, recommendedRecyclerView, staggeredRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 初始化广告轮播
        initBanner(view);

        // 初始化市场图标网格
        initMarketGridView(view);

        // 初始化推荐商品网格
        initRecommendedGridView(view);

        // 初始化瀑布流布局
        initStaggeredGridView(view);

        return view;
    }

    private void initBanner(View view) {
        viewPager = view.findViewById(R.id.banner_view_pager);
        radioGroup = view.findViewById(R.id.banner_radio_group);

        // 准备轮播图片数据
        bannerImages = new ArrayList<>();
        bannerImages.add(R.drawable.banner1);
        bannerImages.add(R.drawable.banner2);
        bannerImages.add(R.drawable.banner3);

        // 设置ViewPager适配器
        BannerPagerAdapter adapter = new BannerPagerAdapter();
        viewPager.setAdapter(adapter);

        // 初始化指示器
        initIndicators();

        // 设置页面切换监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // 更新指示器状态
                ((RadioButton) radioGroup.getChildAt(position % bannerImages.size())).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // 自动轮播
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentItem + 1);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    private void initIndicators() {
        radioGroup.removeAllViews();
        for (int i = 0; i < bannerImages.size(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(i);
            radioButton.setButtonDrawable(android.R.color.transparent);
            radioButton.setBackgroundResource(i == 0 ? R.drawable.indicator_selected : R.drawable.indicator_unselected);
            radioGroup.addView(radioButton);
        }
    }

    private void initMarketGridView(View view) {
        marketRecyclerView = view.findViewById(R.id.market_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 5);
        marketRecyclerView.setLayoutManager(layoutManager);

        // 准备市场图标数据
        List<MarketItem> marketItems = new ArrayList<>();
        marketItems.add(new MarketItem(R.drawable.market1, "图标1"));
        marketItems.add(new MarketItem(R.drawable.market2, "图标2"));
        marketItems.add(new MarketItem(R.drawable.market3, "图标3"));
        marketItems.add(new MarketItem(R.drawable.market4, "图标4"));
        marketItems.add(new MarketItem(R.drawable.market5, "图标5"));
        marketItems.add(new MarketItem(R.drawable.market6, "图标6"));
        marketItems.add(new MarketItem(R.drawable.market7, "图标7"));
        marketItems.add(new MarketItem(R.drawable.market8, "图标8"));
        marketItems.add(new MarketItem(R.drawable.market9, "图标9"));
        marketItems.add(new MarketItem(R.drawable.market10, "图标10"));

        MarketAdapter adapter = new MarketAdapter(marketItems);
        marketRecyclerView.setAdapter(adapter);
    }

    private void initRecommendedGridView(View view) {
        recommendedRecyclerView = view.findViewById(R.id.recommended_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recommendedRecyclerView.setLayoutManager(layoutManager);

        // 准备推荐商品数据
        List<Product> recommendedProducts = new ArrayList<>();
        recommendedProducts.add(new Product(R.drawable.product1, "推荐商品1", "¥199.00"));
        recommendedProducts.add(new Product(R.drawable.product2, "推荐商品2", "¥299.00"));
        recommendedProducts.add(new Product(R.drawable.product3, "推荐商品3", "¥399.00"));
        recommendedProducts.add(new Product(R.drawable.product4, "推荐商品4", "¥499.00"));

        ProductAdapter adapter = new ProductAdapter(recommendedProducts);
        recommendedRecyclerView.setAdapter(adapter);
    }

    private void initStaggeredGridView(View view) {
        staggeredRecyclerView = view.findViewById(R.id.staggered_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredRecyclerView.setLayoutManager(layoutManager);

        // 准备瀑布流商品数据
        List<Product> staggeredProducts = new ArrayList<>();
        staggeredProducts.add(new Product(R.drawable.electronic1, "电器商品1", "¥1999.00"));
        staggeredProducts.add(new Product(R.drawable.electronic2, "电器商品2", "¥2999.00"));
        staggeredProducts.add(new Product(R.drawable.electronic3, "电器商品3", "¥3999.00"));
        staggeredProducts.add(new Product(R.drawable.electronic4, "电器商品4", "¥4999.00"));
        staggeredProducts.add(new Product(R.drawable.electronic5, "电器商品5", "¥5999.00"));
        staggeredProducts.add(new Product(R.drawable.electronic6, "电器商品6", "¥6999.00"));

        StaggeredProductAdapter adapter = new StaggeredProductAdapter(staggeredProducts);
        staggeredRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 停止轮播
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

    private class BannerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(bannerImages.get(position % bannerImages.size()));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}