package com.tookitaki.bitcoin.controller;

import com.tookitaki.bitcoin.handler.BitcoinPriceResponseTemplate;
import com.tookitaki.bitcoin.handler.CoinBaseApiHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/getbitstats")
public class BitCoinStatsController {

    @Autowired
    CoinBaseApiHandler coinBaseApiCall;


    @GetMapping("/average/{fromDate}/{toDate}")
    public Map<String,String> getCustomDatePrice(@PathVariable("fromDate") String fromDate, @PathVariable ("toDate") String toDate){
        Map<String,String> response=coinBaseApiCall.getAverageBetweenCustomDate(fromDate,toDate);


        return  response;
    }
}
