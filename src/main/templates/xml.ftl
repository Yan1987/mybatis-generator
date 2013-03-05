[#ftl/]
[#-- @ftlvariable name="table" type="com.inversoft.mybatis.generator.domain.Table" --]
[#-- @ftlvariable name="options" type="com.inversoft.mybatis.generator.MyBatisGeneratorOptions" --]
[#macro primaryKeyWhereClause table useTableShortName]
    WHERE
  [#list table.primaryKeys as primaryKey]
      [#if useTableShortName]${table.shortName}[/#if]${primaryKey.name} = ${"#"}{${primaryKey.javaFieldName}}
  [/#list]
[/#macro]
<?xml version="1.0" encoding="UTF-8" ?>
<!--
  ~ Copyright (c) 2013, Inversoft Inc., All Rights Reserved
  -->
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${table.fullJavaClassName}">
  <resultMap id="${table.shortJavaClassName}" type="${table.fullJavaClassName}">
    [#if table.primaryKeys?size == 1]
    <id property="${table.primaryKeys[0].javaFieldName}" column="${table.shortName}_${table.primaryKeys[0].name}"/>
    [#else]
      [#list table.primaryKeys as primaryKey]
    <result property="${primaryKey.javaFieldName}" column="${table.shortName}_${primaryKey.name}"/>
      [/#list]
    [/#if]
    [#list table.columns as column]
    <result property="${column.javaFieldName}" column="${table.shortName}_${column.name}"/>
    [/#list]
  </resultMap>

  <sql id="select">
    SELECT
    [#list table.columns as column]
      ${table.shortName}.${column.name} AS ${table.shortName}_${column.name}
    [/#list]
    FROM ${table.name} AS ${table.shortName}
  </sql>

  <select id="findAll" resultMap="${table.shortJavaClassName}">
    <include refid="select"/>
  </select>

  <select id="findById" resultMap="${table.shortJavaClassName}">
    <include refid="select"/>
    [@primaryKeyWhereClause table true/]
  </select>

  <insert id="insert" parameterType="${table.fullJavaClassName}" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
    INSERT INTO ${table.name} (
    [#list table.columns as column]
      ${column.name}[#if column_has_next],[/#if]
    [/#list]
    ) VALUES (
    [#list table.columns as column]
      ${"#"}{${column.javaFieldName}}[#if column_has_next],[/#if]
    [/#list]
    )
  </insert>

  <update id="update" parameterType="com.inversoft.cleanspeak.domain.filter.OpenBlacklistEntry">
    UPDATE ${table.name}
    SET
    [#list table.columns as column]
      ${column.name} = ${"#"}{${column.javaFieldName}}[#if column_has_next],[/#if]
    [/#list]
    [#if table.primaryKeys?size == 0]
      -- Missing primary key columns, can't make this not update the whole table. Try making this use a unique constraint
    [#else]
      [@primaryKeyWhereClause table false/]
    [/#if]
  </update>

  <delete id="delete" parameterType="com.inversoft.cleanspeak.domain.filter.OpenBlacklistEntry">
    DELETE FROM ${table.name}
    [@primaryKeyWhereClause table false/]
  </delete>
</mapper>