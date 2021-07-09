package ru.lischita.les.senbox;

import org.testng.Assert;
import org.testng.annotations.Test;


@Test
public class PointTests
{
  public void testDistance()
  {
    PointTwo pointtwo=new PointTwo(5,5,11,15);
    Assert.assertEquals(pointtwo.distance(),11.661903789690601);
  }
  public void testDistance1()
  {
    PointTwo pointtwo=new PointTwo(7,7,10,10);
    Assert.assertEquals(pointtwo.distance(),4.242640687119285);
  }

}

