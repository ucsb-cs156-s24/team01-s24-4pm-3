package edu.ucsb.cs156.spring.backenddemo.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(LocationQueryService.class)
public class LocationQueryServiceTests {
    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private LocationQueryService locationQueryService;

    @Test
    public void test_getJSON() {
        String location = "ucsb";
        String expectedURL = LocationQueryService.ENDPOINT.replace("{location}", location);
        String fakeJsonResult = "{ \"fake\" : \"result\" }";

        this.mockRestServiceServer.expect(requestTo(expectedURL))
                .andExpect(header("Accept", MediaType.APPLICATION_JSON.toString()))
                .andExpect(header("Content-Type", MediaType.APPLICATION_JSON.toString()))
                .andRespond(withSuccess(fakeJsonResult, MediaType.APPLICATION_JSON));

        String actualResult = locationQueryService.getJSON(location);
        assertEquals(fakeJsonResult, actualResult);
    }

}
