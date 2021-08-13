package org.beckn.bpp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Context {
    private String domain;
    private String country;
    private String city;
    private ActionEnum action;
    @JsonProperty("core_version")
    private String coreVersion;
    @JsonProperty("bap_id")
    private String bapId;
    @JsonProperty("bap_uri")
    private String bapUri;
    @JsonProperty("bpp_id")
    private String bppId;
    @JsonProperty("bpp_uri")
    private String bppUri;
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("message_id")
    private String messageId;
    @JsonProperty("timestamp")
    private String timestamp;
    private String key;
    private String ttl;

    public String getAction() {
        return action.getValue();
    }

    public enum ActionEnum {
        search("search"),
        select("select"),
        init("init"),
        confirm("confirm"),
        update("update"),
        status("status"),
        track("track"),
        cancel("cancel"),
        rating("rating"),
        support("support"),
        on_search("on_search"),
        on_select("on_select"),
        on_init("on_init"),
        on_confirm("on_confirm"),
        on_update("on_update"),
        on_status("on_status"),
        on_track("on_track"),
        on_cancel("on_cancel"),
        on_rating("on_rating"),
        on_support("on_support"),
        ack("ack");

        static Map<String, ActionEnum> VALUE_MAP = Arrays.stream(values())
                .collect(toMap(ActionEnum::getValue, Function.identity()));

        String value;

        ActionEnum(String value) {
            this.value = value;
        }

        public static ActionEnum lookup(String value) {
            return VALUE_MAP.getOrDefault(value, null);
        }

        public String getValue() {
            return value;
        }

    }
}
