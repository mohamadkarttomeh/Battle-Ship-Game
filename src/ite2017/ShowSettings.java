package ite2017;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class ShowSettings extends JFrame {

    AppMainMenu father;
    JButton close;
    JCheckBox check;
    JCheckBox SecoundGUI ;
    int GUIChoese = 1;
    boolean Visible=false;
    JLabel label;
    public ShowSettings(AppMainMenu father) {
        this.father = father;
        setWindow();
        iniAll();
        setBoundsAll();
        setTextAll();
        setBackgroundAll();
        firstShow();
        SecoundGUI.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent me) {
                if(GUIChoese == 1)
                {
                    GUIChoese = 2;
                }
                else 
                {
                    GUIChoese = 1;
                }
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
        check.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent me) {
                Visible=!Visible;
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
        close.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Visible(false,true);
            }

            public void mouseEntered(MouseEvent e) {
                close.setForeground(Color.GRAY);
                return;
            }

            public void mouseExited(MouseEvent e) {
                close.setForeground(Color.black);
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
    public int get_GUIChoese()
    {
        return GUIChoese;
    }
    public void setBackgroundAll()
    {
        check.setBackground(Color.gray);
        SecoundGUI.setBackground(Color.gray);
    }
    public void setTextAll()
    {
        close.setText("close");
        close.setFont(new java.awt.Font("Tahoma",3, 14));
        label.setText("Show Setting : ");
        label.setFont(new java.awt.Font("Tahoma",3, 14));
        check.setFont(new java.awt.Font("Tahoma", 3, 12));
        SecoundGUI.setFont(new java.awt.Font("Tahoma", 3, 14));
    }
    public void setBoundsAll()
    {
        label.setBounds(20,10,200,50);
        close.setBounds(1000, 600, 200, 50);
        check.setBounds(20, 50, 200, 50);
        SecoundGUI.setBounds(20, 100, 200, 50);
    }
    public void iniAll()
    {
        SecoundGUI=new JCheckBox("Secound GUI");
        check=new JCheckBox("Show the Grid of the enemy");
        label = new JLabel();
        close = new JButton();
    }
    public void firstShow()
    {
        this.getContentPane().add(label);
        this.getContentPane().add(close);
        this.getContentPane().add(check);
        this.getContentPane().add(SecoundGUI);
    }
    public void setWindow()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setContentPane(new JLabel(new ImageIcon("IMG\\ShowSettings.jpg")));
    }
    public void Visible(boolean bo ,boolean bo2 ) {
        this.setVisible(bo);
        if(bo2)
         father.Visable(!bo);
    }
    public boolean getVisible(){

        return Visible;
    }
}
