package com.tookitaki.bitcoin.handler;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinBaseTemplate {







    public Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CoinBaseTemplate{" +
                "data=" + data +
                '}';
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Price{

    public float price;
    public Date time;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                ", time=" + time +
                '}';
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Data{

    public String base;
    public String currency;
    public  List<Price> prices;



    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }


    @Override
    public String toString() {
        return "Data{" +
                "base='" + base + '\'' +
                ", currency=" + currency +
                ", prices=" + prices +
                '}';
    }
}

