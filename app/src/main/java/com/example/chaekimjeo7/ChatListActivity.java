package com.example.chaekimjeo7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {

    private ListView chatListView;
    private List<ChatRoomItem> chatRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        chatListView = findViewById(R.id.chatListView);
        chatRooms = new ArrayList<>();

        // 더미 데이터 (나중에 API 연결로 대체 가능)
        chatRooms.add(new ChatRoomItem("이기연", "안녕하세요 책 구매하고 싶어요", "5월 3일"));
        chatRooms.add(new ChatRoomItem("정예원", "안녕하세요!", "5월 3일"));

        ChatListAdapter adapter = new ChatListAdapter(this, chatRooms);
        chatListView.setAdapter(adapter);

        // 채팅방 클릭 시 채팅방 화면으로 이동
        chatListView.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            ChatRoomItem selectedChat = chatRooms.get(position);
            Intent intent = new Intent(ChatListActivity.this, ChatRoomActivity.class);
            intent.putExtra("userName", selectedChat.getUserName());
            startActivity(intent);
        });

        // ✅ 하단 네비게이션 바 클릭 이벤트 처리
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_chat); // 현재 탭 강조

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                Intent intent = new Intent(ChatListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            } else if (id == R.id.nav_chat) {
                // 현재 화면이므로 아무 동작 안 함
                return true;
            } else if (id == R.id.nav_profile) {
                // 프로필 화면 연결할 경우 여기에 작성
                return true;
            }

            return false;
        });
    }
}
