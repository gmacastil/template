
package co.com.telefonica.integration.utility.model.exception;

import co.com.telefonica.integration.utility.model.fault.legacy.ResultExceptionDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonAutoDetect
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageFaultDTO {

    @JsonProperty("result")
    private ResultExceptionDTO result;

}
