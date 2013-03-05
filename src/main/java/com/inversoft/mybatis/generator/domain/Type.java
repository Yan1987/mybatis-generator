/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator.domain;

import java.sql.Types;

/**
 * @author Brian Pontarelli
 */
public enum Type {
  BOOLEAN("boolean", "Boolean"),

  BYTE("byte", "Byte"),
  SHORT("short", "Short"),
  INT("int", "Integer"),
  LONG("long", "Long"),

  FLOAT("float", "Float"),
  DOUBLE("double", "Double"),

  CHAR("char", "Character"),
  STRING("String", "String"),

  DATE("LocalDate", "LocalDate"),
  TIME("LocalTime", "LocalTime"),
  TIMESTAMP("DateTime", "DateTime"),

  BINARY("byte[]", "byte[]");

  public final String nullableName;
  public final String nonNullableName;

  private Type(String nonNullableName, String nullableName) {
    this.nullableName = nullableName;
    this.nonNullableName = nonNullableName;
  }

  public String getNullableName() {
    return nullableName;
  }

  public String getNonNullableName() {
    return nonNullableName;
  }

  public static Type fromJDBC(int jdbcType) {
    if (jdbcType == Types.BIT || jdbcType == Types.BOOLEAN) {
      return BOOLEAN;
    } else if (jdbcType == Types.TINYINT) {
      return BYTE;
    } else if (jdbcType == Types.SMALLINT) {
      return SHORT;
    } else if (jdbcType == Types.INTEGER) {
      return INT;
    } else if (jdbcType == Types.BIGINT) {
      return LONG;
    } else if (jdbcType == Types.FLOAT || jdbcType == Types.DECIMAL || jdbcType == Types.NUMERIC) {
      return FLOAT;
    } else if (jdbcType == Types.DOUBLE) {
      return DOUBLE;
    } else if (jdbcType == Types.CHAR || jdbcType == Types.NCHAR) {
      return CHAR;
    } else if (jdbcType == Types.VARCHAR || jdbcType == Types.NVARCHAR || jdbcType == Types.BLOB || jdbcType == Types.CLOB || jdbcType == Types.LONGNVARCHAR || jdbcType == Types.LONGVARCHAR) {
      return STRING;
    } else if (jdbcType == Types.BINARY || jdbcType == Types.VARBINARY || jdbcType == Types.LONGVARBINARY) {
      return BINARY;
    } else if (jdbcType == Types.DATE) {
      return DATE;
    } else if (jdbcType == Types.TIME) {
      return TIME;
    } else if (jdbcType == Types.TIMESTAMP) {
      return TIMESTAMP;
    }

    throw new IllegalArgumentException("Unmapped JDBC type [" + jdbcType + "]");
  }
}
