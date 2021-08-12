package org.beckn.bpp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.beckn.bpp.common.RestApiClient;
import org.beckn.bpp.dto.OnSearchRequest;
import org.beckn.bpp.dto.Response;
import org.beckn.bpp.dto.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import java.util.List;

@Service
public class BppApplicationService {

    @Autowired
    private RestApiClient apiClient;

    public BppApplicationService(RestOperations apiClient, ObjectMapper objectMapper) {
        this.apiClient = new RestApiClient(apiClient, objectMapper);
    }

    /**
     *  (asynchronously)
     * Send cancellation request_id with reasons list in case of cancellation request. Else send cancelled order object
     * @param request The request body
     * @param authHeader The Auth header received from BAP which needs to be validated
     * @param proxyAuthHeader The Auth header received from BG which needs to be validated
     * @return The response with ACK-NACK
     */
    @Async
    public Response search(SearchRequest request,
                           String authHeader,
                           String proxyAuthHeader) {
        //Validate the headers
        var isHeadersValid = validateHeaders(authHeader, proxyAuthHeader);
        //TODO: Construct and return error
        if (!isHeadersValid) return null;

        //TODO: Fetch the data based on the request

        //TODO: Construct OnSearchRequest based on the response
        OnSearchRequest SearchResponse = new OnSearchRequest();

        //TODO: Call BAP on_search api with the search response
        var response = apiClient.post(request.getContext().getBapUri(),
                constructResponseHeaders(),
                SearchResponse,
                OnSearchRequest.class);

        return Response.of("ACK", null);
    }

    private boolean validateHeaders(String authHeaders, String proxyAuthHeaders) {
        //TODO: logic to validate the headers
        return StringUtils.hasText(authHeaders) || StringUtils.hasText(proxyAuthHeaders);
    }

    private HttpHeaders constructResponseHeaders() {
        var headers = new HttpHeaders();
        var authHeader = ""; //TODO: Construct the auth header for response
        headers.put(HttpHeaders.AUTHORIZATION, List.of(authHeader));
        return headers;
    }
}
