/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import freemarker.template.TemplateException;

/**
 * The main entry point. Usage:
 * <p/>
 * Main &gt;-j jdbc:url&lt; &gt;-u username&lt; &gt;-p password&lt; &gt;-t table&lt; &gt;--pkg package-name&lt; [--java
 * javaOutputDirectory] [--xml xmlOutputDirectory]
 *
 * @author Brian Pontarelli
 */
public class Main {
  public static void main(String[] args) throws ParseException {
    Options options = new Options();
    options.addOption(OptionBuilder.withLongOpt("jdbc").withDescription("Database JDBC URL").isRequired().hasArg().create("j"));
    options.addOption(OptionBuilder.withLongOpt("user").withDescription("Database username").isRequired().hasArg().create("u"));
    options.addOption(OptionBuilder.withLongOpt("pass").withDescription("Database password").isRequired().hasArg().create("p"));
    options.addOption(OptionBuilder.withLongOpt("table").withDescription("Database table").isRequired().hasArg().create("t"));
    options.addOption(OptionBuilder.withLongOpt("domainpkg").withDescription("Java package to place the generated domain class in").isRequired().hasArg().create("k"));
    options.addOption(OptionBuilder.withLongOpt("mapperpkg").withDescription("Java package to place the generated mapper class in").hasArg().create());
    options.addOption(OptionBuilder.withLongOpt("testpkg").withDescription("Java package to place the generated test class in").hasArg().create());
    options.addOption(OptionBuilder.withLongOpt("javamain").withDescription("Directory to put the Java domain class in (defaults to src/main/java)").hasArg().create());
    options.addOption(OptionBuilder.withLongOpt("javatest").withDescription("Directory to put the Java test class in (defaults to src/test/java)").hasArg().create());
    options.addOption(OptionBuilder.withLongOpt("xml").withDescription("Directory to put the XML in (defaults to src/main/resources)").hasArg().create("x"));
    options.addOption(OptionBuilder.withLongOpt("template").withDescription("Directory where the FreeMarker templates are stored (defaults to ../templates relative to the directory where the mybatis-generator script is stored)").hasArg().create());
    options.addOption(OptionBuilder.withLongOpt("force").withDescription("Overwrites existing Java and XML files. By default, existing files are not changed.").create("f"));

    CommandLineParser parser = new PosixParser();
    CommandLine commandLine;
    try {
      commandLine = parser.parse(options, args);

      MyBatisGenerator generator = new MyBatisGenerator();
      generator.run(new MyBatisGeneratorOptions(commandLine));
    } catch (ParseException e) {
      HelpFormatter helpFormatter = new HelpFormatter();
      helpFormatter.printHelp(120, "Usage: com.inversoft.mybatis.generator.Main <options>", e.getMessage(), options, "");
      System.exit(1);
    } catch (TemplateException e) {
      System.err.println("Failed to create MyBatis files because your FreeMarker templates have errors");
      e.printStackTrace();
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Failed to create MyBatis files because some random IOException was thrown");
      e.printStackTrace();
      System.exit(1);
    }
  }
}
