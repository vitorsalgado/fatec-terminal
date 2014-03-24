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
package br.com.fatecpg.repositories.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vitor Hugo Salgado <vsalgadopb@gmail.com>
 */
public class MySqlDb {

    private static Connection mssqlConnection;

    public static Connection getConnection() {
        return refreshConnection();
    }

    private static Connection refreshConnection() {
        try {
            if (mssqlConnection == null || mssqlConnection.isValid(100)) {
                mssqlConnection = createConnection();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return mssqlConnection;
    }

    private static Connection createConnection() {
        Connection connection = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            
            String uid = "root";
            String password = "mysql123";
            
            String url = String.format("jdbc:mysql://localhost:3306/fatec-log");
            
            DriverManager.setLoginTimeout(1);
            connection = DriverManager.getConnection(url, uid, password);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }

        return connection;
    }

}
