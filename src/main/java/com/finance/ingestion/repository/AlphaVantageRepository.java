package com.finance.ingestion.repository;

import com.finance.ingestion.model.Cryptocurrency;
import com.finance.ingestion.model.Stock;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

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
        List<Stock> stocks = new ArrayList<>();

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
                String timestampStr = (String) pair.getKey();
                String openStr = (String) stockData.get("1. open");
                String highStr = (String) stockData.get("2. high");
                String lowStr = (String) stockData.get("3. low");
                String closeStr = (String) stockData.get("4. close");
                String volumeStr = (String) stockData.get("5. volume");

                Date timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timestampStr);
                float open = Float.parseFloat(openStr);
                float high = Float.parseFloat(highStr);
                float low = Float.parseFloat(lowStr);
                float close = Float.parseFloat(closeStr);
                int volume = Integer.parseInt(volumeStr);

                Stock stock = new Stock(symbol, timestamp, open, high, low, close, volume);

                stocks.add(stock);
            }
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }

        return stocks;
    }

    public List<Cryptocurrency> getCryptos(String function, String symbol, String market) {
        List<Cryptocurrency> cryptos = new ArrayList<Cryptocurrency>();

        String uri = this.alphavantageUri
                .concat("function=" + function + "&")
                .concat("symbol=" + symbol + "&")
                .concat("market=" + market + "&")
                .concat("apikey=" + this.apiKey);

        String result = this.restTemplate.getForObject(uri, String.class);

        try {
            JSONObject jsonData = (JSONObject) this.jsonParser.parse(result);

            Map cryptosData = (Map) jsonData.get("Time Series (Digital Currency Daily)");

            Iterator<Map.Entry> cryptosIterator = cryptosData.entrySet().iterator();
            while (cryptosIterator.hasNext()) {
                Map.Entry pair = cryptosIterator.next();
                JSONObject cryptoData = (JSONObject) this.jsonParser.parse(pair.getValue().toString());
                String timestamp = (String) pair.getKey();
                String openStr = (String) cryptoData.get("1a. open (" + market + ")");
                String highStr = (String) cryptoData.get("2a. high (" + market + ")");
                String lowStr = (String) cryptoData.get("3a. low (" + market + ")");
                String closeStr = (String) cryptoData.get("4a. close (" + market + ")");
                String volumeStr = (String) cryptoData.get("5. volume");
                String marketCapStr = (String) cryptoData.get("6. market cap (" + market + ")");

                float open = Float.parseFloat(openStr);
                float high = Float.parseFloat(highStr);
                float low = Float.parseFloat(lowStr);
                float close = Float.parseFloat(closeStr);
                float volume = Float.parseFloat(volumeStr);
                float marketCap = Float.parseFloat(marketCapStr);

                Cryptocurrency cryptocurrency = new Cryptocurrency(symbol, timestamp, open, high, low, close, volume, marketCap);

                cryptos.add(cryptocurrency);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cryptos;

    }
}