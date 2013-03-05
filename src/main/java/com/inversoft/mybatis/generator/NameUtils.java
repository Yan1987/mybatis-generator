/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator;

import org.apache.commons.lang3.StringUtils;

/**
 * Naming tools to convert from database style names to Java names and other usages.
 *
 * @author Brian Pontarelli
 */
public class NameUtils {
  /**
   * Converts a database style name to a Java style name. For example, if you have a table named foo_bar_baz, this will
   * return fooBarBaz.
   *
   * @param databaseName      The database style name.
   * @param capitalFirstPiece If true, the first part of the database style name will be capitalized. This is useful for
   *                          converting table names to class names.
   * @param depluralize       If true, the string is converted from plural to non-plural.
   * @return The Java name.
   */
  public static String toJavaName(String databaseName, boolean capitalFirstPiece, boolean depluralize) {
    String[] parts = databaseName.split("_");
    StringBuilder build = new StringBuilder();
    for (int i = 0; i < parts.length; i++) {
      String part = parts[i];
      if (i == 0 && capitalFirstPiece || i > 0) {
        build.append(StringUtils.capitalize(part));
      } else if (i == 0) {
        build.append(part.toLowerCase());
      } else {
        build.append(part);
      }
    }

    String javaName = build.toString();
    if (depluralize) {
      if (javaName.endsWith("IES")) {
        javaName = javaName.substring(0, javaName.length() - 3) + "Y";
      } else if (javaName.toLowerCase().endsWith("ies")) {
        javaName = javaName.substring(0, javaName.length() - 3) + "y";
      } else if (javaName.endsWith("SES")) {
        javaName = javaName.substring(0, javaName.length() - 2);
      } else if (javaName.toLowerCase().endsWith("sses")) {
        javaName = javaName.substring(0, javaName.length() - 2);
      } else if (javaName.toLowerCase().endsWith("ches")) {
        javaName = javaName.substring(0, javaName.length() - 2);
      } else if (javaName.toLowerCase().endsWith("shes")) {
        javaName = javaName.substring(0, javaName.length() - 2);
      } else if (javaName.toLowerCase().endsWith("xes")) {
        javaName = javaName.substring(0, javaName.length() - 2);
      } else if (javaName.toLowerCase().endsWith("zes")) {
        javaName = javaName.substring(0, javaName.length() - 2);
      } else {
        javaName = javaName.substring(0, javaName.length() - 1);
      }
    }

    return javaName;
  }

  /**
   * Converts a database style name to an acronym (shortened for things like AS clauses). For example, if you have a
   * table named foo_bar_baz, this will return fbb.
   *
   * @param databaseName The database style name.
   * @return The acronym.
   */
  public static String toAcronym(String databaseName) {
    String[] parts = databaseName.split("_");
    StringBuilder build = new StringBuilder();
    for (String part : parts) {
      build.append(Character.toLowerCase(part.charAt(0)));
    }

    return build.toString();
  }
}
