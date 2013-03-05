/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator;

import java.io.File;

import org.testng.annotations.Test;

import com.inversoft.mybatis.generator.domain.Table;
import com.inversoft.mybatis.generator.domain.Type;
import static org.testng.Assert.*;

/**
 * @author Brian Pontarelli
 */
@Test(groups = "integration")
public class DatabaseInspectorTest {
  @Test
  public void all() throws Exception {
    MyBatisGeneratorOptions options = new MyBatisGeneratorOptions("jdbc:mysql://localhost:3306/mybatis_generator_test",
      "dev", "dev", "users", "com.test", new File("src/main/java"), new File("src/main/resources"), new File("templates"));
    DatabaseInspector inspector = new DatabaseInspector();
    Table table = inspector.extract(options);

    assertEquals(table.name, "users");
    assertEquals(table.shortJavaClassName, "User");
    assertEquals(table.fullJavaClassName, "com.test.User");
    assertEquals(table.shortName, "u");
    assertEquals(table.columns.size(), 3);
    assertEquals(table.columns.get(0).name, "name");
    assertEquals(table.columns.get(0).javaFieldName, "name");
    assertEquals(table.columns.get(0).size, 255);
    assertEquals(table.columns.get(0).type, Type.STRING);
    assertFalse(table.columns.get(0).nullable);
    assertEquals(table.columns.get(1).name, "birth_date");
    assertEquals(table.columns.get(1).javaFieldName, "birthDate");
    assertEquals(table.columns.get(1).size, 10); // No clue why MySQL sets DATE fields to size 10, but whatever
    assertEquals(table.columns.get(1).type, Type.DATE);
    assertFalse(table.columns.get(1).nullable);
    assertEquals(table.columns.get(2).name, "nickname");
    assertEquals(table.columns.get(2).javaFieldName, "nickname");
    assertEquals(table.columns.get(2).size, 32);
    assertEquals(table.columns.get(2).type, Type.STRING);
    assertTrue(table.columns.get(2).nullable);

    assertEquals(table.primaryKeys.size(), 1);
    assertEquals(table.primaryKeys.get(0).name, "id");
    assertEquals(table.primaryKeys.get(0).javaFieldName, "id");
    assertEquals(table.primaryKeys.get(0).size, 10); // Looks like MySQL sets the size for INTEGER columns to 10
    assertEquals(table.primaryKeys.get(0).type, Type.INT);
    assertFalse(table.primaryKeys.get(0).nullable);

    assertEquals(table.associations.size(), 1);
    assertEquals(table.associations.get(0).name, "addresses");
    assertEquals(table.associations.get(0).shortName, "a");
    assertEquals(table.associations.get(0).javaFieldName, "addresses");
    assertEquals(table.associations.get(0).shortJavaClassName, "Address");
    assertEquals(table.associations.get(0).fullJavaClassName, "com.test.Address");
  }
}
