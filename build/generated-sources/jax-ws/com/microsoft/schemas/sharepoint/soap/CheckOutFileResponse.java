
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
 *         &lt;element name="CheckOutFileResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "checkOutFileResult"
})
@XmlRootElement(name = "CheckOutFileResponse")
public class CheckOutFileResponse {

    @XmlElement(name = "CheckOutFileResult")
    protected boolean checkOutFileResult;

    /**
     * Obtém o valor da propriedade checkOutFileResult.
     * 
     */
    public boolean isCheckOutFileResult() {
        return checkOutFileResult;
    }

    /**
     * Define o valor da propriedade checkOutFileResult.
     * 
     */
    public void setCheckOutFileResult(boolean value) {
        this.checkOutFileResult = value;
    }

}
