/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator.domain;

import com.inversoft.mybatis.generator.NameUtils;

/**
 * @author Brian Pontarelli
 */
public class Column {
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
}
