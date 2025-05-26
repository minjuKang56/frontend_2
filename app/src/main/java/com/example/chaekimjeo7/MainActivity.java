package com.example.chaekimjeo7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 categoryViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewPager2 연결
        categoryViewPager = findViewById(R.id.categoryViewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CategoryMajorFragment());
        fragments.add(new CategoryEtcFragment());

        FragmentStateAdapter pagerAdapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        };
        categoryViewPager.setAdapter(pagerAdapter);

        // 등록 버튼
        ImageButton fabRegister = findViewById(R.id.fabRegister);
        fabRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BookRegisterActivity.class);
            startActivity(intent);
        });

        // 상단 검색 버튼
        ImageView searchIcon = findViewById(R.id.registerIcon);
        searchIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BookListAllActivity.class);
            startActivity(intent);
        });

        // 시스템바 패딩 적용
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 바텀 네비게이션
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                return true;
            } else if (itemId == R.id.nav_chat) {
                Intent intent = new Intent(MainActivity.this, ChatListActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.nav_profile) {
                return true;
            }

            return false;
        });
    }

    // ✅ Fragment에서 호출: 카테고리 클릭 시 교재 리스트 페이지로 이동
    public void onCategoryClicked(String categoryName) {
        Intent intent = new Intent(MainActivity.this, BookListByCategoryActivity.class);
        intent.putExtra("category", categoryName);
        startActivity(intent);
    }
}
