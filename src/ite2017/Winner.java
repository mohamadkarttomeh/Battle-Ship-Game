package ite2017;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Winner extends JFrame{
    private JLabel label;
    private JButton exit = new JButton();
    Winner(String win)
    {
        setForeground(new java.awt.Color(255, 0, 0));
        setWindow();
        label = new JLabel("The Winner is "+win);
        label.setBounds(50, 100, 500, 50);
        label.setFont(new java.awt.Font("Tahoma",3, 24));
        label.setForeground(new java.awt.Color(255, 0, 0));
        if(win =="Computer Player")
        {
           label.setText("Game Over");
        }
        
        exit = new JButton("Exit");
        exit.setBounds(200, 200, 200, 50);
        exit.setFont(new java.awt.Font("Tahoma",3, 24));
        exit.setForeground(new java.awt.Color(255, 0, 0));
        this.getContentPane().add(exit);
        this.getContentPane().add(label);
        
         exit.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent me) {
               Visible(false);
             }

            public void mousePressed(MouseEvent me) {
            }

            public void mouseReleased(MouseEvent me) {
            }

            public void mouseEntered(MouseEvent me) {
            }

            public void mouseExited(MouseEvent me) {
            }
        });
        this.setVisible(true);
    }
    public void Visible(boolean ok)
    {
        this.setVisible(ok);
    }
    public void setWindow()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setContentPane(new JLabel(new ImageIcon("IMG\\Win.gif")));
    }
}