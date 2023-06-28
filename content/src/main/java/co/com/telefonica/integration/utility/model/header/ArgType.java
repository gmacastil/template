
package co.com.telefonica.integration.utility.model.header;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Este elemento queda definido por un tipo complejo y variable en cuya finalidad es permitir cubrir las necesidades particulares de trasmision de informacion del contexto de aplicacion, no relacionada con conceptos de negocio, en las interacciones entre dos sistemas. La estructura propuesta esta formada por un grupo con pares clave-multivalor. No queda recomendado el uso de este elemento por parte de los sistemas salvo en escenarios con necesidades muy concretas.
 * 
 * <p>Clase Java para argType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="argType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://telefonica.com/globalIntegration/header}keyType"/>
 *         &lt;element name="values" type="{http://telefonica.com/globalIntegration/header}valuesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "argType", propOrder = {
    "key",
    "values"
})
@Setter
@Getter
public class ArgType {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected ValuesType values;

    /**
     * Obtiene el valor de la propiedad key.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Define el valor de la propiedad key.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Obtiene el valor de la propiedad values.
     * 
     * @return
     *     possible object is
     *     {@link ValuesType }
     *     
     */
    public ValuesType getValues() {
        return values;
    }

    /**
     * Define el valor de la propiedad values.
     * 
     * @param value
     *     allowed object is
     *     {@link ValuesType }
     *     
     */
    public void setValues(ValuesType value) {
        this.values = value;
    }

}
