package ru.lischita.les.senbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Odometr extends JApplet implements ActionListener
{ private static final long serialVersionUID=1L;
  Timer timer;

  public void init(){
    OdometrPanel panel= new OdometrPanel();
    this.setBackground(Color.WHITE);
    setContentPane(panel);
 }

 public void start(){
    if (timer==null){
      timer=new Timer (100,this);
      timer.start();
    } else {timer.restart();}
 }

 public void stop(){
    if(timer!=null){
        timer.stop();
        timer=null;
    }
 }

 public void actionPerformed(ActionEvent e) {repaint();}

}
