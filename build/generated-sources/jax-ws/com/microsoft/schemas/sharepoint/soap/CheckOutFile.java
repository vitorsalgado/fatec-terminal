
package com.microsoft.schemas.sharepoint.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="pageUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="checkoutToLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastmodified" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "pageUrl",
    "checkoutToLocal",
    "lastmodified"
})
@XmlRootElement(name = "CheckOutFile")
public class CheckOutFile {

    protected String pageUrl;
    protected String checkoutToLocal;
    protected String lastmodified;

    /**
     * Obtém o valor da propriedade pageUrl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * Define o valor da propriedade pageUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageUrl(String value) {
        this.pageUrl = value;
    }

    /**
     * Obtém o valor da propriedade checkoutToLocal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckoutToLocal() {
        return checkoutToLocal;
    }

    /**
     * Define o valor da propriedade checkoutToLocal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckoutToLocal(String value) {
        this.checkoutToLocal = value;
    }

    /**
     * Obtém o valor da propriedade lastmodified.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastmodified() {
        return lastmodified;
    }

    /**
     * Define o valor da propriedade lastmodified.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastmodified(String value) {
        this.lastmodified = value;
    }

}
