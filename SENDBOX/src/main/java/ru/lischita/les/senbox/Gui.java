package ru.lischita.les.senbox;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;

public class Gui extends JFrame {
  private JButton btnPushMe = new JButton("Push me!");
  private static JLabel l   = new JLabel();

  public Gui() {
    super("This is my title");
    this.setSize(600, 400);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    /*JFrame f = new JFrame("JFrameWindowListener");
    f.getContentPane().add(l);
    f.setPreferredSize(new Dimension(250, 80));
    f.pack();
    f.setVisible(true);
    l.setText("gggggg");*/

    JPanel contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(new FlowLayout());
    contentPane.add(btnPushMe);
    contentPane.add(l);
    btnPushMe.addActionListener(this::btnPushMe);
    l.setText("\n");
    l.setText("nnnn");
    this.setVisible(true);
  }

  private void btnPushMe(ActionEvent event) {
    System.out.println("Button clicked!");
  }

  public static void main(String[] args) {
    new Gui();
  }
}
