package com.example.ecommerce;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CategoryFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager2 = view.findViewById(R.id.view_pager2);

        // 设置ViewPager2适配器
        viewPager2.setAdapter(new CategoryPagerAdapter(this));

        // 将TabLayout与ViewPager2关联
        new TabLayoutMediator(tabLayout, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(position == 0 ? "服装" : "电器");
                    }
                }).attach();

        return view;
    }

    private class CategoryPagerAdapter extends FragmentStateAdapter {

        public CategoryPagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return position == 0 ? new ClothingFragment() : new ElectronicsFragment();
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}