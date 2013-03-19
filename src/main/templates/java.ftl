[#ftl/]
[#-- @ftlvariable name="table" type="com.inversoft.mybatis.generator.domain.Table" --]
/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package ${table.domainPackage};

[#if table.hasNonNullColumn()]
import javax.validation.constraints.NotNull;
[/#if]
[#if table.hasStringColumn()]
import javax.validation.constraints.Size;
[/#if]
import java.util.ArrayList;
import java.util.List;

[#if table.hasStringColumn()]
import org.hibernate.validator.constraints.NotBlank;
[/#if]

import com.inversoft.cleanspeak.domain.Create;
import com.inversoft.cleanspeak.domain.Update;
import com.inversoft.cleanspeak.domain.Identifiable;

/**
 * Domain object for the ${table.name} table.
 */
public class ${table.shortDomainClassName} extends Identifiable {
  private static final long serialVersionUID = 1;

[#list table.columns as column]
  [#if column.type.nullableName == "String"]
    [#if !column.nullable]
  @NotBlank(groups = {Create.class, Update.class})
    [/#if]
  @Size(max = ${column.size?c}, groups = {Create.class, Update.class})
  [#elseif !column.nullable && (column.type == "DATE" || column.type == "TIME" || column.type == "TIMESTAMP")]
  @NotNull(groups = {Create.class, Update.class})
  [/#if]
  [#if column.nullable]
  public ${column.type.nullableName} ${column.javaFieldName};
  [#else]
  public ${column.type.nonNullableName} ${column.javaFieldName};
  [/#if]
[/#list]

[#list table.associations as association]
  public List<${association.shortDomainClassName}> ${association.pluralJavaFieldName} = new ArrayList<${association.shortDomainClassName}>();
[/#list]
}