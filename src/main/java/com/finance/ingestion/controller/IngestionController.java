package com.finance.ingestion.controller;

import com.finance.ingestion.model.Stock;
import com.finance.ingestion.repository.AlphaVantageRepository;
import com.finance.ingestion.repository.IngestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingest")
public class IngestionController {

    @Autowired
    private AlphaVantageRepository alphaVantageRepository;

    @Autowired
    private IngestionRepository ingestionRepository;

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health() {
        return "{ \"status\" : \"healthy\" }";
    }

    @RequestMapping(value = "/stocks/{function}/{symbol}/{interval}/{outputSize}", method = RequestMethod.GET)
    public void ingestStocks(@PathVariable("function") String function, @PathVariable("symbol") String symbol,
                             @PathVariable("interval") String interval, @PathVariable("outputSize") String outputSize) {
        List<Stock> stocks = this.alphaVantageRepository.getStocks(function, symbol, interval, outputSize);
        this.ingestionRepository.ingestStocks(stocks);
    }

}
