package ite2017;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ShipSettings extends JFrame {

    JButton close, change;
    JLabel label ,Widthlabel = new JLabel("**Width**") ,Heightlabel = new JLabel("**Height**")  ;
    JSpinner NumShip;
    JSpinner Width[] = new JSpinner[100];
    JSpinner Height[] = new JSpinner[100];
    SpinnerModel sm;
    int num = 5;
    List<Ship> ships = new ArrayList<Ship>();
    JLabel array[] = new JLabel[100];
    int maxShip = 10;
    private int maxWidth = 5, maxHeight = 5;
    AppMainMenu father;

    ShipSettings(AppMainMenu father) {
        this.father = father;
        setWindow();
        iniAll();
        setBoundsAll();
        setTextAll();
  
        num = (int) NumShip.getValue();
        change.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Change();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
        NumShip.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Change();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
        close.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Visible(false, true);
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
    public void setTextAll()
    {
        close.setText("close");
        close.setFont(new java.awt.Font("Tahoma",3, 20));
        change.setText("change");      
        change.setFont(new java.awt.Font("Tahoma", 3, 18));
        label.setText("Number of the Ship : ");
        label.setFont(new java.awt.Font("Tahoma", 3, 18));
        Widthlabel.setFont(new java.awt.Font("Tahoma", 3, 18));
        Heightlabel.setFont(new java.awt.Font("Tahoma", 3, 18));
    }
    public void setBoundsAll()
    {
        close.setBounds(1000, 600, 200, 50);
        change.setBounds(500, 10, 150, 50);
        label.setBounds(10, 10, 200, 50);
        NumShip.setBounds(250, 10, 200, 50);
        Widthlabel.setBounds(225,50,200,50);
        Heightlabel.setBounds(525,50,200,50);
    }
    public void iniAll()
    {
        close = new JButton();
        change = new JButton();
        label = new JLabel();
        sm = new SpinnerNumberModel(5, 1, maxShip, 1); //default value,lower bound,upper bound,increment by
        NumShip = new JSpinner(sm);
        
        for (int i = 0; i < 11; i++) {
            sm = new SpinnerNumberModel(1, 1, maxWidth, 1);
            Width[i] = new JSpinner(sm);
            Width[i].setBounds(250, 100 + i * 50, 50, 40);
            Width[i].setValue(Math.min((int) i + 1, maxWidth));
            sm = new SpinnerNumberModel(1, 1, maxHeight, 1);
            Height[i] = new JSpinner(sm);
            Height[i].setBounds(550, 100 + i * 50, 50, 40);
            Height[i].setValue((int) 1);
            array[i] = new JLabel("Ship " + (i + 1) + " :");
            array[i].setFont(new java.awt.Font("Tahoma", 3, 18));
            array[i].setBounds(10, 100 + i * 50, 100, 50);
            }
    }
    public void setWindow()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setContentPane(new JLabel(new ImageIcon("IMG\\ShipSetting.jpg")));
    }
    public void MaxWH(int width, int height) {
        maxWidth = width;
        maxHeight = height;
        Show(false);
    }

    public void f() {
        
        ships.clear();
         for (int i = 0; i < num; i++) {
            int temp = (int)Width[i].getValue();
            if(temp > maxWidth)
                temp = 1;
            sm = new SpinnerNumberModel(temp, 1, maxWidth, 1); //default value,lower bound,upper bound,increment by
            Width[i] = new JSpinner(sm);
            Width[i].setBounds(250, 100 + i * 50, 50, 40);
            Width[i].setValue(temp);
            temp = (int)Height[i].getValue();
             System.out.println("AAA : "+maxHeight);
            if(temp > maxHeight)
                temp = 1;
            sm = new SpinnerNumberModel(temp , 1 , maxHeight, 1); //default value,lower bound,upper bound,increment by
            Height[i] = new JSpinner(sm);
            Height[i].setBounds(550, 100 + i * 50, 50, 40);
            Height[i].setValue(temp);
            array[i] = new JLabel("Ship " + (i + 1) + " :");
            array[i].setFont(new java.awt.Font("Tahoma", 3, 18));
            array[i].setBounds(10, 100 + i * 50, 100, 50);
            this.getContentPane().add(array[i]);
            this.getContentPane().add(Width[i]);
            this.getContentPane().add(Height[i]);
            }
    }

    public void Show(boolean ok) {
        this.getContentPane().removeAll();
        this.getContentPane().add(close);
        this.getContentPane().add(change);
        this.getContentPane().add(label);
        this.getContentPane().add(NumShip);
        this.getContentPane().add(Widthlabel);
        this.getContentPane().add(Heightlabel);
        f();
        if (ok) {
            Visible(false, false);
            Visible(true, false);
        }
    }

    public void Visible(boolean bo, boolean bo2) {
        if (bo2) {
            father.Visable(!bo);
            if (bo) {
                Show(true);
            } else {
                this.setVisible(bo);
            }
        } else {
            this.setVisible(bo);
        }
    }

    public void Change() {
        num = (int) NumShip.getValue();
        Show(true);
    }

    public List<Ship> getListShip() {
        Show(false);
        ships.clear();
        System.out.println(num);
        for (int i = 0; i < num; i++) {
            ships.add(new Ship(1, 1, (int) Width[i].getValue(), (int) Height[i].getValue()));
        }
        return ships;
    }
}
