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
package br.com.fatecpg.repositories.sharepoint;

import br.com.fatecpg.core.repositories.SpContext;
import br.com.fatecpg.core.common.BasicAuthenticator;
import com.microsoft.schemas.sharepoint.soap.GetListItems.Query;
import com.microsoft.schemas.sharepoint.soap.GetListItems.QueryOptions;
import com.microsoft.schemas.sharepoint.soap.GetListItems.ViewFields;
import com.microsoft.schemas.sharepoint.soap.GetListItemsResponse.GetListItemsResult;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author vitor.salgado
 */
@Service
public class SpContextImpl implements SpContext {

    private String username;
    private String password; 
    
    @Value("#{appProperties['sharepointUsername']}")
    public void setUsername(String username) {
        this.username = username;
    }

    @Value("#{appProperties['sharepointPassword']}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public NodeList executeQuery(String sitePath, String listName, Query query, ViewFields viewFields) {
        return executeQuery(sitePath, listName, query, viewFields, 0);
    }

    @Override
    public NodeList executeQuery(String sitePath, String listName, Query query, ViewFields viewFields, int rowLimit) {
        QueryOptions queryOptions = null;
        String webID = "";

        BasicAuthenticator auth = new BasicAuthenticator(this.username, this.password);
        auth.authenticate();
        
        ListsSoap listsSoapClient;
        listsSoapClient = (ListsSoap) SharepointConnector.getListsClient(this.username, this.password, sitePath);

        GetListItemsResult result
                = listsSoapClient.getListItems(listName, null, query, viewFields, String.valueOf(rowLimit), queryOptions, webID);

        Object listResult = result.getContent().get(0);

        if ((listResult != null) && (listResult instanceof ElementNSImpl)) {
            ElementNSImpl node = (ElementNSImpl) listResult;

            NodeList list = node.getElementsByTagName("z:row");

            return list;
        }

        return null;
    }

    @Override
    public ViewFields createViewFieldsNode(String... fields) {
        ViewFields viewFields = new ViewFields();

        if (fields.length <= 0) {
            viewFields.getContent().add(generateXmlNode("<ViewFields></ViewFields>"));
            return viewFields;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("<ViewFields>");

        for (String field : fields) {
            builder.append("<FieldRef Name='")
                    .append((field))
                    .append("'/>");
        }

        builder.append("</ViewFields>");
        viewFields.getContent().add(generateXmlNode(builder.toString()));

        return viewFields;
    }

    @Override
    public Query createQueryNode(String strQuery) {

        if (strQuery == null || strQuery.isEmpty()) {
            throw new IllegalArgumentException("strQuery can't be null or empty.");
        }

        Query query = new Query();
        query.getContent().add(generateXmlNode(strQuery));

        return query;
    }

    private Element generateXmlNode(String sXML) {

        Document documentOptions = null;
        DocumentBuilder builder;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            builder = factory.newDocumentBuilder();
            documentOptions = builder.parse(new InputSource(new StringReader(sXML)));
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            throw new RuntimeException(String.format("failed to create xml element node with: %s", sXML));
        }

        Element elementOptions = documentOptions.getDocumentElement();
        return elementOptions;
    }

}
