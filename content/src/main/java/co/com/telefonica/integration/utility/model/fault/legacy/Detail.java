package co.com.telefonica.integration.utility.model.fault.legacy;

import co.com.telefonica.integration.utility.model.fault.wsdl.OperationFaultType;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Detail {

    @XmlElement(name = "MessageFault", namespace = "http://telefonica.com/globalIntegration/fault")
    private OperationFaultType messageFault;
}
