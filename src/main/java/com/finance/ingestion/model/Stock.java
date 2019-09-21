package com.finance.ingestion.model;

public class Stock {

    private String symbol;

    private String timestamp;

    private float open;

    private float high;

    private float low;

    private float close;

    private int volume;

    public Stock(String symbol, String timestamp, float open, float high, float low, float close, int volume) {
        this.symbol = symbol;
        this.timestamp = timestamp;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public float getOpen() {
        return this.open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return this.high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return this.low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getClose() {
        return this.close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "{" +
                "\"symbol\": \"" + this.symbol + "\", " +
                "\"timestamp\": \"" + this.timestamp + "\", " +
                "\"open\": " + this.open + ", " +
                "\"high\": " + this.high + ", " +
                "\"low\": " + this.low + ", " +
                "\"close\": " + this.close + ", " +
                "\"volume\": " + this.volume +
                "}";
    }
}