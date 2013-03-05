[#ftl/]
[#-- @ftlvariable name="table" type="com.inversoft.mybatis.generator.domain.Table" --]
/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package ${table.domainPackage};

import ${table.fullDomainClassName};

public interface ${table.shortMapperClassName} {
  /**
   * Queries all of the objects from the database.
   *
   * @return The list of all the objects in the database.
   */
  List<${table.shortDomainClassName}> queryAll();

  /**
   * Queries all of the objects from the database.
   *
   * @return The list of all the objects in the database.
   */
  List<${table.shortDomainClassName}> queryById([#list table.primaryKeys as primaryKey]${primaryKey.type.nonNullableName} ${primaryKey.javaFieldName}[#if primaryKey_has_next], [/#if][/#list]);

  /**
   * Creates a new record in the database for the given object.
   *
   * @param ${table.javaFieldName} The instance to create the record for.
   */
  void create(${table.shortDomainClassName} ${table.javaFieldName});

  /**
   * Updates the record in the database for the given object.
   *
   * @param ${table.javaFieldName} The instance to update.
   */
  void update(${table.shortDomainClassName} ${table.javaFieldName});

  /**
   * Deletes the record from the database for the given object.
   *
   * @param ${table.javaFieldName} The instance to delete.
   */
  void delete(${table.shortDomainClassName} ${table.javaFieldName});
}