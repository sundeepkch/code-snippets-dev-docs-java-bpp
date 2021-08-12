package org.beckn.bpp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Catalog {
    @JsonProperty("bpp/descriptor")
    private Descriptor bppDescriptor;

    @JsonProperty("bpp/categories")
    private List<Category> bppCategories = null;

    @JsonProperty("bpp/fulfillments")
    private List<Fulfillment> bppFulfillments = null;

    @JsonProperty("bpp/payments")
    private List<Payment> bppPayments = null;

    @JsonProperty("bpp/offers")
    private List<Offer> bppOffers = null;

    @JsonProperty("bpp/providers")
    private List<Provider> bppProviders = null;

    private OffsetDateTime exp = null;
}
