
package co.com.telefonica.integration.utility.model.fault.wsdl;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para appDetailType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="appDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exceptionAppCode" type="{http://telefonica.com/globalIntegration/fault}exceptionAppCodeType"/>
 *         &lt;element name="exceptionAppMessage" type="{http://telefonica.com/globalIntegration/fault}exceptionAppMessageType"/>
 *         &lt;element name="exceptionAppLocation" type="{http://telefonica.com/globalIntegration/fault}exceptionAppLocationType" minOccurs="0"/>
 *         &lt;element name="exceptionAppCause" type="{http://telefonica.com/globalIntegration/fault}exceptionAppCauseType" minOccurs="0"/>
 *         &lt;element name="varArg" type="{http://telefonica.com/globalIntegration/fault}varArgType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appDetailType", propOrder = {
    "exceptionAppCode",
    "exceptionAppMessage",
    "exceptionAppLocation",
    "exceptionAppCause",
    "varArg"
})
@Setter
@Getter
public class AppDetailType {

    @XmlElement(required = true)
    protected String exceptionAppCode;
    @XmlElement(required = true)
    protected String exceptionAppMessage;
    protected String exceptionAppLocation;
    protected String exceptionAppCause;
    protected VarArgType varArg;

    /**
     * Obtiene el valor de la propiedad exceptionAppCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionAppCode() {
        return exceptionAppCode;
    }

    /**
     * Define el valor de la propiedad exceptionAppCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionAppCode(String value) {
        this.exceptionAppCode = value;
    }

    /**
     * Obtiene el valor de la propiedad exceptionAppMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionAppMessage() {
        return exceptionAppMessage;
    }

    /**
     * Define el valor de la propiedad exceptionAppMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionAppMessage(String value) {
        this.exceptionAppMessage = value;
    }

    /**
     * Obtiene el valor de la propiedad exceptionAppLocation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionAppLocation() {
        return exceptionAppLocation;
    }

    /**
     * Define el valor de la propiedad exceptionAppLocation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionAppLocation(String value) {
        this.exceptionAppLocation = value;
    }

    /**
     * Obtiene el valor de la propiedad exceptionAppCause.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionAppCause() {
        return exceptionAppCause;
    }

    /**
     * Define el valor de la propiedad exceptionAppCause.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionAppCause(String value) {
        this.exceptionAppCause = value;
    }

    /**
     * Obtiene el valor de la propiedad varArg.
     * 
     * @return
     *     possible object is
     *     {@link VarArgType }
     *     
     */
    public VarArgType getVarArg() {
        return varArg;
    }

    /**
     * Define el valor de la propiedad varArg.
     * 
     * @param value
     *     allowed object is
     *     {@link VarArgType }
     *     
     */
    public void setVarArg(VarArgType value) {
        this.varArg = value;
    }

}
