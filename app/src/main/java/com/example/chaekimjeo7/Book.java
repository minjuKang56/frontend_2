package com.example.chaekimjeo7;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private int salePrice;
    private int originalPrice;
    private int marketPrice;   // 정가
    private int imageResId;
    private String professor;
    private String category;

    // 생성자
    public Book(String title, int salePrice, int marketPrice, int originalPrice,
                int imageResId, String professor, String category) {
        this.title = title;
        this.salePrice = salePrice;
        this.marketPrice = marketPrice;
        this.originalPrice = originalPrice;
        this.imageResId = imageResId;
        this.professor = professor;
        this.category = category;
    }

    // Getter 메서드들
    public String getTitle() { return title; }
    public int getSalePrice() { return salePrice; }
    public int getMarketPrice() { return marketPrice; }
    public int getOriginalPrice() { return originalPrice; }
    public int getImageResId() { return imageResId; }
    public String getProfessor() { return professor; }
    public String getCategory() { return category; }
}
