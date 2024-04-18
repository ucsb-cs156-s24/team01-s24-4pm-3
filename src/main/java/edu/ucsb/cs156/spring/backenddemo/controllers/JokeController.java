package edu.ucsb.cs156.spring.backenddemo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucsb.cs156.spring.backenddemo.services.JokeQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Joke info from Joke api")
@Slf4j
@RestController
@RequestMapping("/api/jokes")
public class JokeController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    JokeQueryService jokeQueryService;

    @Operation(summary = "Get 1 or 2 Programming or Spooky joke(s)", description = "JSON return format documented here: https://v2.jokeapi.dev/")
    @GetMapping("/get")
    public ResponseEntity<String> getJokes(
        @Parameter(name="category", description="category of joke", example="Programming") @RequestParam String category,
        @Parameter(name="numJokes", description="number of jokes to return", example="1") @RequestParam int numJokes
    ) throws JsonProcessingException {
        log.info("getJokes: category={} numJokes={}", category, numJokes);
        String result = jokeQueryService.getJSON(category, numJokes);
        return ResponseEntity.ok().body(result);
    }
}
