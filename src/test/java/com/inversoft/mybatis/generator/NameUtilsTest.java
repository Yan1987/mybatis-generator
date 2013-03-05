/*
 * Copyright (c) 2013, Inversoft Inc., All Rights Reserved
 */
package com.inversoft.mybatis.generator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests the NameUtils class.
 *
 * @author Brian Pontarelli
 */
public class NameUtilsTest {
  @Test
  public void toJavaName() {
    assertEquals(NameUtils.toJavaName("foo_bar_baz", false, false), "fooBarBaz");
    assertEquals(NameUtils.toJavaName("foo_Bar_Baz", false, false), "fooBarBaz");
    assertEquals(NameUtils.toJavaName("Foo_Bar_Baz", false, false), "fooBarBaz");
    assertEquals(NameUtils.toJavaName("FOO_Bar_Baz", false, false), "fooBarBaz");
    assertEquals(NameUtils.toJavaName("foo_BAR_Baz", false, false), "fooBARBaz");
    assertEquals(NameUtils.toJavaName("foo_bar_BAZ", false, false), "fooBarBAZ");

    assertEquals(NameUtils.toJavaName("foo_bar_baz", true, false), "FooBarBaz");
    assertEquals(NameUtils.toJavaName("foo_Bar_Baz", true, false), "FooBarBaz");
    assertEquals(NameUtils.toJavaName("Foo_Bar_Baz", true, false), "FooBarBaz");
    assertEquals(NameUtils.toJavaName("FOO_Bar_Baz", true, false), "FOOBarBaz");
    assertEquals(NameUtils.toJavaName("foo_BAR_Baz", true, false), "FooBARBaz");
    assertEquals(NameUtils.toJavaName("foo_bar_BAZ", true, false), "FooBarBAZ");

    assertEquals(NameUtils.toJavaName("foo_bar_bazs", true, true), "FooBarBaz");
    assertEquals(NameUtils.toJavaName("foo_Bar_Bazs", true, true), "FooBarBaz");
    assertEquals(NameUtils.toJavaName("Foo_Bar_Bazs", true, true), "FooBarBaz");
    assertEquals(NameUtils.toJavaName("FOO_Bar_Bazs", true, true), "FOOBarBaz");
    assertEquals(NameUtils.toJavaName("foo_BAR_Bazs", true, true), "FooBARBaz");
    assertEquals(NameUtils.toJavaName("foo_bar_BAZS", true, true), "FooBarBAZ");

    assertEquals(NameUtils.toJavaName("foo_bar_bazies", true, true), "FooBarBazy");
    assertEquals(NameUtils.toJavaName("foo_Bar_Bazies", true, true), "FooBarBazy");
    assertEquals(NameUtils.toJavaName("Foo_Bar_Bazies", true, true), "FooBarBazy");
    assertEquals(NameUtils.toJavaName("FOO_Bar_Bazies", true, true), "FOOBarBazy");
    assertEquals(NameUtils.toJavaName("foo_BAR_Bazies", true, true), "FooBARBazy");
    assertEquals(NameUtils.toJavaName("foo_bar_BAZIES", true, true), "FooBarBAZY");

    assertEquals(NameUtils.toJavaName("foo_bar_bazes", true, true), "FooBarBaz");
    assertEquals(NameUtils.toJavaName("foo_Bar_Bazes", true, true), "FooBarBaz");
    assertEquals(NameUtils.toJavaName("Foo_Bar_Bazes", true, true), "FooBarBaz");
    assertEquals(NameUtils.toJavaName("FOO_Bar_Bazes", true, true), "FOOBarBaz");
    assertEquals(NameUtils.toJavaName("foo_BAR_Bazes", true, true), "FooBARBaz");
    assertEquals(NameUtils.toJavaName("foo_bar_BAZES", true, true), "FooBarBAZ");

    assertEquals(NameUtils.toJavaName("foo_bar_batches", true, true), "FooBarBatch");
    assertEquals(NameUtils.toJavaName("foo_Bar_Batches", true, true), "FooBarBatch");
    assertEquals(NameUtils.toJavaName("Foo_Bar_Batches", true, true), "FooBarBatch");
    assertEquals(NameUtils.toJavaName("FOO_Bar_Batches", true, true), "FOOBarBatch");
    assertEquals(NameUtils.toJavaName("foo_BAR_Batches", true, true), "FooBARBatch");
    assertEquals(NameUtils.toJavaName("foo_bar_BATCHES", true, true), "FooBarBATCH");

    assertEquals(NameUtils.toJavaName("foo_bar_basses", true, true), "FooBarBass");
    assertEquals(NameUtils.toJavaName("foo_Bar_Basses", true, true), "FooBarBass");
    assertEquals(NameUtils.toJavaName("Foo_Bar_Basses", true, true), "FooBarBass");
    assertEquals(NameUtils.toJavaName("FOO_Bar_Basses", true, true), "FOOBarBass");
    assertEquals(NameUtils.toJavaName("foo_BAR_Basses", true, true), "FooBARBass");
    assertEquals(NameUtils.toJavaName("foo_bar_BASSES", true, true), "FooBarBASS");
  }

  @Test
  public void toAcronym() {
    assertEquals(NameUtils.toAcronym("foo_bar_baz"), "fbb");
    assertEquals(NameUtils.toAcronym("foo_Bar_Baz"), "fbb");
    assertEquals(NameUtils.toAcronym("Foo_Bar_Baz"), "fbb");
    assertEquals(NameUtils.toAcronym("FOO_Bar_Baz"), "fbb");
    assertEquals(NameUtils.toAcronym("foo_BAR_Baz"), "fbb");
    assertEquals(NameUtils.toAcronym("foo_bar_BAZ"), "fbb");
  }
}
