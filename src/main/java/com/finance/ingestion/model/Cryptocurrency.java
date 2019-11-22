package com.finance.ingestion.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cryptocurrency {

    private String symbol;

    private Date timestamp;

    private float open;

    private float high;

    private float low;

    private float close;

    private float volume;

    private float marketCap;

    public Cryptocurrency(String symbol, Date timestamp, float open, float high, float low, float close, float volume, float marketCap) {
        this.symbol = symbol;
        this.timestamp = timestamp;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.marketCap = marketCap;
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

    public float getVolume() {
        return this.volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getMarketCap() {
        return this.marketCap;
    }

    public void setMarketCap(float marketCap) {
        this.marketCap = marketCap;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String timestampStr = format.format(this.timestamp);

        return "{" +
                "\"symbol\": \"" + this.symbol + "\", " +
                "\"timestamp\": \"" + timestampStr + "\", " +
                "\"open\": " + this.open + ", " +
                "\"high\": " + this.high + ", " +
                "\"low\": " + this.low + ", " +
                "\"close\": " + this.close + ", " +
                "\"volume\": " + this.volume + ", " +
                "\"marketCap\": " + this.marketCap +
                "}";
    }
}
