package co.com.telefonica.integration.utility.model.fault.legacy;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Body")
public class LegacyFaultBody {

    @XmlElement(name = "Fault", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    private Fault fault;

}