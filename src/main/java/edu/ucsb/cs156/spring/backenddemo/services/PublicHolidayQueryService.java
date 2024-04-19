package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import org.springframework.http.*;

import java.util.List;
import java.util.Map;



@Slf4j
@Service
public class PublicHolidayQueryService {


    private final RestTemplate restTemplate;

    public PublicHolidayQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "https://date.nager.at/swagger/index.html";

    public String getJSON(String year, String countryCode) throws HttpClientErrorException {
        log.info("year={}", year);
        log.info("countryCode={}", countryCode);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        Map<String, String> uriVariables = Map.of("year", year, "countryCode", countryCode);
        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT, HttpMethod.GET, entity, String.class, uriVariables);
        return re.getBody();
    }

   

}