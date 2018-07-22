package ite2017;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class GridSettings extends JFrame {

    JButton Back = new JButton("Back");
    JButton show = new JButton("show");
    AppMainMenu father;
    int width, height;
    JSpinner width_Spinner, height_Spinner;
    JLabel labelwidth  = new JLabel("Width ");
    JLabel labelheight = new JLabel("height ");;
    JButton MyGrid[][] = new JButton[21][21];
    SpinnerModel sm,sm2;
    GridSettings(AppMainMenu father) {
        this.father = father;
        setWindow();
        
        sm = new SpinnerNumberModel(5, 1, 12, 1); //default value,lower bound,upper bound,increment by
        width_Spinner = new JSpinner(sm);
        sm2 = new SpinnerNumberModel(5, 1, 12, 1); 
        height_Spinner = new JSpinner(sm2);
       
        height = (int) height_Spinner.getValue();
        width = (int) width_Spinner.getValue();

        show.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Show();
            }

            public void mouseEntered(MouseEvent e) {
                show.setForeground(Color.GRAY);
                return;
            }

            public void mouseExited(MouseEvent e) {
                show.setForeground(Color.black);
                return;
            }

            public void mousePressed(MouseEvent e) {
                return;
            }

            public void mouseReleased(MouseEvent e) {
                return;
            }
        });
        Back.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Visible(false, true);
            }

            public void mouseEntered(MouseEvent e) {
                Back.setForeground(Color.GRAY);
                return;
            }

            public void mouseExited(MouseEvent e) {
                Back.setForeground(Color.black);
                return;
            }

            public void mousePressed(MouseEvent e) {
                return;
            }

            public void mouseReleased(MouseEvent e) {
                return;
            }
        });
        setTextAll();
        setBoundsAll();
        firstShow();
    }
    public void setBoundsAll()
    {
        labelwidth.setBounds(100, 10, 200, 50);
        Back.setBounds(1000, 600, 200, 50);
        show.setBounds(50, 210, 150, 50);
        height_Spinner.setBounds(50, 150, 150, 50); /// y , x , width , hegith
        labelheight.setBounds(100, 100, 200, 50);
        width_Spinner.setBounds(50, 50, 150, 50); /// y , x , width , hegith
    }
    public void setTextAll()
    {
        labelwidth.setFont(new java.awt.Font("Tahoma", 3, 18));
        labelheight.setFont(new java.awt.Font("Tahoma", 3, 18));
        Back.setFont(new java.awt.Font("Tahoma", 3, 20));
        show.setFont(new java.awt.Font("Tahoma", 3, 20));
    }
    public void firstShow()
    {
        this.getContentPane().add(height_Spinner);
        this.getContentPane().add(labelheight);
        this.getContentPane().add(width_Spinner);
        this.getContentPane().add(labelwidth);
        this.getContentPane().add(Back);
        this.getContentPane().add(show);
    }
    public void setWindow()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setContentPane(new JLabel(new ImageIcon("IMG\\GridSettings.jpg")));
    }
    public void Visible(boolean bo, boolean bo2) {
        this.setVisible(bo);
        if (bo2) {
            father.Visable(!bo);
        }
    }

    public void Show() {
        this.getContentPane().removeAll();
        width = (int) width_Spinner.getValue();
        height = (int) height_Spinner.getValue();
        father.shipSettings.MaxWH(width , height);
        
        this.getContentPane().add(Back);
        this.getContentPane().add(show);
        this.getContentPane().add(labelwidth);
        this.getContentPane().add(labelheight);
        this.getContentPane().add(width_Spinner);
        this.getContentPane().add(height_Spinner);
        for (int j = 0; j < width; j++) {
            for (int k = 0; k < height; k++) {
                MyGrid[j][k] = new JButton();
                MyGrid[j][k].setBounds(250 + j * 25, 50 + k * 25, 25, 25);
                MyGrid[j][k].setBackground(Color.black);
                this.getContentPane().add(MyGrid[j][k]);
            }
        }
        Visible(false, false);
        Visible(true, false);
    }

    public int get_Width() {
        return height;
    }

    public int get_Height() {
        return width;
    }
}
