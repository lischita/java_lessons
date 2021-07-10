package ru.lischita.les.senbox;

public class PointThree
{
    double x;
    double y;
    public PointThree(double x, double y)
    {
      this.x =x;
      this.y=y;
    }
  public double distance (PointThree point_three)
  {
    return Math.sqrt((Math.pow((point_three.x-this.x),2)+Math.pow((point_three.y-this.y),2)));
  }
}



