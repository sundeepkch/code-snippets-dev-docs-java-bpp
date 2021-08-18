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
     */
    public Response search(SearchRequest request, HttpHeaders headers) {
        var searchResponse = new OnSearchRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // Fetch the data based on the request
        var requestIntent = request.getMessage().getIntent();

        // Execute business logic here

        // Generate the response
        searchResponse = generateSearchResponse();
        //invoke on_search api
        return invokeOnSearch(searchResponse, headers);

    }

    public OnSearchRequest generateSearchResponse() {
        // Convert response into protocol object
        return bppClient.getSampleSearchResponse();
    }

    public Response invokeOnSearch(OnSearchRequest searchResponse, HttpHeaders headers) {
        if(searchResponse.getError() == null) {
            // Call BAP on_search api with the search response
            var url = lookUp(headers);
            var response = apiClient.post(url[0] + Context.ActionEnum.on_search,
                    constructResponseHeaders(),
                    searchResponse,
                    OnSearchRequest.class);
            return Response.of("ACK", null);
        } else {
            return Response.of("NACK", null);
        }
    }

    public Response select(SelectRequest request, HttpHeaders headers) {
        var selectResponse = new OnSelectRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // Fetch the data based on the request
        var requestIntent = request.getMessage().getOrder();

        // Execute business logic here

        // Generate the response
        selectResponse = generateSelectResponse(requestIntent);

        //invoke on_select api
        return invokeOnSelect(selectResponse, headers);
    }

    public OnSelectRequest generateSelectResponse(Object selectedResults) {
        var selectResult = bppClient.getSampleSelectResponse();
        // Convert response into protocol object similar to example above
        return selectResult;
    }

    public Response invokeOnSelect(OnSelectRequest selectResponse, HttpHeaders headers) {
        if(selectResponse.getError() == null) {
            // Call BAP on_select api with the select response

            // Call to look up function which returns the the public key and BAP/BG Endpoint to be called
            var url = lookUp(headers);

            var response = apiClient.post(url[0] + Context.ActionEnum.on_select,
                    constructResponseHeaders(),
                    selectResponse,
                    OnSelectRequest.class);
            return Response.of("ACK", null);
        } else {
            return Response.of("NACK", null);
        }
    }

    public Response init(InitRequest request, HttpHeaders headers) {
        var initResponse = new OnInitRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // Fetch the data based on the request
        var requestOrder = request.getMessage().getOrder();

        // Execute business logic here

        // Generate the response
        initResponse = generateInitResponse(requestOrder);

        //invoke on_init api
        return invokeOnInit(initResponse, headers);
    }

    public OnInitRequest generateInitResponse(Object orderResponse) {
        var initResult = bppClient.initializeOrder();
        // Convert response into protocol object similar to example above
        return initResult;
    }

    public Response invokeOnInit(OnInitRequest initResponse, HttpHeaders headers) {
        if(initResponse.getError() == null) {
            // Call BAP on_init api with the order response

            // Call to look up function which returns the the public key and BAP Endpoint to be called
            var url = lookUp(headers);

            var response = apiClient.post(url[0] + Context.ActionEnum.on_init,
                    constructResponseHeaders(),
                    initResponse,
                    OnInitRequest.class);
            return Response.of("ACK", null);
        } else {
            return Response.of("NACK", null);
        }
    }

    public Response confirm(ConfirmRequest request, HttpHeaders headers) {
        var onConfirmRequest = new OnConfirmRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // Confirm the given order
        var confirmOrder = request.getMessage().getOrder();

        // Execute business logic here

        // Generate the response
        onConfirmRequest = generateConfirmResponse(confirmOrder);

        //invoke on_conrim api
        return invokeOnConfirm(onConfirmRequest, headers);
    }

    public OnConfirmRequest generateConfirmResponse(Object orderResponse) {
        var confirmResult = bppClient.confirmOrder();
        // Convert response into protocol object similar to example above
        return confirmResult;
    }

    public Response invokeOnConfirm(OnConfirmRequest confirmResponse, HttpHeaders headers) {
        if(confirmResponse.getError() == null) {
            // Call BAP on_confirm api with the order response

            // Call to look up function which returns the the public key and BAP Endpoint to be called
            var url = lookUp(headers);

            var response = apiClient.post(url[0] + Context.ActionEnum.on_confirm,
                    constructResponseHeaders(),
                    confirmResponse,
                    OnConfirmRequest.class);
            return Response.of("ACK", null);
        } else {
            return Response.of("NACK", null);
        }
    }

    public Response status(StatusRequest request, HttpHeaders headers) {
        var onStatusRequest = new OnStatusRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // Get the latest order status
        var orderId = request.getMessage().getOrderId();

        // Execute business logic here

        // Generate the response
        onStatusRequest = generateStatusResponse(orderId);

        //invoke on_status api
        return invokeOnStatus(onStatusRequest, headers);
    }

    public OnStatusRequest generateStatusResponse(String orderId) {
        var statusResult = bppClient.getStatus();
        // Convert response into protocol object similar to example above
        return statusResult;
    }

    public Response invokeOnStatus(OnStatusRequest statusResponse, HttpHeaders headers) {
        if(statusResponse.getError() == null) {
            // Call BAP on_status api with the status response

            // Call to look up function which returns the the public key and BAP/BG Endpoint to be called
            var url = lookUp(headers);

            var response = apiClient.post(url[0] + Context.ActionEnum.on_status,
                    constructResponseHeaders(),
                    statusResponse,
                    OnStatusRequest.class);
            return Response.of("ACK", null);
        } else {
            return Response.of("NACK", null);
        }
    }

    public Response track(TrackRequest request, HttpHeaders headers) {
        var onTrackRequest = new OnTrackRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // Track the given order
        var trackOrder = request.getMessage().getOrderId();

        // Execute business logic here

        // Generate the response
        onTrackRequest = generateTrackingResponse(trackOrder);

        //invoke on_track api
        return invokeOnTrack(onTrackRequest, headers);
    }

    public OnTrackRequest generateTrackingResponse(Object trackingDetails) {
        var trackResult = bppClient.getTracking();
        // Convert response into protocol object similar to example above
        return trackResult;
    }

    public Response invokeOnTrack(OnTrackRequest trackResponse, HttpHeaders headers) {
        if(trackResponse.getError() == null) {
            // Call BAP on_track api with the order response

            // Call to look up function which returns the the public key and BAP Endpoint to be called
            var url = lookUp(headers);

            var response = apiClient.post(url[0] + Context.ActionEnum.on_track,
                    constructResponseHeaders(),
                    trackResponse,
                    OnTrackRequest.class);
            return Response.of("ACK", null);
        } else {
            return Response.of("NACK", null);
        }
    }

    public Response cancel(CancelRequest request, HttpHeaders headers) {
        var onCancelRequest = new OnCancelRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // Cancel the given order
        var cancelOrder = request.getMessage().getOrderId();

        // Execute business logic here

        // Generate the response
        onCancelRequest = generateCancelledOrderResponse(cancelOrder);

        //invoke on_cancel api
        return invokeOnCancel(onCancelRequest, headers);
    }

    public OnCancelRequest generateCancelledOrderResponse(Object cancelOrderDetails) {
        var cancelResult = bppClient.getCancelledOrder();
        // Convert response into protocol object similar to example above
        return cancelResult;
    }

    public Response invokeOnCancel(OnCancelRequest cancelResponse, HttpHeaders headers) {
        if(cancelResponse.getError() == null) {
            // Call BAP on_cancel api with the order response

            // Call to look up function which returns the the public key and BAP Endpoint to be called
            var url = lookUp(headers);

            // Call BAP on_cancel api with the cancelled order object
            var response = apiClient.post(url[0] + Context.ActionEnum.on_cancel,
                    constructResponseHeaders(),
                    cancelResponse,
                    OnCancelRequest.class);
            return Response.of("ACK", null);
        } else {
            return Response.of("NACK", null);
        }
    }

    public Response update(UpdateRequest request, HttpHeaders headers) {
        var onUpdateRequest = new OnUpdateRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // Update the given order
        var updateOrder = request.getMessage().getOrder();

        // Execute business logic here

        // Generate the response
        onUpdateRequest = generateUpdatedOrderResponse(updateOrder);

        //invoke on_update api
        return invokeOnUpdate(onUpdateRequest, headers);
    }

    public OnUpdateRequest generateUpdatedOrderResponse(Object updatedDetails) {
        var updatedResult = bppClient.updateOrder();
        // Convert response into protocol object similar to example above
        return updatedResult;
    }

    public Response invokeOnUpdate(OnUpdateRequest updateResponse, HttpHeaders headers) {
        if(updateResponse.getError() == null) {
            // Call BAP on_update api with the order response

            // Call to look up function which returns the the public key and BAP Endpoint to be called
            var url = lookUp(headers);

            var response = apiClient.post(url[0] + Context.ActionEnum.on_update,
                    constructResponseHeaders(),
                    updateResponse,
                    OnUpdateRequest.class);
            return Response.of("ACK", null);
        } else {
            return Response.of("NACK", null);
        }
    }

    public Response rating(RatingRequest request, HttpHeaders headers) {
        var onRatingRequest = new OnRatingRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // Rate the given order
        var rateOrder = request.getMessage().getId();

        // Execute business logic here

        // Generate the response
        onRatingRequest = rateDelivery(rateOrder);

        //invoke on_rate api
        return invokeOnRating(onRatingRequest, headers);
    }

    public OnRatingRequest rateDelivery(Object orderRating) {
        var ratingResult = bppClient.getFeedbackOnRating();
        // Convert response into protocol object similar to example above
        return ratingResult;
    }

    public Response invokeOnRating(OnRatingRequest ratingResponse, HttpHeaders headers) {
        if(ratingResponse.getError() == null) {
            // Call BAP on_rating api with the order response

            // Call to look up function which returns the the public key and BAP Endpoint to be called
            var url = lookUp(headers);

            // Call BAP on_rating api with the rating order object
            var response = apiClient.post(url[0] + Context.ActionEnum.on_rating,
                    constructResponseHeaders(),
                    ratingResponse,
                    OnRatingRequest.class);
            return Response.of("ACK", null);
        } else {
            return Response.of("NACK", null);
        }
    }

    public Response support(SupportRequest request, HttpHeaders headers) {
        var onSupportRequest = new OnSupportRequest();
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        // Construct and return error
        if (!isHeadersValid) return null;

        // support contact for the given order
        var orderSupport = request.getMessage().getRefId();

        // Execute business logic here

        // Generate the response
        onSupportRequest = supportDelivery(orderSupport);

        //invoke on_support api
        return invokeOnSupport(onSupportRequest, headers);
    }

    public OnSupportRequest supportDelivery(Object support) {
        var supportResult = bppClient.getSupportContact();
        // Convert response into protocol object similar to example above
        return supportResult;
    }

    public Response invokeOnSupport(OnSupportRequest supportResponse, HttpHeaders headers) {
        if(supportResponse.getError() == null) {
            // Call BAP on_support api with the order response

            // Call to look up function which returns the the public key and BAP Endpoint to be called
            var url = lookUp(headers);

            // Call BAP on_support with the support contact details
            var response = apiClient.post(url[0] + Context.ActionEnum.on_support,
                    constructResponseHeaders(),
                    supportResponse,
                    OnSupportRequest.class);
            return Response.of("ACK", null);
        } else {
            return Response.of("NACK", null);
        }
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
