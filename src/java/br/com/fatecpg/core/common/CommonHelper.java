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
package br.com.fatecpg.core.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Vitor Hugo Salgado <vsalgadopb@gmail.com>
 */
public final class CommonHelper {

    public static String formatStackTraceToString(Exception ex) {

        if (ex == null) {
            throw new IllegalArgumentException("ex can't be null.");
        }

        String result = null;
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;

        try {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            ex.printStackTrace(printWriter);
            result = stringWriter.toString();
        } finally {
            try {
                if (stringWriter != null) {
                    stringWriter.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                //
            }
        }

        return result;
    }

}
