/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator.domain;

import com.inversoft.mybatis.generator.NameUtils;

/**
 * Models a column in a table in the database.
 *
 * @author Brian Pontarelli
 */
public class Column implements Comparable<Column> {
  public String name;
  public boolean nullable;
  public Type type;
  public int size;
  public String javaFieldName;

  public Column(String name, boolean nullable, Type type, int size) {
    this.name = name;
    this.nullable = nullable;
    this.type = type;
    this.size = size;
    this.javaFieldName = NameUtils.toJavaName(name, false, false);
  }

  @Override
  public int compareTo(Column o) {
    return name.compareTo(o.name);
  }
}
