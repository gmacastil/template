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
@XmlRootElement(name = "reqHeader")
public class LegacyRequestReqHeader {

	@XmlElement(name = "transactionId", namespace = "http://bes.huawei.com/schema/base-type/v0.1")
	private String transactionId;
	
	@XmlElement(name = "accessUser", namespace = "http://bes.huawei.com/schema/base-type/v0.1")
	private String accessUser;
	
	@XmlElement(name = "accessPassword", namespace = "http://bes.huawei.com/schema/base-type/v0.1")
	private String accessPassword;
	
}