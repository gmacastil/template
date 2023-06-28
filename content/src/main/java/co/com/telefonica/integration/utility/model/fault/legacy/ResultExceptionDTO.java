package co.com.telefonica.integration.utility.model.fault.legacy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonAutoDetect
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ResultExceptionDTO {

    @JsonProperty("systemCode")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("details")
    private ExceptionDetails details;

    @JsonAutoDetect
    @JsonSerialize
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    public static class ExceptionDetails {

        @JsonProperty("code")
        private String code;

        @JsonProperty("component")
        private String component;

        @JsonProperty("message")
        private String message;

        @JsonProperty("codeLegacy")
        private String codeLegacy;

        @JsonProperty("messageLegacy")
        private String messageLegacy;

        @JsonProperty("arguments")
        private List<Object> arguments;

        @JsonProperty("endpoint")
        private String endpoint;

    }

}

