/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator.domain;

import java.util.ArrayList;
import java.util.List;

import com.inversoft.mybatis.generator.NameUtils;

/**
 * @author Brian Pontarelli
 */
public class Table {
  public String name;
  public String shortName;
  public String javaPackage;
  public String javaFieldName;
  public String shortJavaClassName;
  public String fullJavaClassName;
  public final List<Column> columns = new ArrayList<Column>();
  public final List<Column> primaryKeys = new ArrayList<Column>();
  public final List<Column> foreignKeys = new ArrayList<Column>();
  public final List<Table> associations = new ArrayList<Table>();

  public Table(String name, String javaPackaage) {
    this.name = name;
    this.javaPackage = javaPackaage;
    this.shortName = NameUtils.toAcronym(name);
    this.javaFieldName = NameUtils.toJavaName(name, false, false);
    this.shortJavaClassName = NameUtils.toJavaName(name, true, true);
    this.fullJavaClassName = this.javaPackage + "." + this.shortJavaClassName;
  }

  public boolean hasNonNullColumn() {
    boolean nonNullable = false;
    for (Column column : columns) {
      nonNullable |= !column.nullable;
    }

    return nonNullable;
  }

  public boolean hasStringColumn() {
    boolean string = false;
    for (Column column : columns) {
      string |= column.type == Type.STRING;
    }

    return string;
  }
}
