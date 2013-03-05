/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.inversoft.mybatis.generator.domain.Table;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Runs the generator and writes out the XML and Java files.
 *
 * @author Brian Pontarelli
 */
public class MyBatisGenerator {
  public void run(MyBatisGeneratorOptions options) throws IOException, TemplateException {
    System.out.printf("Running MyBatis generator for:\n  database [%s]\n  username [%s]\n  password [%s]\n  table [%s]\n  " +
      "package [%s]\n  javaDirectory [%s]\n  xmlDirectory [%s]\n  templateDirectory [%s]\n",
      options.jdbcURL,
      options.username,
      options.password,
      options.table,
      options.domainPackage,
      options.javaDirectory,
      options.xmlDirectory,
      options.templateDirectory);

    DatabaseInspector inspector = new DatabaseInspector();
    Table table = inspector.extract(options);

    // FreeMarker output
    DefaultObjectWrapper objectWrapper = new DefaultObjectWrapper();
    objectWrapper.setExposeFields(true);

    Configuration configuration = new Configuration();
    configuration.setObjectWrapper(objectWrapper);
    configuration.setDirectoryForTemplateLoading(options.templateDirectory);

    Map<String, Object> rootMap = new HashMap<String, Object>();
    rootMap.put("table", table);
    rootMap.put("options", options);

    // Write out the Java domain file
    File javaFile = new File(options.javaDirectory, table.fullDomainClassName.replace('.', '/') + ".java");
    if (!javaFile.getParentFile().isDirectory() && !javaFile.getParentFile().mkdirs()) {
      throw new IOException("Unable to create directory for the Java Domain file [" + javaFile.getAbsolutePath() + "]");
    }

    if (!javaFile.isFile() || options.force) {
      System.out.println("Writing out the Java file to [" + javaFile.getAbsolutePath() + "]");
      FileWriter writer = new FileWriter(javaFile);
      Template template = configuration.getTemplate("java.ftl");
      template.process(rootMap, writer);
      writer.close();
    }

    // Write out the Java mapper file
    File javaMapperFile = new File(options.javaDirectory, table.fullMapperClassName.replace('.', '/') + ".java");
    if (!javaMapperFile.getParentFile().isDirectory() && !javaMapperFile.getParentFile().mkdirs()) {
      throw new IOException("Unable to create directory for the Java Mapper file [" + javaMapperFile.getAbsolutePath() + "]");
    }

    if (!javaMapperFile.isFile() || options.force) {
      System.out.println("Writing out the Java Mapper file to [" + javaMapperFile.getAbsolutePath() + "]");
      FileWriter writer = new FileWriter(javaMapperFile);
      Template template = configuration.getTemplate("mapper.ftl");
      template.process(rootMap, writer);
      writer.close();
    }

    // Write out the XML file
    File xmlFile = new File(options.xmlDirectory, table.fullDomainClassName.replace('.', '/') + "Mapper.xml");
    if (!xmlFile.getParentFile().isDirectory() && !xmlFile.getParentFile().mkdirs()) {
      throw new IOException("Unable to create directory for the XML file [" + xmlFile.getAbsolutePath() + "]");
    }

    if (!xmlFile.isFile() || options.force) {
      System.out.println("Writing out the XML file to [" + xmlFile.getAbsolutePath() + "]");
      FileWriter writer = new FileWriter(xmlFile);
      Template template = configuration.getTemplate("xml.ftl");
      template.process(rootMap, writer);
      writer.close();
    }
  }
}
