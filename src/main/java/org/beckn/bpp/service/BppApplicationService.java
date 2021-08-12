package org.beckn.bpp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.beckn.bpp.client.BppApiClient;
import org.beckn.bpp.common.RestApiClient;
import org.beckn.bpp.dto.OnSearchRequest;
import org.beckn.bpp.dto.Response;
import org.beckn.bpp.dto.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestOperations;

import java.util.List;

@Service
public class BppApplicationService {

    @Autowired
    private RestApiClient apiClient;

    @Autowired
    private BppApiClient bppClient;

    public BppApplicationService(RestOperations apiClient, ObjectMapper objectMapper) {
        this.apiClient = new RestApiClient(apiClient, objectMapper);
    }

    /**
     * Send cancellation request_id with reasons list in case of cancellation request. Else send cancelled order object
     *
     * @param request The request body
     * @param headers The headers received from BAP which needs to be validated
     * @return The response with ACK-NACK
     */
    public Response search(SearchRequest request, HttpHeaders headers) {
        var searchResponse = new OnSearchRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        //TODO: Construct and return error
        System.out.println("Is headers valid :: " + isHeadersValid);
        if (!isHeadersValid) return null;

        //TODO: Fetch the data based on the request
        var requestIntent = request.getMessage().getIntent();
        if (requestIntent.getProvider() != null) {
            //TODO Search based on the given provider info
            searchResponse = bppClient.getDataByProvider();
        }
        if (requestIntent.getFulfillment() != null) {
            //TODO Search based on the given fulfillment info
            searchResponse = bppClient.getDataByFulfillmentProvider();
        }
        //TODO: Call BAP on_search api with the search response
        var url = lookUp(headers);
        var response = apiClient.post(url[0] + "/on_search",
                constructResponseHeaders(),
                searchResponse,
                OnSearchRequest.class);
        System.out.println(response);
        return Response.of("ACK", null);
    }

    private boolean validateHeaders(HttpHeaders headers) {
        //TODO: logic to validate the headers
        return !CollectionUtils.isEmpty(headers.get(HttpHeaders.AUTHORIZATION)) ||
                !CollectionUtils.isEmpty(headers.get(HttpHeaders.AUTHORIZATION));
    }

    /**
     * Method to construct response headers to call BAP/BG
     *
     * @return HttpHeaders - needs to be returning after constructing based on the signature
     */
    private HttpHeaders constructResponseHeaders() {
        var headers = new HttpHeaders();
        var authHeader = ""; //TODO: Construct the auth header for response
        headers.put(HttpHeaders.AUTHORIZATION, List.of(authHeader));
        return headers;
    }

    /**
     * Function to lookup the Public Key & URI based in the Subscriber ID from Header
     *
     * @param headers Authorization Header from the request
     * @return Array with Callback URI & Public Key
     */
    private String[] lookUp(HttpHeaders headers) {
        var publicKey = "";
        var uri = "http://localhost:8081/bap";
        return new String[]{uri, publicKey};
    }
}
