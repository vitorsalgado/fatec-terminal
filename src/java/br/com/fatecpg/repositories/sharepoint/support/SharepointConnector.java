/* 
 * Copyright (C) 2014 Vitor Hugo Salgado <vsalgadopb@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.fatecpg.repositories.sharepoint.support;

import com.microsoft.schemas.sharepoint.soap.Lists;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author vitor.salgado
 */
public final class SharepointConnector {

    public static ListsSoap getListsClient(String username, String password, String endPointAddress) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("username can't be null or empty.");
        }

        if (password == null || username.isEmpty()) {
            throw new IllegalArgumentException("password can't be null or empty.");
        }

        if (endPointAddress == null || username.isEmpty()) {
            throw new IllegalArgumentException("endPointAddress can't be null or empty.");
        }

        Lists service = new Lists();
        ListsSoap port = service.getListsSoap();

        ((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
        ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointAddress);

        return port;
    }
}
