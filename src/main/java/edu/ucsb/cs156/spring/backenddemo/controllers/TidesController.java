package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.spring.backenddemo.services.LocationQueryService;
import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Tides info from api.tidesandcurrents.noaa.gov") // https://api.tidesandcurrents.noaa.gov/api/prod/datagetter?application=ucsb-cs156&begin_date=20230710&end_date=20230712&station=9411340&product=predictions&datum=mllw&units=english&time_zone=lst_ldt&interval=hilo&format=json	
@Slf4j
@RestController
@RequestMapping("/api/tides/")
public class TidesController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    TidesQueryService tidesQueryService;

    @Operation(summary = "Get list of Tides that match a given beginDate, endDate, and station name",
               description =  "Uses API documented here: https://api.tidesandcurrents.noaa.gov/api/prod/\t")
    @GetMapping("/get")
    public ResponseEntity<String> getTides(
            @Parameter(name="beginDate", description="Begin date", example = "20231231") @RequestParam String beginDate,
            @Parameter(name="endDate", description="End date", example = "20240120") @RequestParam String endDate,
            @Parameter(name="station", description="Station ID", example = "9411340") @RequestParam String station
    ) throws JsonProcessingException {
        log.info("getTides: beginDate={}, endDate={}, station={}", beginDate, endDate, station);
        String result = tidesQueryService.getJSON(beginDate, endDate, station);
        return ResponseEntity.ok().body(result);
    }

}