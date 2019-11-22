package com.finance.ingestion.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stock {

    private String symbol;

    private Date timestamp;

    private float open;

    private float high;

    private float low;

    private float close;

    private int volume;

    public Stock(String symbol, Date timestamp, float open, float high, float low, float close, int volume) {
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

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
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
        // convert the given date this.timestamp to a string of date pattern that mongoDB can parse
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String timestampStr = format.format(this.timestamp);

        return "{" +
                "\"symbol\": \"" + this.symbol + "\", " +
                "\"timestamp\": \"" + timestampStr + "\", " +
                "\"open\": " + this.open + ", " +
                "\"high\": " + this.high + ", " +
                "\"low\": " + this.low + ", " +
                "\"close\": " + this.close + ", " +
                "\"volume\": " + this.volume +
                "}";
    }
}