package com.example.chaekimjeo7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ChatListAdapter extends BaseAdapter {

    private Context context;
    private List<ChatRoomItem> chatList;

    public ChatListAdapter(Context context, List<ChatRoomItem> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @Override
    public int getCount() {
        return chatList.size();
    }

    @Override
    public Object getItem(int position) {
        return chatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            item = LayoutInflater.from(context).inflate(R.layout.chat_list_item, parent, false);
        }

        ImageView profileImage = item.findViewById(R.id.profileImage);
        TextView userName = item.findViewById(R.id.userName);
        TextView lastMessage = item.findViewById(R.id.lastMessage);
        TextView messageDate = item.findViewById(R.id.messageDate);

        ChatRoomItem chat = chatList.get(position);
        userName.setText(chat.getUserName());
        lastMessage.setText(chat.getLastMessage());
        messageDate.setText(chat.getDate());

        // TODO: Glide/Picasso로 프로필 이미지 적용 가능 (지금은 기본 아이콘)

        return item;
    }
}
