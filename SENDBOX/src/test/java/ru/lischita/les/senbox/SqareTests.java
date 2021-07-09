package ru.lischita.les.senbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SqareTests
{
  @Test
    public void testArea()
  {
    Sqare s = new Sqare(5);
    Assert.assertEquals(s.aria(),25.0);
  }
}
