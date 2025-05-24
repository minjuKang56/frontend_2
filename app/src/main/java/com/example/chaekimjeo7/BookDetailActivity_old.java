package com.example.chaekimjeo7;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Arrays;
import java.util.List;

public class BookDetailActivity_old extends AppCompatActivity {

    private ViewPager2 imageSlider;
    private TextView imageIndicator;
    private TextView bookTitleView, originalPriceView, salePriceView, discountRateView, statusBadge;
    private ImageView mainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        imageSlider = findViewById(R.id.imageSlider);
        imageIndicator = findViewById(R.id.imageIndicator);
        mainImage = findViewById(R.id.bookMainImage);

        List<Integer> images = Arrays.asList(
                R.drawable.book_sample1,
                R.drawable.book_sample2,
                R.drawable.book_sample3,
                R.drawable.book_sample4,
                R.drawable.book_sample5
        );

        ImageSliderAdapter adapter = new ImageSliderAdapter(images);
        imageSlider.setAdapter(adapter);
        imageIndicator.setText("1/" + images.size());

        imageSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                imageIndicator.setText((position + 1) + "/" + images.size());
            }
        });

        bookTitleView = findViewById(R.id.bookTitle);
        originalPriceView = findViewById(R.id.originalPrice);
        salePriceView = findViewById(R.id.salePrice);
        discountRateView = findViewById(R.id.discountRate);
        statusBadge = findViewById(R.id.bookStatusBadge);

        Intent intent = getIntent();
        String bookTitle = intent.getStringExtra("title");
        int salePrice = intent.getIntExtra("salePrice", 0);
        int marketPrice = intent.getIntExtra("marketPrice", 0);
        int imageResId = intent.getIntExtra("imageResId", R.drawable.book_sample1);

        int discountPercent = 0;
        if (marketPrice > 0 && salePrice > 0) {
            discountPercent = Math.round(((float)(marketPrice - salePrice) / marketPrice) * 100);
        }

        bookTitleView.setText(bookTitle);
        originalPriceView.setText(marketPrice + "원");
        originalPriceView.setPaintFlags(originalPriceView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        salePriceView.setText(salePrice + "원");
        discountRateView.setText(discountPercent + "%");
        mainImage.setImageResource(imageResId);

        // 상태 뱃지 설정 (하드코딩 예시: 예약중)
        String bookStatus = "예약중";
        statusBadge.setText(bookStatus);
        int backgroundColor;
        switch (bookStatus) {
            case "판매중":
                backgroundColor = 0xFFC8E6C9;
                break;
            case "예약중":
                backgroundColor = 0xFFFFF59D;
                break;
            case "판매완료":
                backgroundColor = 0xFFE0E0E0;
                break;
            default:
                backgroundColor = 0xFFE0E0E0;
        }
        statusBadge.setBackgroundColor(backgroundColor);

        // 찜 아이콘 토글
        ImageView likeIcon = findViewById(R.id.bookLikeIcon);
        final boolean[] isLiked = {false};
        likeIcon.setOnClickListener(v -> {
            isLiked[0] = !isLiked[0];
            likeIcon.setImageResource(isLiked[0] ? R.drawable.ic_heart_filled : R.drawable.ic_heart_outline);
        });

        // 신고 버튼
        ImageButton reportButton = findViewById(R.id.reportButton);
        reportButton.setOnClickListener(v -> {
            final String[] reasons = {"비방 및 욕설", "부적절한 사진", "무통보 거래 파기", "기타"};
            final int[] selectedIndex = {-1};

            AlertDialog.Builder builder = new AlertDialog.Builder(BookDetailActivity_old.this);
            builder.setTitle("신고 사유를 선택해주세요");
            builder.setSingleChoiceItems(reasons, -1, (dialog, which) -> selectedIndex[0] = which);
            builder.setPositiveButton("신고하기", (dialog, which) -> {
                if (selectedIndex[0] != -1) {
                    String selectedReason = reasons[selectedIndex[0]];
                    Toast.makeText(this, "신고 접수: " + selectedReason, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "신고 사유를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("취소", (dialog, which) -> dialog.dismiss());
            builder.show();
        });

        // 문의 버튼 → 채팅화면 이동
        Button inquiryButton = findViewById(R.id.inquiryButton);

        inquiryButton.setOnClickListener(v -> {
            Intent chatIntent = new Intent(this, ChatActivity.class);
            chatIntent.putExtra("sellerName", "강민주"); // 실제 변수로 바꿔도 좋아요
            chatIntent.putExtra("bookTitle", bookTitle); // 위에서 받은 책 제목 그대로 사용
            startActivity(chatIntent);
        });
    }
}