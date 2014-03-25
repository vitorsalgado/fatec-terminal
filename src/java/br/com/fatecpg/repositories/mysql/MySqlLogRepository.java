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

import br.com.fatecpg.core.entities.Log;
import br.com.fatecpg.core.repositories.DbProvider;
import br.com.fatecpg.core.repositories.LogRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vitor Hugo Salgado <vsalgadopb@gmail.com>
 */
@Repository
public class MySqlLogRepository implements LogRepository {

    private DbProvider dbProvider;

    @Autowired
    public void setDbProvider(DbProvider dbProvider) {
        this.dbProvider = dbProvider;
    }

    @Override
    public void add(Log log) {
        if (log == null) {
            throw new IllegalArgumentException("log can't be null.");
        }

        String sql = "insert into log (message, url, ipaddress, username, createdon, details) values (?, ?, ?, ?, ?, ?)";
        Connection connection = dbProvider.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, log.getMessage());
            preparedStatement.setString(2, log.getUrl());
            preparedStatement.setString(3, log.getIpAddress());
            preparedStatement.setString(4, log.getUsername());

            Date sqlDate
                    = new Date(log.getCreatedDate().getYear(), log.getCreatedDate().getMonth(), log.getCreatedDate().getDay());

            preparedStatement.setDate(5, sqlDate);
            preparedStatement.setString(6, log.getDetails());
            
            preparedStatement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Log getById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be greather than 0.");
        }

        Log log = null;

        String sql = "select id, message, url, ipaddress, usernamme, createdon, details from log where id = ?";
        Connection connection = dbProvider.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                log = new Log();
                log.setId(resultSet.getInt("id"));
                log.setMessage(resultSet.getString("message"));
                log.setUrl(resultSet.getString("url"));
                log.setIpAddress("ipaddress");
                log.setUsername(resultSet.getString("username"));
                log.setCreatedDate(resultSet.getDate("createdOn"));
                log.setDetails(resultSet.getString("details"));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return log;
    }

}
