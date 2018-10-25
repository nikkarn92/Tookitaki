package com.tookitaki.bitcoin.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class CoinBaseApiHandler {


    private static final String COIN_BASE_ALL_PERIOD_URI="https://www.coinbase.com/api/v2/prices/BTC-USD/historic?period=all";

    private static CoinBaseTemplate getAllPeriodBitCoinData(){
        RestTemplate restTemplate = new RestTemplate();
        CoinBaseTemplate coinBaseTemplate = restTemplate.getForObject(COIN_BASE_ALL_PERIOD_URI, CoinBaseTemplate.class);
        System.out.println(coinBaseTemplate.toString());

        return coinBaseTemplate;
    }

    /*
    *
    * @Author: Nikhil Karn
    *
    *
    *
     */
    public BitcoinPriceResponseTemplate getLastWeekBitcoinPrice(){

        BitcoinPriceResponseTemplate bitcoinPriceResponseTemplate=new BitcoinPriceResponseTemplate();

        CoinBaseTemplate coinBaseTemplate=getAllPeriodBitCoinData();
        List<Price> priceList= coinBaseTemplate.getData().getPrices();


        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        int tempVar = calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();

        calendar.add(Calendar.DATE, -tempVar - 7);
        Date start = calendar.getTime();
        calendar.add(Calendar.DATE, 6);
        Date end = calendar.getTime();

        List<Price> filteredPrice =
                priceList.stream()
                        .filter(p -> p.getTime().before(end))
                        .filter(p -> p.getTime().after(start))
                        .sorted(Comparator.comparing(Price::getTime)) // sort by date
                        .collect(Collectors.toList());

        bitcoinPriceResponseTemplate.setDetail("Bitcoin Price list for last week i.e from "+start+" to "+end);
        bitcoinPriceResponseTemplate.setPrices(filteredPrice);
        bitcoinPriceResponseTemplate.setCurrency(coinBaseTemplate.getData().getCurrency());
        bitcoinPriceResponseTemplate.setBase(coinBaseTemplate.getData().getBase());


        return bitcoinPriceResponseTemplate;
    }


    /*
     *
     * @Author: Nikhil Karn
     *
     *
     *
     */
    public BitcoinPriceResponseTemplate getLastMonthBitcoinPrice(){
        BitcoinPriceResponseTemplate bitcoinPriceResponseTemplate=new BitcoinPriceResponseTemplate();

        CoinBaseTemplate coinBaseTemplate=getAllPeriodBitCoinData();
        List<Price> priceList= coinBaseTemplate.getData().getPrices();


        Calendar aCalendar = Calendar.getInstance();

        aCalendar.set(Calendar.DATE, 1);
        aCalendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDateOfPreviousMonth = aCalendar.getTime();
        aCalendar.set(Calendar.DATE, 1);

        aCalendar.set(Calendar.HOUR, 0);
        aCalendar.set(Calendar.MINUTE, 0);
        aCalendar.set(Calendar.SECOND, 0);
        aCalendar.set(Calendar.HOUR_OF_DAY, 0);

        Date firstDateOfPreviousMonth = aCalendar.getTime();

        List<Price> filteredPrice =
                priceList.stream()
                        .filter(p -> p.getTime().before(lastDateOfPreviousMonth))
                        .filter(p -> p.getTime().after(firstDateOfPreviousMonth))
                        .sorted(Comparator.comparing(Price::getTime)) // sort by date
                        .collect(Collectors.toList());

        bitcoinPriceResponseTemplate.setDetail("Bitcoin Price list for last month i.e from "+firstDateOfPreviousMonth+" to "+lastDateOfPreviousMonth);
        bitcoinPriceResponseTemplate.setPrices(filteredPrice);
        bitcoinPriceResponseTemplate.setCurrency(coinBaseTemplate.getData().getCurrency());
        bitcoinPriceResponseTemplate.setBase(coinBaseTemplate.getData().getBase());


        return bitcoinPriceResponseTemplate;
    }


    /*
     *
     * @Author: Nikhil Karn
     *@Details: method to get data of last year bitcoin price
     *
     *
     */
    public BitcoinPriceResponseTemplate getLastYearBitcoinPrice(){
        BitcoinPriceResponseTemplate bitcoinPriceResponseTemplate=new BitcoinPriceResponseTemplate();

        CoinBaseTemplate coinBaseTemplate=getAllPeriodBitCoinData();
        List<Price> priceList= coinBaseTemplate.getData().getPrices();


        Calendar aCalendar = Calendar.getInstance();


        aCalendar.add(Calendar.YEAR, -1);
        aCalendar.set(Calendar.MONTH,0);
        aCalendar.set(Calendar.DATE,1);
        aCalendar.set(Calendar.HOUR, 0);
        aCalendar.set(Calendar.MINUTE, 0);
        aCalendar.set(Calendar.SECOND, 0);
        aCalendar.set(Calendar.HOUR_OF_DAY, 0);

        Date firstDateOfPreviousYear = aCalendar.getTime();



        aCalendar.set(Calendar.MONTH,11);
        aCalendar.set(Calendar.DATE,31);
        aCalendar.set(Calendar.HOUR, 23);
        aCalendar.set(Calendar.MINUTE, 55);
        aCalendar.set(Calendar.SECOND, 55);
        aCalendar.set(Calendar.HOUR_OF_DAY, 23);

        Date lastDateOfPreviousYear = aCalendar.getTime();

        List<Price> filteredPrice =
                priceList.stream()
                        .filter(p -> p.getTime().before(lastDateOfPreviousYear))
                        .filter(p -> p.getTime().after(firstDateOfPreviousYear))
                        .sorted(Comparator.comparing(Price::getTime)) // sort by date
                        .collect(Collectors.toList());

        bitcoinPriceResponseTemplate.setDetail("Bitcoin Price list for last year i.e from "+firstDateOfPreviousYear+" to "+lastDateOfPreviousYear);
        bitcoinPriceResponseTemplate.setPrices(filteredPrice);
        bitcoinPriceResponseTemplate.setCurrency(coinBaseTemplate.getData().getCurrency());
        bitcoinPriceResponseTemplate.setBase(coinBaseTemplate.getData().getBase());


        return bitcoinPriceResponseTemplate;
    }

    public BitcoinPriceResponseTemplate getInBetweenDateBitcoinPrice(String fromDate,String toDate){

        BitcoinPriceResponseTemplate bitcoinPriceResponseTemplate=new BitcoinPriceResponseTemplate();

        CoinBaseTemplate coinBaseTemplate=getAllPeriodBitCoinData();
        List<Price> priceList= coinBaseTemplate.getData().getPrices();


        Date inputFromromDate;
        Date inputToDate;
        try {
             inputFromromDate = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
             inputToDate = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);
        }catch (Exception e){
            throw new RuntimeException("INVALID DATE FORMAT, PLEASE ENTER DATE IN DD-MM-YYYY format");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputFromromDate);

        Date tempinputFromromDate = calendar.getTime();


        calendar = Calendar.getInstance();
        calendar.setTime(inputToDate);

        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 55);
        calendar.set(Calendar.SECOND, 55);
        calendar.set(Calendar.HOUR_OF_DAY, 23);

        Date tempinputToDate = calendar.getTime();

        List<Price> filteredPrice =
                priceList.stream()
                        .filter(p -> p.getTime().before(tempinputToDate))
                        .filter(p -> p.getTime().after(tempinputFromromDate))
                        .sorted(Comparator.comparing(Price::getTime)) // sort by date
                        .collect(Collectors.toList());

        bitcoinPriceResponseTemplate.setDetail("Bitcoin Price list from "+tempinputFromromDate+" to "+tempinputToDate);
        bitcoinPriceResponseTemplate.setPrices(filteredPrice);
        bitcoinPriceResponseTemplate.setCurrency(coinBaseTemplate.getData().getCurrency());
        bitcoinPriceResponseTemplate.setBase(coinBaseTemplate.getData().getBase());


        return bitcoinPriceResponseTemplate;
    }



    public Map<String,String> getAverageBetweenCustomDate(String fromDate, String toDate){

        BitcoinPriceResponseTemplate tempResponse=getInBetweenDateBitcoinPrice(fromDate,toDate);

        List<Price> filteredPrice=tempResponse.prices;

        Double tempSum=0.0;

        for(Price tempPrice:filteredPrice){
            tempSum=tempSum+tempPrice.getPrice();
        }

        Double average=tempSum/filteredPrice.size();

        DecimalFormat df = new DecimalFormat("#.##");
        average = Double.valueOf(df.format(average));

        Map<String,String> response=new HashMap<>();
        response.put("avgPrice",""+average);
        response.put("response","Average price of bitcoin from "+fromDate+" to "+toDate+" is Rs: "+average);

        return  response;
    }
}
