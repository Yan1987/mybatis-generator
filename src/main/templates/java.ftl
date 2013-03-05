[#ftl/]
[#-- @ftlvariable name="table" type="com.inversoft.mybatis.generator.domain.Table" --]
/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package ${table.javaPackage};

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
[#if table.hasStringColumn()]
import javax.validation.Size;
  [#if table.hasNonNullColumn()]
import org.hibernate.validator.constraints.NotBlank;
  [/#if]
[#else]
import javax.validation.NotNull;
[/#if]

import com.inversoft.cleanspeak.domain.Create;
import com.inversoft.cleanspeak.domain.Edit;
import com.inversoft.cleanspeak.domain.Identifiable;

public class ${table.shortJavaClassName} extends Identifiable {
  private static final long serialVersionUID = 1;

[#list table.columns as column]
  [#if column.type.nullableName == "String"]
    [#if !column.nullable]
  @NotBlank(groups = {Create.class, Edit.class})
    [/#if]
  @Size(max = ${column.size?c}, groups = {Create.class, Edit.class})
  [#elseif !column.nullable && (column.type == "DATE" || column.type == "TIME" || column.type == "TIMESTAMP")]
  @NotNull(groups = {Create.class, Edit.class})
  [/#if]
  [#if column.nullable]
  public ${column.type.nullableName} ${column.javaFieldName};
  [#else]
  public ${column.type.nonNullableName} ${column.javaFieldName};
  [/#if]
[/#list]

[#list table.associations as association]
  public List<${association.shortJavaClassName}> ${association.javaFieldName} = new ArrayList<${association.shortJavaClassName}>();
[/#list]
}