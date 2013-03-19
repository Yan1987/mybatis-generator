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
public class Table implements Comparable<Table> {
  public String name;
  public String shortName;
  public String domainPackage;
  public String mapperPackage;
  public String testPackage;
  public String singularJavaFieldName;
  public String pluralJavaFieldName;
  public String shortDomainClassName;
  public String fullDomainClassName;
  public String shortMapperClassName;
  public String fullMapperClassName;
  public String shortTestClassName;
  public String fullTestClassName;
  public final List<Column> columns = new ArrayList<Column>();
  public final List<Column> primaryKeys = new ArrayList<Column>();
  public final List<Column> foreignKeys = new ArrayList<Column>();
  public final List<Table> associations = new ArrayList<Table>();

  public Table(String name, String domainPackaage, String mapperPackage, String testPackage) {
    this.name = name;
    this.domainPackage = domainPackaage;
    this.mapperPackage = mapperPackage;
    this.testPackage = testPackage;
    this.shortName = NameUtils.toAcronym(name);
    this.singularJavaFieldName = NameUtils.toJavaName(name, false, true);
    this.pluralJavaFieldName = NameUtils.toJavaName(name, false, false);
    this.shortDomainClassName = NameUtils.toJavaName(name, true, true);
    this.fullDomainClassName = this.domainPackage + "." + this.shortDomainClassName;
    this.shortMapperClassName = this.shortDomainClassName + "Mapper";
    this.fullMapperClassName = this.mapperPackage + "." + this.shortMapperClassName;
    this.shortTestClassName = this.shortDomainClassName + "Test";
    this.fullTestClassName = this.testPackage + "." + this.shortTestClassName;
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

  @Override
  public int compareTo(Table o) {
    return name.compareTo(o.name);
  }
}
