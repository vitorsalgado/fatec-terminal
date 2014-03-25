
package com.microsoft.schemas.sharepoint.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreateContentTypeResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "createContentTypeResult"
})
@XmlRootElement(name = "CreateContentTypeResponse")
public class CreateContentTypeResponse {

    @XmlElement(name = "CreateContentTypeResult")
    protected String createContentTypeResult;

    /**
     * Obtém o valor da propriedade createContentTypeResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateContentTypeResult() {
        return createContentTypeResult;
    }

    /**
     * Define o valor da propriedade createContentTypeResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateContentTypeResult(String value) {
        this.createContentTypeResult = value;
    }

}
