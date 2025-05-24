package com.example.chaekimjeo7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookListByCategoryActivity extends AppCompatActivity {

    private RecyclerView bookRecyclerView;
    private BookAdapter bookAdapter;
    private List<Book> bookList;
    private Button sortPopular, sortLowPrice, sortRecent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_by_category);

        bookRecyclerView = findViewById(R.id.bookRecyclerView);
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 샘플 데이터 (썸네일 이미지 리소스 추가)
        bookList = new ArrayList<>();
        bookList.add(new Book("객체지향 프로그래밍", 15000, 15284, R.drawable.book_sample1));
        bookList.add(new Book("인공지능과 기계학습", 16000, 16000, R.drawable.book_sample2));
        bookList.add(new Book("데이터패터이식", 10000, 14000, R.drawable.book_sample3));

        bookAdapter = new BookAdapter(bookList, this);
        bookRecyclerView.setAdapter(bookAdapter);

        // 정렬 버튼들
        sortPopular = findViewById(R.id.sortPopular);
        sortLowPrice = findViewById(R.id.sortLowPrice);
        sortRecent = findViewById(R.id.sortRecent);

        sortPopular.setOnClickListener(v -> {
            highlightSelectedTab(sortPopular);
            // 인기순 정렬 (여기선 기본 유지)
        });

        sortLowPrice.setOnClickListener(v -> {
            highlightSelectedTab(sortLowPrice);
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getSalePrice() - o2.getSalePrice();
                }
            });
            bookAdapter.notifyDataSetChanged();
        });

        sortRecent.setOnClickListener(v -> {
            highlightSelectedTab(sortRecent);
            Collections.reverse(bookList); // 최근등록순 예시
            bookAdapter.notifyDataSetChanged();
        });

        highlightSelectedTab(sortPopular); // 기본 강조
    }

    private void highlightSelectedTab(Button selected) {
        Button[] allTabs = {sortPopular, sortLowPrice, sortRecent};
        for (Button btn : allTabs) {
            btn.setBackgroundResource(R.drawable.tab_unselected);
            btn.setTextColor(getResources().getColor(android.R.color.black));
        }
        selected.setBackgroundResource(R.drawable.tab_selected);
        selected.setTextColor(getResources().getColor(android.R.color.black));
    }


}
