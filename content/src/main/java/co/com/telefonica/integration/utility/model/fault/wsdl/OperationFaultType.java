
package co.com.telefonica.integration.utility.model.fault.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para operationFaultType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="operationFaultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exceptionCategory" type="{http://telefonica.com/globalIntegration/fault}exceptionCategoryType"/>
 *         &lt;element name="exceptionCode" type="{http://telefonica.com/globalIntegration/fault}exceptionCodeType"/>
 *         &lt;element name="exceptionMessage" type="{http://telefonica.com/globalIntegration/fault}exceptionMessageType"/>
 *         &lt;element name="exceptionDetail" type="{http://telefonica.com/globalIntegration/fault}exceptionDetailType"/>
 *         &lt;element name="exceptionSeverity" type="{http://telefonica.com/globalIntegration/fault}exceptionSeverityType"/>
 *         &lt;element name="exceptionType" type="{http://telefonica.com/globalIntegration/fault}exceptionType" minOccurs="0"/>
 *         &lt;element name="exceptionProtocol" type="{http://telefonica.com/globalIntegration/fault}exceptionProtocolType" minOccurs="0"/>
 *         &lt;element name="appDetail" type="{http://telefonica.com/globalIntegration/fault}appDetailType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operationFaultType", propOrder = {
    "exceptionCategory",
    "exceptionCode",
    "exceptionMessage",
    "exceptionDetail",
    "exceptionSeverity",
    "exceptionType",
    "exceptionProtocol",
    "appDetail"
})
public class OperationFaultType {

    @XmlElement(required = true)
    protected String exceptionCategory;
    @XmlElement(required = true)
    protected String exceptionCode;
    @XmlElement(required = true)
    protected String exceptionMessage;
    @XmlElement(required = true)
    protected String exceptionDetail;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String exceptionSeverity;
    protected String exceptionType;
    protected ExceptionProtocolType exceptionProtocol;
    protected AppDetailType appDetail;

    /**
     * Obtiene el valor de la propiedad exceptionCategory.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionCategory() {
        return exceptionCategory;
    }

    /**
     * Define el valor de la propiedad exceptionCategory.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionCategory(String value) {
        this.exceptionCategory = value;
    }

    /**
     * Obtiene el valor de la propiedad exceptionCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionCode() {
        return exceptionCode;
    }

    /**
     * Define el valor de la propiedad exceptionCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionCode(String value) {
        this.exceptionCode = value;
    }

    /**
     * Obtiene el valor de la propiedad exceptionMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    /**
     * Define el valor de la propiedad exceptionMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionMessage(String value) {
        this.exceptionMessage = value;
    }

    /**
     * Obtiene el valor de la propiedad exceptionDetail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionDetail() {
        return exceptionDetail;
    }

    /**
     * Define el valor de la propiedad exceptionDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionDetail(String value) {
        this.exceptionDetail = value;
    }

    /**
     * Obtiene el valor de la propiedad exceptionSeverity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionSeverity() {
        return exceptionSeverity;
    }

    /**
     * Define el valor de la propiedad exceptionSeverity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionSeverity(String value) {
        this.exceptionSeverity = value;
    }

    /**
     * Obtiene el valor de la propiedad exceptionType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionType() {
        return exceptionType;
    }

    /**
     * Define el valor de la propiedad exceptionType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionType(String value) {
        this.exceptionType = value;
    }

    /**
     * Obtiene el valor de la propiedad exceptionProtocol.
     * 
     * @return
     *     possible object is
     *     {@link ExceptionProtocolType }
     *     
     */
    public ExceptionProtocolType getExceptionProtocol() {
        return exceptionProtocol;
    }

    /**
     * Define el valor de la propiedad exceptionProtocol.
     * 
     * @param value
     *     allowed object is
     *     {@link ExceptionProtocolType }
     *     
     */
    public void setExceptionProtocol(ExceptionProtocolType value) {
        this.exceptionProtocol = value;
    }

    /**
     * Obtiene el valor de la propiedad appDetail.
     * 
     * @return
     *     possible object is
     *     {@link AppDetailType }
     *     
     */
    public AppDetailType getAppDetail() {
        return appDetail;
    }

    /**
     * Define el valor de la propiedad appDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link AppDetailType }
     *     
     */
    public void setAppDetail(AppDetailType value) {
        this.appDetail = value;
    }

}
