package ru.lischita.les.senbox;

import javax.swing.*;
import java.awt.*;

public class OdometrPanel extends JPanel {
  private static final long serialVersionUID=1L;
  long hitCount=1;

  public void paint (Graphics myGraphics){
    myGraphics.setFont(new Font ("Monospaced",Font.BOLD,24));
    myGraphics.drawString("Вы посититель номер "+Long.toString(hitCount++),50,50);

  }


}
