package org.beckn.bpp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Context {
    private Domain domain;
    private String country; //TODO Country.code
    private String city; //TODO City.code
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


    public enum ActionEnum {
        SEARCH("search"),
        SELECT("select"),
        INIT("init"),
        CONFIRM("confirm"),
        UPDATE("update"),
        STATUS("status"),
        TRACK("track"),
        CANCEL("cancel"),
        FEEDBACK("feedback"),
        SUPPORT("support"),
        ON_SEARCH("on_search"),
        ON_SELECT("on_select"),
        ON_INIT("on_init"),
        ON_CONFIRM("on_confirm"),
        ON_UPDATE("on_update"),
        ON_STATUS("on_status"),
        ON_TRACK("on_track"),
        ON_CANCEL("on_cancel"),
        ON_FEEDBACK("on_feedback"),
        ON_SUPPORT("on_support"),
        ACK("ack");

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
