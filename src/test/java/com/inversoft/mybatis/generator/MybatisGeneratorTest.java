/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator;

import java.io.File;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * @author Brian Pontarelli
 */
public class MybatisGeneratorTest {
  @Test
  public void all() throws Exception {
    MyBatisGeneratorOptions options = new MyBatisGeneratorOptions("jdbc:mysql://localhost:3306/mybatis_generator_test",
      "dev", "dev", "users", "com.test.domain", "com.test.mapper", "com.test.test", new File("build/test/src/main/java"),
      new File("build/test/src/test/java"), new File("build/test/src/main/resources"), new File("src/main/templates"), false);
    MyBatisGenerator generator = new MyBatisGenerator();
    generator.run(options);

    assertTrue(new File("build/test/src/main/java/com/test/domain/User.java").isFile());
    assertTrue(new File("build/test/src/main/java/com/test/mapper/UserMapper.java").isFile());
    assertTrue(new File("build/test/src/test/java/com/test/test/UserTest.java").isFile());
  }
}
