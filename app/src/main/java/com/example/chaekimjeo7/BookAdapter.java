package com.example.chaekimjeo7;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> bookList;
    private Context context;

    public BookAdapter(List<Book> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.title.setText(book.getTitle());
        holder.price.setText(book.getSalePrice() + "원");
        holder.market.setText("시세 " + book.getMarketPrice() + "원");
        holder.image.setImageResource(book.getImageResId());

        // ✅ 책 클릭 시 상세 페이지로 이동 + 데이터 전달
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookSellDetailActivity.class);
            intent.putExtra("title", book.getTitle());
            intent.putExtra("salePrice", book.getSalePrice());
            intent.putExtra("marketPrice", book.getMarketPrice());
            intent.putExtra("imageResId", book.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, market;
        ImageView image;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookTitle);
            price = itemView.findViewById(R.id.bookPrice);
            market = itemView.findViewById(R.id.bookMarket);
            image = itemView.findViewById(R.id.bookImage);
        }
    }
}

