package com.tookitaki.bitcoin.controller;


import com.tookitaki.bitcoin.handler.BitcoinPriceResponseTemplate;
import com.tookitaki.bitcoin.handler.CoinBaseApiHandler;
import com.tookitaki.bitcoin.handler.CoinBaseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getbitcoinprice")
public class BitcoinPriceDetailController {

    @Autowired
    CoinBaseApiHandler coinBaseApiCall;


    @GetMapping("/lastweek")
    public BitcoinPriceResponseTemplate getLastWeekPrice(){
        BitcoinPriceResponseTemplate bitcoinPriceResponseTemplate=coinBaseApiCall.getLastWeekBitcoinPrice();
        return  bitcoinPriceResponseTemplate;
    }

    @GetMapping("/lastmonth")
    public BitcoinPriceResponseTemplate getLastMonthPrice(){
        BitcoinPriceResponseTemplate bitcoinPriceResponseTemplate=coinBaseApiCall.getLastMonthBitcoinPrice();
        return  bitcoinPriceResponseTemplate;
    }

    @GetMapping("/lastyear")
    public BitcoinPriceResponseTemplate getLastYearPrice(){
        BitcoinPriceResponseTemplate bitcoinPriceResponseTemplate=coinBaseApiCall.getLastYearBitcoinPrice();
        return  bitcoinPriceResponseTemplate;
    }

    @GetMapping("/customdaterange/{fromDate}/{toDate}")
    public BitcoinPriceResponseTemplate getCustomDatePrice(@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate){

        BitcoinPriceResponseTemplate bitcoinPriceResponseTemplate=coinBaseApiCall.getInBetweenDateBitcoinPrice(fromDate,toDate);
        return  bitcoinPriceResponseTemplate;
    }
}
