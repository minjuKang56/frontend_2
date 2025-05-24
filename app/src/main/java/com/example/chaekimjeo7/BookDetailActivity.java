package com.example.chaekimjeo7;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookDetailActivity extends AppCompatActivity {

    private ImageView mainImage;
    private TextView bookTitleView, professorTag, categoryTag, salePriceView, bookDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.white);
        setContentView(R.layout.activity_book_detail); // 이미 만든 XML 레이아웃 그대로 사용

        // 🔗 XML 뷰 연결
        mainImage = findViewById(R.id.bookMainImage);
        bookTitleView = findViewById(R.id.bookTitle);
        professorTag = findViewById(R.id.professorTag);
        categoryTag = findViewById(R.id.categoryTag);
        salePriceView = findViewById(R.id.salePrice);
        bookDescriptionView = findViewById(R.id.bookDescription);

        // 📦 Intent로 전달받은 데이터 꺼내기
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String professor = intent.getStringExtra("professor");
        String category = intent.getStringExtra("category");
        String price = intent.getStringExtra("price");
        String description = intent.getStringExtra("description");
        String imageUriStr = intent.getStringExtra("imageUri");
        Log.d("BookDetail", "imageUriStr: " + imageUriStr);
        if (imageUriStr != null) {
            Uri imageUri = Uri.parse(imageUriStr);
            Glide.with(this).load(imageUri).into(mainImage);  // ✅ 외부 URI도 OK
        }

        // 📋 텍스트 세팅
        bookTitleView.setText(title);
        professorTag.setText(professor);
        categoryTag.setText(category);
        salePriceView.setText(price + "원");
        bookDescriptionView.setText(description);

        // 🖼 이미지 세팅
        if (imageUriStr != null) {
            Uri imageUri = Uri.parse(imageUriStr);
            Glide.with(this).load(imageUri).into(mainImage);  // ✅ 외부 URI도 OK
        }

        // ✅ 하단 네비게이션 바 클릭 이벤트 처리
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_home); // 현재 탭 강조

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // 상세페이지는 홈에서 유입된 경우가 많으니 이동 생략
                return true;
            } else if (id == R.id.nav_chat) {
                Intent chatIntent = new Intent(BookDetailActivity.this, ChatListActivity.class);
                startActivity(chatIntent);
                finish();
                return true;
            } else if (id == R.id.nav_profile) {
                return true;
            }

            return false;
        });
    }
}
