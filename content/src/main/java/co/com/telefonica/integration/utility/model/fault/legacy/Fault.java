package co.com.telefonica.integration.utility.model.fault.legacy;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Fault {

    @XmlElement(name = "faultCode")
    private String faultCode;

    @XmlElement(name = "faultString")
    private String faultString;

    @XmlElement(name = "detail")
    private Detail detail;
}
