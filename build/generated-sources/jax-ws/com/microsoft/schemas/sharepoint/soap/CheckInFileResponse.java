
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
 *         &lt;element name="CheckInFileResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "checkInFileResult"
})
@XmlRootElement(name = "CheckInFileResponse")
public class CheckInFileResponse {

    @XmlElement(name = "CheckInFileResult")
    protected boolean checkInFileResult;

    /**
     * Obtém o valor da propriedade checkInFileResult.
     * 
     */
    public boolean isCheckInFileResult() {
        return checkInFileResult;
    }

    /**
     * Define o valor da propriedade checkInFileResult.
     * 
     */
    public void setCheckInFileResult(boolean value) {
        this.checkInFileResult = value;
    }

}
