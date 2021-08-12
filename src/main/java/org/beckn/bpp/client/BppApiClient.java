package org.beckn.bpp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.beckn.bpp.dto.OnSearchRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BppApiClient {

    public OnSearchRequest getDataByFulfillmentProvider() {
        var inJson = BppApiClient.class.getResourceAsStream("/responses/on_search/return_fulfillment_catalog_with_providers.json");
        try {
            return new ObjectMapper().readValue(inJson, OnSearchRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OnSearchRequest();
    }

    public OnSearchRequest getDataByProvider() {
        var inJson = BppApiClient.class.getResourceAsStream("/responses/on_search/return_category_catalog.json");
        try {
            return new ObjectMapper().readValue(inJson, OnSearchRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OnSearchRequest();
    }
}
