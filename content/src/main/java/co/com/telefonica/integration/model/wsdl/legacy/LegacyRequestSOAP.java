package co.com.telefonica.integration.model.wsdl.legacy;

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
public class LegacyRequestSOAP {

	@XmlElement(name = "Body")
	private LegacyRequestBody body;

}