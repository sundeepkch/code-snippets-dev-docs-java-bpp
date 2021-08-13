package org.beckn.bpp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.beckn.bpp.dto.*;
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

    public OnSelectRequest getSelectedItemsData() {
        var inJson = BppApiClient.class.getResourceAsStream("/responses/on_select/return_quote.json");
        try {
            return new ObjectMapper().readValue(inJson, OnSelectRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OnSelectRequest();
    }

    public OnInitRequest initializeOrder() {
        var inJson = BppApiClient.class.getResourceAsStream("/responses/on_init/return_updated_quote_with_payment_term_on_order.json");
        try {
            return new ObjectMapper().readValue(inJson, OnInitRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OnInitRequest();
    }

    public OnConfirmRequest confirmOrder() {
        var inJson = BppApiClient.class.getResourceAsStream("/responses/on_confirm/confirm_order_on_order_paid.json");
        try {
            return new ObjectMapper().readValue(inJson, OnConfirmRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OnConfirmRequest();
    }

    public OnStatusRequest getStatus() {
        var inJson = BppApiClient.class.getResourceAsStream("/responses/on_status/return_status_active.json");
        try {
            return new ObjectMapper().readValue(inJson, OnStatusRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OnStatusRequest();
    }

    public OnTrackRequest getTracking() {
        var inJson = BppApiClient.class.getResourceAsStream("/responses/on_track/return_tracking.json");
        try {
            return new ObjectMapper().readValue(inJson, OnTrackRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OnTrackRequest();
    }

    public OnCancelRequest getCancelledOrder() {
        var inJson = BppApiClient.class.getResourceAsStream("/responses/on_cancel/order_cancelled.json");
        try {
            return new ObjectMapper().readValue(inJson, OnCancelRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OnCancelRequest();
    }

    public OnUpdateRequest updateOrder() {
        var inJson = BppApiClient.class.getResourceAsStream("/responses/on_update/updated_order.json");
        try {
            return new ObjectMapper().readValue(inJson, OnUpdateRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OnUpdateRequest();
    }

    public OnRatingRequest getFeedbackOnRating() {
        var inJson = BppApiClient.class.getResourceAsStream("/responses/on_rating/ack_of_rating_and_feedback_form.json");
        try {
            return new ObjectMapper().readValue(inJson, OnRatingRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OnRatingRequest();
    }

    public OnSupportRequest getSupportContact() {
        var inJson = BppApiClient.class.getResourceAsStream("/responses/on_support/return_support.json");
        try {
            return new ObjectMapper().readValue(inJson, OnSupportRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OnSupportRequest();
    }
}
