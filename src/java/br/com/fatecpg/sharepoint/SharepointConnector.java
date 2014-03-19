/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecpg.sharepoint;

import com.microsoft.schemas.sharepoint.soap.Lists;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author vitor.salgado
 */
public class SharepointConnector {

    public static ListsSoap getListsClient(String username, String password, String endPointAddress) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("username can't be null or empty.");
        
        if (password == null || username.isEmpty())
            throw new IllegalArgumentException("passwor can't be null or empty.");
        
        if(endPointAddress == null || username.isEmpty())
            throw new IllegalArgumentException("endPointAddress can't be null or empty.");

        Lists service = new Lists();
        ListsSoap port = service.getListsSoap();

        ((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
        ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointAddress);

        return port;
    }
}
