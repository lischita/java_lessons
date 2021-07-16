package ru.lischita.les.senbox;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


//@SuppressWarnings("serial");

public class MyNewGui extends JFrame
{
  public MyNewGui ()
  {
    setTitle("Расстояние между точками");
    setSize(600,400);
    setLocationRelativeTo(null);
     setLayout(new GridLayout(5, 3, 100, 7));
    //setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
    setDefaultCloseOperation(EXIT_ON_CLOSE);



  }
  void addPointinfo(PointThree point3_1)
  {
    add(new JLabel ("  Точка: с координатой X="+point3_1.x+"  и Y="+point3_1.y));
    add(new JLabel ("  "));
    add(new JLabel ("  "));
    add(new JLabel ("  "));
       pack();
       setVisible(true);
   }

  void addPointinfo(double point3_1)
  {
    add(new JLabel ("  Расстояние между точками = "+point3_1+"   "));
    add(new JLabel ("  "));

    JButton btnSave = new JButton("Klick");
    //btnSave.setForeground(new Color(0, 135, 200).brighter());
    btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
    btnSave.setBorder(BorderFactory.createLineBorder(Color.black));
    btnSave.setPreferredSize(new Dimension(100,30));
    //btnSave.setBackground(new Color(3, 59, 90));
    add(btnSave);
    pack();
    setVisible(true);
   }
}
