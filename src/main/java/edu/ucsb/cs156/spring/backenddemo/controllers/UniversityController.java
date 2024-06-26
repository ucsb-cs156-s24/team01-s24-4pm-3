package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.CountryCodeQueryService;
import lombok.extern.slf4j.Slf4j;
import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="University info", description="JSON return format documented here: http://universities.hipolabs.com/search")

// @Parameter(name="name", description="name of university", example="100")
@Slf4j
@RestController
@RequestMapping("/api/university")
public class UniversityController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UniversityQueryService universityQueryService;

    @Operation(summary = "Get list of universities that match a given name", description = "Uses API documented here: http://universities.hipolabs.com/search")
    @GetMapping("/get")
    public ResponseEntity<String> getUniversityName(
        @Parameter(name="name", example="Harvard") @RequestParam String name
    ) throws JsonProcessingException {
        String result = universityQueryService.getJSON(name);
        return ResponseEntity.ok().body(result);
    }

}