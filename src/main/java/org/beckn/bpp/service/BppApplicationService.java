package org.beckn.bpp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.beckn.bpp.client.BppApiClient;
import org.beckn.bpp.common.RestApiClient;
import org.beckn.bpp.dto.*;
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
        // Construct and return error
        if (!isHeadersValid) return null;

        // Fetch the data based on the request
        var requestIntent = request.getMessage().getIntent();
        if (requestIntent.getProvider() != null) {
            // Search based on the given provider info
            searchResponse = bppClient.getDataByProvider();
        }
        if (requestIntent.getFulfillment() != null) {
            // Search based on the given fulfillment info
            searchResponse = bppClient.getDataByFulfillmentProvider();
        }
        // Call BAP on_search api with the search response
        var url = lookUp(headers);
        var response = apiClient.post(url[0] + Context.ActionEnum.on_search,
                constructResponseHeaders(),
                searchResponse,
                OnSearchRequest.class);
        return Response.of("ACK", null);
    }

    public Response select(SelectRequest request, HttpHeaders headers) {
        var selectRequest = new OnSelectRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // get data for the selected items
        selectRequest = bppClient.getSelectedItemsData();

        // Call BAP on_select api with the search response
        var url = lookUp(headers);
        var response = apiClient.post(url[0] + Context.ActionEnum.on_select,
                constructResponseHeaders(),
                selectRequest,
                OnSelectRequest.class);

        return Response.of("ACK", null);
    }

    public Response init(InitRequest request, HttpHeaders headers) {
        var onInitRequest = new OnInitRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        onInitRequest = bppClient.initializeOrder();

        // Call BAP on_select api with the search response
        var url = lookUp(headers);
        var response = apiClient.post(url[0] + Context.ActionEnum.on_init,
                constructResponseHeaders(),
                onInitRequest,
                OnInitRequest.class);


        return Response.of("ACK", null);
    }

    public Response confirm(ConfirmRequest request, HttpHeaders headers) {
        var onConfirmRequest = new OnConfirmRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        onConfirmRequest = bppClient.confirmOrder();

        // Call BAP on_confirm api
        var url = lookUp(headers);
        var response = apiClient.post(url[0] + Context.ActionEnum.on_confirm,
                constructResponseHeaders(),
                onConfirmRequest,
                OnConfirmRequest.class);

        return Response.of("ACK", null);
    }

    public Response status(StatusRequest request, HttpHeaders headers) {
        var onStatusRequest = new OnStatusRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        onStatusRequest = bppClient.getStatus();

        // Call BAP on_status api
        var url = lookUp(headers);
        var response = apiClient.post(url[0] + Context.ActionEnum.on_status,
                constructResponseHeaders(),
                onStatusRequest,
                OnStatusRequest.class);

        return Response.of("ACK", null);
    }

    public Response track(TrackRequest request, HttpHeaders headers) {
        var onTrackRequest = new OnTrackRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        onTrackRequest = bppClient.getTracking();

        // Call BAP on_track api
        var url = lookUp(headers);
        var response = apiClient.post(url[0] + Context.ActionEnum.on_track,
                constructResponseHeaders(),
                onTrackRequest,
                OnTrackRequest.class);

        return Response.of("ACK", null);
    }

    public Response cancel(CancelRequest request, HttpHeaders headers) {
        var onCancelRequest = new OnCancelRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        onCancelRequest = bppClient.getCancelledOrder();

        // Call BAP on_cancel api
        var url = lookUp(headers);
        var response = apiClient.post(url[0] + Context.ActionEnum.on_cancel,
                constructResponseHeaders(),
                onCancelRequest,
                OnCancelRequest.class);

        return Response.of("ACK", null);
    }

    public Response update(UpdateRequest request, HttpHeaders headers) {
        var onUpdateRequest = new OnUpdateRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        onUpdateRequest = bppClient.updateOrder();

        // Call BAP on_update api
        var url = lookUp(headers);
        var response = apiClient.post(url[0] + Context.ActionEnum.on_update,
                constructResponseHeaders(),
                onUpdateRequest,
                OnUpdateRequest.class);

        return Response.of("ACK", null);
    }

    public Response rating(RatingRequest request, HttpHeaders headers) {
        var onRatingRequest = new OnRatingRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        onRatingRequest = bppClient.getFeedbackOnRating();

        // Call BAP on_rating api
        var url = lookUp(headers);
        var response = apiClient.post(url[0] + Context.ActionEnum.on_rating,
                constructResponseHeaders(),
                onRatingRequest,
                OnRatingRequest.class);

        return Response.of("ACK", null);
    }

    public Response support(SupportRequest request, HttpHeaders headers) {
        var onSupportRequest = new OnSupportRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        onSupportRequest = bppClient.getSupportContact();

        // Call BAP on_support api
        var url = lookUp(headers);
        var response = apiClient.post(url[0] + Context.ActionEnum.on_support,
                constructResponseHeaders(),
                onSupportRequest,
                OnSupportRequest.class);

        return Response.of("ACK", null);
    }

    public List<Option> getCancellationReasons(Context context, HttpHeaders headers) {
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        var reasons = List.<Option>of();

        return reasons;
    }

    public List<Option> getReturnReasons(Context context, HttpHeaders headers) {
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // Get the available list of return reasons
        var reasons = List.<Option>of();

        return reasons;
    }

    public List<RatingCategories> getRatingCategories(Context context, HttpHeaders headers) {
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // Get the available list of rating categories
        var categories = List.<RatingCategories>of();

        return categories;
    }

    private boolean validateHeaders(HttpHeaders headers) {
        // logic to validate the headers
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
        var authHeader = ""; // Construct the auth header for response
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
        var uri = "http://localhost:8081/bap/";
        return new String[]{uri, publicKey};
    }


}
