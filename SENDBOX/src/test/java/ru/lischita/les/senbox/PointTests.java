package ru.lischita.les.senbox;

import org.testng.Assert;
import org.testng.annotations.Test;


@Test
public class PointTests
{
  public void testDistance()
  {
    PointThree point3_1=new PointThree(5,5);
    PointThree point3_2=new PointThree(11,15);
    Assert.assertEquals(point3_1.distance(point3_2),11.661903789690601);
  }
  public void testDistance1()
  {
    PointThree point3_1=new PointThree(7,7);
    PointThree point3_2=new PointThree(10,10);
    Assert.assertEquals(point3_1.distance(point3_2),4.242640687119285);
      }

}

