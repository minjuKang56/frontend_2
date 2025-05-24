package com.example.chaekimjeo7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // 전달받은 값 꺼내기
        Intent intent = getIntent();
        String sellerName = intent.getStringExtra("sellerName");
        String bookTitle = intent.getStringExtra("bookTitle");

        // 헤더 텍스트뷰에 표시
        TextView chatHeader = findViewById(R.id.chatHeader);
        chatHeader.setText(sellerName + "님과의 채팅 (" + bookTitle + ")");
    }
}
