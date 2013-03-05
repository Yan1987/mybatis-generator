/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.inversoft.mybatis.generator.domain.Column;
import com.inversoft.mybatis.generator.domain.Table;
import com.inversoft.mybatis.generator.domain.Type;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Inspects the database and extracts the information about a specific table.
 *
 * @author Brian Pontarelli
 */
public class DatabaseInspector {
  /**
   * Runs the extraction.
   *
   * @param options The options.
   * @return The table information.
   */
  public Table extract(MyBatisGeneratorOptions options) {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setURL(options.jdbcURL);
    dataSource.setUser(options.username);
    dataSource.setPassword(options.password);

    ResultSet resultSet = null;
    Connection connection = null;
    try {
      connection = dataSource.getConnection();
      DatabaseMetaData metaData = connection.getMetaData();

      // Load the primary key
      Set<String> primaryKeys = new HashSet<String>();
      resultSet = metaData.getPrimaryKeys(null, null, options.table);
      while (resultSet.next()) {
        primaryKeys.add(resultSet.getString("COLUMN_NAME"));
      }
      resultSet.close();

      // Load the foreign keys in this table
      Set<String> foreignKeys = new HashSet<String>();
      resultSet = metaData.getImportedKeys(null, null, options.table);
      while (resultSet.next()) {
        String name = resultSet.getString("FKCOLUMN_NAME");
        foreignKeys.add(name);
      }
      resultSet.close();

      // Load the columns for the given table
      Table table = new Table(options.table, options.javaPackage);
      resultSet = metaData.getColumns(null, null, options.table, "%");
      while (resultSet.next()) {
        String name = resultSet.getString("COLUMN_NAME");
        Type type = Type.fromJDBC(resultSet.getInt("DATA_TYPE"));
        int size = resultSet.getInt("COLUMN_SIZE");
        boolean nullable = resultSet.getString("IS_NULLABLE").equals("YES");
        Column column = new Column(name, nullable, type, size);

        boolean primaryKey = primaryKeys.contains(name);
        boolean foreignKey = foreignKeys.contains(name);
        if (foreignKey) {
          table.foreignKeys.add(column);
        } else if (primaryKey) {
          table.primaryKeys.add(column);
        } else {
          table.columns.add(column);
        }
      }
      resultSet.close();

      // Load the associations (tables that have a foreign key to this one)
      resultSet = metaData.getExportedKeys(null, null, options.table);
      while (resultSet.next()) {
        String name = resultSet.getString("FKTABLE_NAME");
        table.associations.add(new Table(name, options.javaPackage));
      }
      resultSet.close();

      return table;
    } catch (SQLException e) {
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }

      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }

      e.printStackTrace();
      System.exit(1);
      throw new RuntimeException();
    }
  }
}
