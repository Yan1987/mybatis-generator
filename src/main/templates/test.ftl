[#ftl/]
[#-- @ftlvariable name="table" type="com.inversoft.mybatis.generator.domain.Table" --]
/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package ${table.testPackage};

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Test for the ${table.shortDomainClassName}.
 */
@Test(groups = "integration"}
public class ${table.shortTestClassName} extends BaseIntegrationTest {
  public ${table.shortMapperClassName} mapper;

  @BeforeClass
  public void setupMapper() {
    ${table.shortMapperClassName} = sqlSession.getMapper(${table.shortMapperClassName}.class);
  }

  @Test
  public void create() {
    clearDatabase();

    ${table.shortDomainClassName} ${table.singularJavaFieldName} = make${table.shortDomainClassName}([@makeCallParams/]);
    mapper.create(${table.singularJavaFieldName});

    [#list table.primaryKeys as primaryKey]
    assertNotNull(${table.singularJavaFieldName}.${primaryKey.javaFieldName});
    assertNotEquals(${table.singularJavaFieldName}.${primaryKey.javaFieldName}, 0);
    [/#list]

    sqlSession.commit();
  }

  @Test
  public void retrieveById() throws Exception {
    clearDatabase();

    [#list table.columns as column]
      [#if column.type == 'DATE']
    LocalDate today = new LocalDate();
      [#elseif column.type == 'TIME']
    LocalTime time = new LocalTime();
      [#elseif column.type == 'TIMESTAMP']
    DateTime now = new DateTime();
      [/#if]
    [/#list]

    ${table.shortDomainClassName} ${table.singularJavaFieldName} = make${table.shortDomainClassName}([@makeCallParams/]);
    mapper.create(${table.singularJavaFieldName});
    sqlSession.commit();

    ${table.singularJavaFieldName} = mapper.retrieveById(${table.singularJavaFieldName}.id);
    assertNotNull(${table.singularJavaFieldName}, "Should have found entity with id of [" + ${table.singularJavaFieldName}.id + "]");
    [#list table.primaryKeys as key]
    assertNotNull(${table.singularJavaFieldName}.${key.javaFieldName});
    [/#list]
    [#list table.columns as column]
      [#if column.type == 'STRING']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, "foo");
      [#elseif column.type == 'BINARY']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, "foo".getBytes("UTF-8");
      [#elseif column.type == 'BOOLEAN']
    assertTrue(${table.singularJavaFieldName}.${column.javaFieldName});
      [#elseif column.type == 'BYTE']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, (byte) 42);
      [#elseif column.type == 'SHORT']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, (short) 42);
      [#elseif column.type == 'INT' || column.type == 'LONG']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, 42);
      [#elseif column.type == 'FLOAT' || column.type == 'DOUBLE']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, 42.0, 0.0);
      [#elseif column.type == 'DATE']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, today);
      [#elseif column.type == 'TIME']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, time);
      [#elseif column.type == 'TIMESTAMP']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, now);
      [/#if]
    [/#list]
  }

  @Test
  public void update() throws Exception {
    clearDatabase();

    [#list table.columns as column]
      [#if column.type == 'DATE']
    LocalDate today = new LocalDate();
      [#elseif column.type == 'TIME']
    LocalTime time = new LocalTime();
      [#elseif column.type == 'TIMESTAMP']
    DateTime now = new DateTime();
      [/#if]
    [/#list]

    ${table.shortDomainClassName} ${table.singularJavaFieldName} = make${table.shortDomainClassName}([@makeCallParams/]);
    mapper.create(${table.singularJavaFieldName});
    sqlSession.commit();

    [#list table.columns as column]
      [#if column.type == 'STRING']
    ${table.singularJavaFieldName}.${column.javaFieldName} = "fooUpdate";
      [#elseif column.type == 'BINARY']
    ${table.singularJavaFieldName}.${column.javaFieldName} = "fooUpdate".getBytes("UTF-8");
      [#elseif column.type == 'BOOLEAN']
    ${table.singularJavaFieldName}.${column.javaFieldName} = false;
      [#elseif column.type == 'BYTE']
    ${table.singularJavaFieldName}.${column.javaFieldName} = (byte) 1;
      [#elseif column.type == 'SHORT']
    ${table.singularJavaFieldName}.${column.javaFieldName} = (short) 1;
      [#elseif column.type == 'INT' || column.type == 'LONG']
    ${table.singularJavaFieldName}.${column.javaFieldName} = 1;
      [#elseif column.type == 'FLOAT' || column.type == 'DOUBLE']
    ${table.singularJavaFieldName}.${column.javaFieldName} = 1.0;
      [#elseif column.type == 'DATE']
    ${table.singularJavaFieldName}.${column.javaFieldName} = today.plusDays(1);
      [#elseif column.type == 'TIME']
    ${table.singularJavaFieldName}.${column.javaFieldName} = time.plusHours(1);
      [#elseif column.type == 'TIMESTAMP']
    ${table.singularJavaFieldName}.${column.javaFieldName} = now.plusDays(1);
      [/#if]
    [/#list]

    dictionaryEntryMapper.update(dictionaryEntry);
    sqlSession.commit();

    ${table.singularJavaFieldName} = mapper.retrieveById(${table.singularJavaFieldName}.id);
    [#list table.columns as column]
      [#if column.type == 'STRING']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, "fooUpdate");
      [#elseif column.type == 'BINARY']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, "fooUpdate".getBytes("UTF-8");
      [#elseif column.type == 'BOOLEAN']
    assertFalse(${table.singularJavaFieldName}.${column.javaFieldName});
      [#elseif column.type == 'BYTE']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, (byte) 1);
      [#elseif column.type == 'SHORT']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, (short) 1);
      [#elseif column.type == 'INT' || column.type == 'LONG']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, 1);
      [#elseif column.type == 'FLOAT' || column.type == 'DOUBLE']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, 1.0, 0.0);
      [#elseif column.type == 'DATE']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, today.plusDays(1));
      [#elseif column.type == 'TIME']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, time.plusHours(1));
      [#elseif column.type == 'TIMESTAMP']
    assertEquals(${table.singularJavaFieldName}.${column.javaFieldName}, now.plusDays(1));
      [/#if]
    [/#list]
  }

  private ${table.shortDomainClassName} make${table.shortDomainClassName}([@makeParams/]) {
    ${table.shortDomainClassName} ${table.singularJavaFieldName} = new ${table.shortDomainClassName}();

    return ${table.singularJavaFieldName};
  }
}

[#macro makeParams]
  [#list table.columns as column]
    [#if column.type == 'STRING']
    String [#rt/]
    [#elseif column.type == 'BINARY']
    byte[] [#rt/]
    [#elseif column.type == 'BOOLEAN']
    boolean [#rt/]
    [#elseif column.type == 'BYTE']
    Byte [#rt/]
    [#elseif column.type == 'SHORT']
    Short [#rt/]
    [#elseif column.type == 'INT' || column.type == 'LONG']
    Integer [#rt/]
    [#elseif column.type == 'FLOAT' || column.type == 'DOUBLE']
    Float [#rt/]
    [#elseif column.type == 'DATE']
    LocalDate [#rt/]
    [#elseif column.type == 'TIME']
    LocalTime [#rt/]
    [#elseif column.type == 'TIMESTAMP']
    DateTime [#rt/]
    [/#if]
  ${column.javaFieldName}[#if column_has_next],[/#if][#rt/]
  [/#list]
[/#macro]

[#macro makeCallParams]
  [#list table.columns as column]
    [#if column.type == 'STRING']
"foo" [#rt/]
    [#elseif column.type == 'BINARY']
"foo".getBytes("UTF-8") [#rt/]
    [#elseif column.type == 'BOOLEAN']
true [#rt/]
    [#elseif column.type == 'BYTE']
(byte) 42 [#rt/]
    [#elseif column.type == 'SHORT']
(short) 42 [#rt/]
    [#elseif column.type == 'INT' || column.type == 'LONG']
42 [#rt/]
    [#elseif column.type == 'FLOAT' || column.type == 'DOUBLE']
42.0 [#rt/]
    [#elseif column.type == 'DATE']
today [#rt/]
    [#elseif column.type == 'TIME']
time [#rt/]
    [#elseif column.type == 'TIMESTAMP']
now [#rt/]
    [/#if]
  ${column.javaFieldName}[#if column_has_next],[/#if][#rt/]
  [/#list]
[/#macro]