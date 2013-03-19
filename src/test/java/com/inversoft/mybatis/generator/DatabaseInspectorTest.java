/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator;

import java.io.File;

import org.testng.annotations.Test;

import com.inversoft.mybatis.generator.domain.Table;
import com.inversoft.mybatis.generator.domain.Type;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Brian Pontarelli
 */
@Test(groups = "integration")
public class DatabaseInspectorTest {
  @Test
  public void all() throws Exception {
    MyBatisGeneratorOptions options = new MyBatisGeneratorOptions("jdbc:mysql://localhost:3306/mybatis_generator_test",
      "dev", "dev", "users", "com.test", "com.test.mapper", "com.test.test", new File("src/main/java"),
      new File("src/test/java"), new File("src/main/resources"), new File("templates"), false);
    DatabaseInspector inspector = new DatabaseInspector();
    Table table = inspector.extract(options);

    assertEquals(table.name, "users");
    assertEquals(table.shortDomainClassName, "User");
    assertEquals(table.fullDomainClassName, "com.test.User");
    assertEquals(table.shortName, "u");
    assertEquals(table.columns.size(), 4);
    assertEquals(table.columns.get(0).name, "birth_date");
    assertEquals(table.columns.get(0).javaFieldName, "birthDate");
    assertEquals(table.columns.get(0).size, 10); // No clue why MySQL sets DATE fields to size 10, but whatever
    assertEquals(table.columns.get(0).type, Type.DATE);
    assertFalse(table.columns.get(0).nullable);
    assertEquals(table.columns.get(1).name, "male");
    assertEquals(table.columns.get(1).javaFieldName, "male");
    assertEquals(table.columns.get(1).size, 1);
    assertEquals(table.columns.get(1).type, Type.BOOLEAN);
    assertTrue(table.columns.get(1).nullable);
    assertEquals(table.columns.get(2).name, "name");
    assertEquals(table.columns.get(2).javaFieldName, "name");
    assertEquals(table.columns.get(2).size, 255);
    assertEquals(table.columns.get(2).type, Type.STRING);
    assertFalse(table.columns.get(2).nullable);
    assertEquals(table.columns.get(3).name, "nickname");
    assertEquals(table.columns.get(3).javaFieldName, "nickname");
    assertEquals(table.columns.get(3).size, 32);
    assertEquals(table.columns.get(3).type, Type.STRING);
    assertTrue(table.columns.get(3).nullable);

    assertEquals(table.primaryKeys.size(), 1);
    assertEquals(table.primaryKeys.get(0).name, "id");
    assertEquals(table.primaryKeys.get(0).javaFieldName, "id");
    assertEquals(table.primaryKeys.get(0).size, 10); // Looks like MySQL sets the size for INTEGER columns to 10
    assertEquals(table.primaryKeys.get(0).type, Type.INT);
    assertFalse(table.primaryKeys.get(0).nullable);

    assertEquals(table.associations.size(), 1);
    assertEquals(table.associations.get(0).name, "addresses");
    assertEquals(table.associations.get(0).shortName, "a");
    assertEquals(table.associations.get(0).pluralJavaFieldName, "addresses");
    assertEquals(table.associations.get(0).singularJavaFieldName, "address");
    assertEquals(table.associations.get(0).shortDomainClassName, "Address");
    assertEquals(table.associations.get(0).fullDomainClassName, "com.test.Address");
  }
}
