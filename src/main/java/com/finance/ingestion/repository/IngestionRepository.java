package com.finance.ingestion.repository;

import com.finance.ingestion.model.Cryptocurrency;
import com.finance.ingestion.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class IngestionRepository {

    @Autowired
    private HttpHeaders httpHeaders;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${database.library.uri}")
    private String databaseLibraryUri;

    public void ingestStocks(List<Stock> stocks) {
        String uri = this.databaseLibraryUri.concat("/stocks/");

        for (int i = 0; i < stocks.size(); i++) {
            HttpEntity<String> request = new HttpEntity<String>(stocks.get(i).toString(), this.httpHeaders);
            this.restTemplate.postForEntity(uri, request, String.class);
        }
    }

    public void ingestCryptos(List<Cryptocurrency> cryptos) {
        String uri = this.databaseLibraryUri.concat("/cryptos/");

        for (int i = 0; i < cryptos.size(); i++) {
            HttpEntity<String> request = new HttpEntity<String>(cryptos.get(i).toString(), this.httpHeaders);
            this.restTemplate.postForEntity(uri, request, String.class);
        }
    }
}
