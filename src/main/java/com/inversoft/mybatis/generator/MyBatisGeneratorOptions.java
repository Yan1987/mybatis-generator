/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator;

import java.io.File;

import org.apache.commons.cli.CommandLine;

/**
 * @author Brian Pontarelli
 */
public class MyBatisGeneratorOptions {
  public final String jdbcURL;
  public final String username;
  public final String password;
  public final String table;
  public final String domainPackage;
  public final String mapperPackage;
  public final File javaDirectory;
  public final File xmlDirectory;
  public final File templateDirectory;
  public final boolean force;

  public MyBatisGeneratorOptions(String jdbcURL, String username, String password, String table, String domainPackage,
                                 String mapperPackage, File javaDirectory, File xmlDirectory, File templateDirectory,
                                 boolean force) {
    this.jdbcURL = jdbcURL;
    this.username = username;
    this.password = password;
    this.table = table;
    this.domainPackage = domainPackage;
    this.mapperPackage = mapperPackage;
    this.javaDirectory = javaDirectory;
    this.xmlDirectory = xmlDirectory;
    this.templateDirectory = templateDirectory;
    this.force = force;
  }

  public MyBatisGeneratorOptions(CommandLine commandLine) {
    this.jdbcURL = commandLine.getOptionValue("jdbc");
    this.username = commandLine.getOptionValue("user");
    this.password = commandLine.getOptionValue("pass");
    this.table = commandLine.getOptionValue("table");
    this.domainPackage = commandLine.getOptionValue("pkg");
    this.mapperPackage = commandLine.hasOption("mapperpkg") ? commandLine.getOptionValue("mapperpkg") : this.domainPackage;

    this.javaDirectory = new File(commandLine.hasOption("java") ? commandLine.getOptionValue("java") : "src/main/java");
    this.xmlDirectory = new File(commandLine.hasOption("xml") ? commandLine.getOptionValue("xml") : "src/main/resources");
    this.templateDirectory = new File(commandLine.hasOption("template") ? commandLine.getOptionValue("template") : System.getProperty("home") + "/templates");

    this.force = commandLine.hasOption("force");
  }
}
