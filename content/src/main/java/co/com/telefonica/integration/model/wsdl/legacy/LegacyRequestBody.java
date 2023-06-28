package co.com.telefonica.integration.model.wsdl.legacy;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Body")
public class LegacyRequestBody {

    /*@XmlElement(name = "QueryCustomerReqMsg", namespace = "http://bes.huawei.com/interface/customer-management/v0.1")
    private QueryCustomerReqMsg queryCustomerReqMsg;*/

}