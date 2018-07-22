package ite2017;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JFrame{
    boolean ok = true , ok2 = false;
    String Name;
    JButton Log_In = new JButton("Login");
    JLabel TextPlayerName = new JLabel("Enter Your Name");
    JTextField PlayerName = new JTextField("");
    Login(String s)
    {
        this.setTitle(s);
        setWindow();
        Log_In.setFont(new java.awt.Font("Tahoma",3, 18));
        PlayerName.setFont(new java.awt.Font("Tahoma",3, 18));
        TextPlayerName.setFont(new java.awt.Font("Tahoma",3, 18));
        Log_In.setBounds(50,140, 200, 50);
        TextPlayerName.setBounds(50 ,20, 200, 50);
        PlayerName.setBounds(50,70, 200, 50);
        
        check(Log_In);
        this.getContentPane().add(Log_In);
        this.getContentPane().add(TextPlayerName);
        this.getContentPane().add(PlayerName);
        this.setVisible(true);
        
        while(ok)
        {
            System.out.print("");
            if(ok2)
            {
                PlayerName.setBackground(Color.red);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                ok2 = false;
                PlayerName.setBackground(Color.white);
            }
        }
        this.setVisible(false);
    }
    
    public void check(JButton button) {
        button.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                System.out.println(PlayerName.getText()+ " "+ok);
               if(PlayerName.getText().length() > 0)
               {
                   Name = PlayerName.getText();
                   ok = false;
               }
               else 
               {
                   ok2 = true;
               }
            }
            
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.GRAY);
                return;
            }

            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.BLACK);
                return;
            }

            public void mousePressed(MouseEvent e) {
                return;
            }

            public void mouseReleased(MouseEvent e) {
                return;
            }
        });
    }

    public String getPlayerName()
    {
        return Name;
    }
    public void setWindow()
    {
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
      }
    
}
