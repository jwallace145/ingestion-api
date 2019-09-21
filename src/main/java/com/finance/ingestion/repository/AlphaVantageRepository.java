package com.finance.ingestion.repository;

import com.finance.ingestion.model.Stock;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
this class is in charge of calling the alpha vantage api, consuming the data, and parsing the data
 */
public class AlphaVantageRepository {

    @Autowired
    private JSONParser jsonParser;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${alphavantage.uri}")
    private String alphavantageUri;

    @Value("${api.key}")
    private String apiKey;

    public List<Stock> getStocks(String function, String symbol, String interval, String outputSize) {
        List<Stock> stocks = new ArrayList<Stock>();

        String uri = this.alphavantageUri
                .concat("function=" + function + "&")
                .concat("symbol=" + symbol + "&")
                .concat("interval=" + interval + "&")
                .concat("apikey=" + this.apiKey + "&")
                .concat("outputsize=" + outputSize);

        String result = this.restTemplate.getForObject(uri, String.class);

        try {
            JSONObject jsonData = (JSONObject) this.jsonParser.parse(result);

            Map stocksData = (Map) jsonData.get("Time Series " + "(" + interval + ")");

            Iterator<Map.Entry> stocksIterator = stocksData.entrySet().iterator();
            while (stocksIterator.hasNext()) {
                Map.Entry pair = stocksIterator.next();
                JSONObject stockData = (JSONObject) this.jsonParser.parse(pair.getValue().toString());
                String timestamp = (String) pair.getKey();
                String openStr = (String) stockData.get("1. open");
                String highStr = (String) stockData.get("2. high");
                String lowStr = (String) stockData.get("3. low");
                String closeStr = (String) stockData.get("4. close");
                String volumeStr = (String) stockData.get("5. volume");

                float open = Float.parseFloat(openStr);
                float high = Float.parseFloat(highStr);
                float low = Float.parseFloat(lowStr);
                float close = Float.parseFloat(closeStr);
                int volume = Integer.parseInt(volumeStr);

                Stock stock = new Stock(symbol, timestamp, open, high, low, close, volume);

                stocks.add(stock);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return stocks;
    }
}