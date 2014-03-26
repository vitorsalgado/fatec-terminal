/*
 * Copyright (C) 2014 vitor.salgado
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

import br.com.fatecpg.core.entities.Internship;
import br.com.fatecpg.core.repositories.InternshipRepository;
import br.com.fatecpg.core.repositories.SpContext;
import com.microsoft.schemas.sharepoint.soap.GetListItems.Query;
import com.microsoft.schemas.sharepoint.soap.GetListItems.ViewFields;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author vitor.salgado
 */
@Repository
public class SharepointInternshipRepository implements InternshipRepository {

    private String listsPath = "http://www.fatecpg.com.br/estagio/_vti_bin/lists.asmx";
    private SpContext spContext;

    @Autowired
    public void setSpContext(SpContext spContext) {
        this.spContext = spContext;
    }

    @Value("#{appProperties['rootSiteListsServicePath']}")
    public void setListsPath(String path) {
        this.listsPath = path;
    }

    @Override
    public List<Internship> getAllValid() {

        String listName = "oportunidades";
        ViewFields viewFields = spContext.createViewFieldsNode("ID", "Title", "Body", "Expires", "Author", "Created");
        String strQuery = "<Query><Where><Geq><FieldRef Name='Expires'/><Value Type='DateTime'><Today /></Value></Geq>"
                + "</Where><OrderBy><FieldRef Name='Created' Ascending='False'/></OrderBy><Query>";
        Query query = spContext.createQueryNode(strQuery);

        NodeList list = spContext.executeQuery(listsPath, listName, query, viewFields);
        List<Internship> internships = new ArrayList<>();

        if (list.getLength() <= 0) {
            return internships;
        }

        for (int i = 0; i < list.getLength(); i++) {
            NamedNodeMap attributes = list.item(i).getAttributes();
            Internship internship = new Internship();

            String createdBy = SharepointFieldsReader.readStringField(attributes, "ows_Author");
            String[] createdByArray = createdBy.split(";#");

            internship.setCreatedBy(createdByArray[1]);
            //internship.setCreatedOn();
            internship.setDetails(SharepointFieldsReader.readStringField(attributes, "ows_Body"));
            internship.setTitle(SharepointFieldsReader.readStringField(attributes, "ows_Title"));

            internships.add(internship);
        }

        return internships;
    }

}
