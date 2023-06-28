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
@XmlRootElement(name = "Envelope")
public class LegacyFaultSOAP {

	@XmlElement(name = "Header", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
	private LegacyFaultHeader header;

	@XmlElement(name = "Body", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
	private LegacyFaultBody body;

}