package co.com.telefonica.integration.utility.model.exception;

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
public class ResultApiExceptionDTO {

    @JsonProperty("code")
    private String code;

    @JsonProperty("localizedMessage")
    private String localizedMessage;

    @JsonProperty("exceptionDetails")
    private List<ApiExceptionDetails> exceptionDetails;

    @JsonProperty("message")
    private String message;

    @JsonProperty("category")
    private String category;

    @JsonProperty("resolved")
    private boolean resolved;

    @JsonAutoDetect
    @JsonSerialize
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    public static class ApiExceptionDetails {

        @JsonProperty("code")
        private String code;

        @JsonProperty("component")
        private String component;

        @JsonProperty("message")
        private String message;

        @JsonProperty("arguments")
        private List<Object> arguments;

        @JsonProperty("endpoint")
        private String endpoint;

    }
}
