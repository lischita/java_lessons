package ru.lischita.les.senbox;

public class First_pro {
  public static void main(String[] args)
  {
    String string_1="Hello";
    String string_2=" world ";
    Sqare r=new Sqare(10);
    PointOne point_1=new PointOne(5,5);
    PointOne point_2=new PointOne(11,15);
    PointThree point3_1=new PointThree (5,5);
    PointThree point3_2=new PointThree (11,15);
    MyNewGui   out = new MyNewGui ();

    print ("Результат работы функции","");
    print ("Точка1: с координатой X="+point_1.x," и  Y="+point_1.y);
    print ("Точка2: с координатой X="+point_2.x," и  Y="+point_2.y);
    print ("Расстояние между точкой 1 и 2 =", " "+ distance(point_1,point_2));

    print ("Результат работы метода","");
    print ("Точка1: с координатой X="+point3_1.x," и  Y="+point3_1.y);
    print ("Точка2: с координатой X="+point3_2.x," и  Y="+point3_2.y);
    print ("Расстояние между точкой 1 и 2 =", " "+ point3_1.distance(point3_2));


   // out.addPointinfo(point3_1);
    //out.addPointinfo(point3_2);
    //out.addPointinfo(point3_1.distance(point3_2));

    /*PointTwo pointtwo=new PointTwo(7,7,10,10);
    print ("Результат работы метода","");
    print ("Точка1: с координатой X="+pointtwo.x1," и  Y="+pointtwo.y1);
    print ("Точка2: с координатой X="+pointtwo.x2," и  Y="+pointtwo.y2);
    print ("Расстояние между точкой 1 и 2 =", " "+ pointtwo.distance());


    print (string_1,string_2);
    System.out.println ("Площадь квадрата со стороной " + r.l+" равна "+ r.aria());*/

  }
  public static void print(String s_1,String s_2)
  {
    System.out.println(s_1+s_2);
  }
  public static double distance (PointOne point_1,PointOne point_2)
  {
    return Math.sqrt((Math.pow((point_2.x-point_1.x),2)+Math.pow((point_2.y-point_1.y),2)));
  }

}