package com.tookitaki.bitcoin.handler;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BitcoinPriceResponseTemplate {

    public String detail;
    public String currency;
    public String base;
    public List<Price> prices;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "BitcoinPriceResponseTemplate{" +
                "detail='" + detail + '\'' +
                ", currency='" + currency + '\'' +
                ", base='" + base + '\'' +
                ", prices=" + prices +
                '}';
    }
}
