@XmlSchema(
    namespace = "http://schemas.xmlsoap.org/soap/envelope/",
    elementFormDefault = XmlNsForm.QUALIFIED,
    xmlns = {
		@XmlNs(prefix="v0", namespaceURI="http://bes.huawei.com/interface/billing/v0.1"),
		@XmlNs(prefix="v01", namespaceURI="http://bes.huawei.com/schema/base-type/v0.1")
    }
)  

package co.com.telefonica.integration.model.wsdl.legacy;
import javax.xml.bind.annotation.*;