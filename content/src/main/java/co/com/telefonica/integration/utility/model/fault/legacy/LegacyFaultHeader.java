package co.com.telefonica.integration.utility.model.fault.legacy;

import co.com.telefonica.integration.utility.model.header.HeaderOutType;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Header")
public class LegacyFaultHeader {

    @XmlElement(name = "HeaderOut", namespace = "http://telefonica.com/globalIntegration/header")
    private HeaderOutType header;


}