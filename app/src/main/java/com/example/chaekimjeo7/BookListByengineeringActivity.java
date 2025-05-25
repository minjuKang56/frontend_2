package com.example.chaekimjeo7;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookListByengineeringActivity extends AppCompatActivity {

    private RecyclerView bookRecyclerView;
    private BookAdapter bookAdapter;
    private List<Book> bookList;
    private Button sortLowPrice, sortRecent;
    private EditText searchInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_by_category);

        // ✅ 상단 카테고리명 표시
        String categoryName = getIntent().getStringExtra("category");
        TextView pageTitle = findViewById(R.id.pageTitle);
        if (categoryName != null && !categoryName.isEmpty()) {
            pageTitle.setText(categoryName);
        }

        // ✅ 검색창 연결
        searchInput = findViewById(R.id.searchInput);

        // ✅ 리사이클러뷰 세팅
        bookRecyclerView = findViewById(R.id.bookRecyclerView);
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 샘플 데이터
        bookList = new ArrayList<>();
        bookList.add(new Book("객체지향 프로그래밍", 15000, 28000, 15284, R.drawable.book_sample1, "김스베틀라나", "공과대학"));
        bookList.add(new Book("인공지능과 기계학습", 16000, 24000, 16000, R.drawable.book_sample2, "홍길동", "공과대학"));
        bookList.add(new Book("자료구조", 10000, 20000, 14000, R.drawable.book_sample3, "이영희", "공과대학"));
        bookList.add(new Book("논리회로", 10000, 22000, 14000, R.drawable.book_sample4, "최철수", "공과대학"));
        bookList.add(new Book("객체지향 프로그래밍", 10000, 28000, 14000, R.drawable.book_sample5, "김스베틀라나", "공과대학"));

        // ✅ 어댑터 세팅
        bookAdapter = new BookAdapter(bookList, this);
        bookRecyclerView.setAdapter(bookAdapter);

        // ✅ 검색 기능 연동 (실시간 필터링)
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bookAdapter.filter(s.toString());
            }
        });

        // ✅ 정렬 버튼
        sortLowPrice = findViewById(R.id.sortLowPrice);
        sortRecent = findViewById(R.id.sortRecent);

        sortLowPrice.setOnClickListener(v -> {
            highlightSelectedTab(sortLowPrice);

            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getSalePrice() - o2.getSalePrice();
                }
            });

            bookAdapter = new BookAdapter(bookList, this);         // 정렬된 리스트로 새로 초기화
            bookRecyclerView.setAdapter(bookAdapter);               // 새 어댑터 연결
            bookAdapter.filter(searchInput.getText().toString());   // 검색 필터 재적용
        });

        sortRecent.setOnClickListener(v -> {
            highlightSelectedTab(sortRecent);

            Collections.reverse(bookList);                          // 최신순 정렬
            bookAdapter = new BookAdapter(bookList, this);
            bookRecyclerView.setAdapter(bookAdapter);
            bookAdapter.filter(searchInput.getText().toString());
        });


        // ✅ 하단 네비게이션 바 처리
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_home); // 현재 위치 표시

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                return true;
            } else if (id == R.id.nav_chat) {
                Intent intent = new Intent(this, ChatListActivity.class);
                startActivity(intent);
                finish();
                return true;
            } else if (id == R.id.nav_profile) {
                Toast.makeText(this, "프로필 기능 준비 중입니다.", Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;
        });
    }

    private void highlightSelectedTab(Button selected) {
        Button[] allTabs = {sortLowPrice, sortRecent};
        for (Button btn : allTabs) {
            btn.setBackgroundResource(R.drawable.tab_unselected);
            btn.setTextColor(getResources().getColor(android.R.color.black));
        }
        selected.setBackgroundResource(R.drawable.tab_selected);
        selected.setTextColor(getResources().getColor(android.R.color.black));
    }
}
