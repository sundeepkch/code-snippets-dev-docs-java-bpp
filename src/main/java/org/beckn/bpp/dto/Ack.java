package org.beckn.bpp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ack {

    private AckNackStatusEnum status;

    public enum AckNackStatusEnum {
        ACK("ACK"),
        NACK("NACK");

        static Map<String, AckNackStatusEnum> VALUE_MAP = Arrays.stream(values())
                .collect(toMap(AckNackStatusEnum::getValue, Function.identity()));

        String value;

        AckNackStatusEnum(String value) {
            this.value = value;
        }

        public static AckNackStatusEnum lookup(String value) {
            return VALUE_MAP.getOrDefault(value, null);
        }

        public String getValue() {
            return value;
        }

    }
}


