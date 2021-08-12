package org.beckn.bpp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FulfillmentStart {
    private Location location;
    private Time time;
    private Descriptor instructions;
    private Contact contact;
    private Person person;
}
