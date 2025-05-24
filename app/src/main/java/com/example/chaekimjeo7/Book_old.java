package com.example.chaekimjeo7;

public class Book_old {
    private String title;
    private int salePrice;
    private int marketPrice;
    private int imageResId;

    public Book_old(String title, int salePrice, int marketPrice, int imageResId) {
        this.title = title;
        this.salePrice = salePrice;
        this.marketPrice = marketPrice;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public int getMarketPrice() {
        return marketPrice;
    }

    public int getImageResId() {
        return imageResId;
    }
}
