/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator;

import java.io.File;

import org.testng.annotations.Test;

/**
 * @author Brian Pontarelli
 */
public class MybatisGeneratorTest {
  @Test
  public void all() throws Exception {
    File baseDir = new File("../../libraries/mybatis-generator");
    MyBatisGeneratorOptions options = new MyBatisGeneratorOptions("jdbc:mysql://localhost:3306/mybatis_generator_test",
      "dev", "dev", "users", "com.test", new File(baseDir, "build/test/src/main/java"), new File(baseDir, "build/test/src/main/resources"),
      new File(baseDir, "src/main/templates"));
    MyBatisGenerator generator = new MyBatisGenerator();
    generator.run(options);
  }
}
