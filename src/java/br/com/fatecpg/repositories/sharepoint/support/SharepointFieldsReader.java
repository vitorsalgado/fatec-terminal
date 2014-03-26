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

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Encapsulated logic to read Sharepoint fields. 
 * Sharepoint sometimes returns unexpected field values, like string values for fields that were originally set up as numeric. 
 * @author Vitor Hugo Salgado <vsalgadopb@gmail.com>
 */
public final class SharepointFieldsReader {

    public static String readStringField(NamedNodeMap attributes, String fieldName) {
        if (attributes == null) {
            throw new IllegalArgumentException("attributes can't be null.");
        }

        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("fieldName can't be null or empty.");
        }

        Node node = attributes.getNamedItem(fieldName);

        if (node == null) {
            return "";
        }

        return node.getNodeValue();
    }

    public static int readIntegerField(NamedNodeMap attributes, String fieldName) {
        if (attributes == null) {
            throw new IllegalArgumentException("attributes can't be null.");
        }

        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("fieldName can't be null or empty.");
        }

        Node node = attributes.getNamedItem(fieldName);

        if (node == null) {
            return 0;
        }

        try {
            return Integer.parseInt(node.getNodeValue());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static double readDoubleField(NamedNodeMap attributes, String fieldName) {
        if (attributes == null) {
            throw new IllegalArgumentException("attributes can't be null.");
        }

        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("fieldName can't be null or empty.");
        }

        Node node = attributes.getNamedItem(fieldName);

        if (node == null) {
            return 0;
        }

        try {
            return Double.parseDouble(node.getNodeValue());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

}
