package com.finance.ingestion.configs;

import com.finance.ingestion.repository.AlphaVantageRepository;
import com.finance.ingestion.repository.IngestionRepository;
import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackageClasses = IngestionRepository.class)
public class IngestionConfigs {

    @Bean
    public AlphaVantageRepository getAlphaVantageRepository() {
        return new AlphaVantageRepository();
    }

    @Bean
    public IngestionRepository getIngestionRepository() {
        return new IngestionRepository();
    }

    @Bean
    public JSONParser getJsonParser() {
        return new JSONParser();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
