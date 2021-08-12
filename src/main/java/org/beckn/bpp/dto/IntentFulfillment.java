package org.beckn.bpp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntentFulfillment {
    private String id; //TODO Fulfillment.id
    private IntentFulfillmentStart start;
    private IntentFulfillmentStart end;
    private Tags tags;

}
