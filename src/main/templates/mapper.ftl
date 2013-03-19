[#ftl/]
[#-- @ftlvariable name="table" type="com.inversoft.mybatis.generator.domain.Table" --]
/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package ${table.mapperPackage};

import java.util.List;

import ${table.fullDomainClassName};

/**
 * Mapper for ${table.shortDomainClassName} instances.
 */
public interface ${table.shortMapperClassName} {
  /**
   * Retrieves all of the ${table.shortDomainClassName} instances from the database.
   *
   * @return The list of all the ${table.shortDomainClassName} instances in the database.
   */
  List<${table.shortDomainClassName}> retrieveAll();

  /**
   * Retrieves a single ${table.shortDomainClassName} instance from the database by its primary key.
   *
  [#list table.primaryKeys as primaryKey]
   * @param ${primaryKey.javaFieldName} The primary key.
  [/#list]
   * @return The ${table.shortDomainClassName} instance or null if it doesn't exist.
   */
  ${table.shortDomainClassName} retrieveById([#list table.primaryKeys as primaryKey]${primaryKey.type.nonNullableName} ${primaryKey.javaFieldName}[#if primaryKey_has_next], [/#if][/#list]);

  /**
   * Creates a new record in the database for the given ${table.shortDomainClassName}.
   *
   * @param ${table.singularJavaFieldName} The instance to create the record for.
   */
  void create(${table.shortDomainClassName} ${table.singularJavaFieldName});

  /**
   * Updates the record in the database for the given ${table.shortDomainClassName}.
   *
   * @param ${table.singularJavaFieldName} The instance to update.
   */
  void update(${table.shortDomainClassName} ${table.singularJavaFieldName});

  /**
   * Deletes the record from the database for the given ${table.shortDomainClassName}.
   *
   * @param ${table.singularJavaFieldName} The instance to delete.
   */
  void delete(${table.shortDomainClassName} ${table.singularJavaFieldName});
}