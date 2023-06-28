
package co.com.telefonica.integration.utility.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDetails {

    private String code;

    private String component;

    private String message;

    private String codeLegacy;

    private String messageLegacy;

    private List<Argument> argument;

    private String endpoint;

}
