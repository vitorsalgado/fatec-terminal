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

import br.com.fatecpg.core.repositories.DbProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vitor Hugo Salgado <vsalgadopb@gmail.com>
 */
@Service
public class MySqlDbProvider implements DbProvider {

    private Connection mssqlConnection;
    private String mysqlUsername;
    private String mysqlPassword;
    private String mysqlUrl;

    @Value("#{appProperties['mysqlUsername']}")
    public void setMysqlUsername(String mysqlUsername) {
        this.mysqlUsername = mysqlUsername;
    }

    @Value("#{appProperties['mysqlPassword']}")
    public void setMysqlPassword(String mysqlPassword) {
        this.mysqlPassword = mysqlPassword;
    }

    @Value("#{appProperties['mysqlUrl']}")
    public void setMysqlUrl(String mysqlUrl) {
        this.mysqlUrl = mysqlUrl;
    }

    @Override
    public Connection getConnection() {
        return refreshConnection();
    }

    private Connection refreshConnection() {
        try {
            if (mssqlConnection == null || mssqlConnection.isValid(100)) {
                mssqlConnection = createConnection();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return mssqlConnection;
    }

    private Connection createConnection() {
        Connection connection = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            DriverManager.setLoginTimeout(1);
            connection = DriverManager.getConnection(this.mysqlUrl, this.mysqlUsername, this.mysqlPassword);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }

        return connection;
    }

}
