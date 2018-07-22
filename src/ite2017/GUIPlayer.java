package ite2017;

import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class GUIPlayer extends JFrame implements Serializable{

    int Width = 10, Height = 10, NumDestryedMyShip = 0, NumDestryedherShip = 0, NumShip = 0;
    JButton MyGrid[][] = new JButton[21][21];
    boolean HaveMine[][] = new boolean[21][21];
    JButton DraftGrid[][] = new JButton[21][21];
    boolean attack[][] = new boolean[21][21];
    boolean attackDraft[][] = new boolean[21][21];
    JButton back = new JButton();
    JTextField SaveText = new JTextField("1");
    JButton Save = new JButton("Save Game");
    JButton QuickSave = new JButton("Quick Save");
    JLabel label = new JLabel();
    JLabel label2 = new JLabel();
    JLabel labelShip = new JLabel();
    Color DestroyedSquer, ShipSquer, UnknowSquer, EmptySquer, WaterSquer , MineColor;
    int sizeSquer = 25;
    int GUIChoese = 1;
    JLabel counter = new JLabel("0");
    JLabel lastAttack = new JLabel();
    JLabel Textcounter = new JLabel("Counter : ");
    JLabel TextlastAttack = new JLabel("Last Attack: ");
    JLabel countertime = new JLabel("");
    JLabel Textcountertime = new JLabel("Timer : ");
    int numSquer=0,score=0;
    int counterTime = 0;
    Player father;
    GUIPlayer(int Width, int Height, Player father, int GUIChoese) {
        this.father = father;
        this.GUIChoese = GUIChoese;
        this.Width = Width;
        this.Height = Height;
        
        numSquer = Width * Height;
        setWindow();
        MineColor = Color.cyan;
        if (GUIChoese == 1) {
            DestroyedSquer = Color.red;
            WaterSquer = Color.blue;
            UnknowSquer = Color.black;
            EmptySquer = Color.yellow;
            ShipSquer = Color.green;
            sizeSquer = 25;
            iniGrid1();
        } else {
            DestroyedSquer = Color.red;
            WaterSquer = Color.blue;
            UnknowSquer = Color.black;
            EmptySquer = Color.yellow;
            ShipSquer = Color.green;
            this.setContentPane(new JLabel(new ImageIcon("IMG\\GUI2.jpg")));
            sizeSquer = 30;
            iniGrid2();
        }
        for (int i = 0; i < Width; i++)
            for (int j = 0; j < Height; j++)
                HaveMine[i][j] = false;
        setTextAll();
        setBoundsAll();
        
        this.getContentPane().add(back);
        
        ch();
        
        
        this.getContentPane().add(labelShip);
        this.getContentPane().add(label);
        this.getContentPane().add(counter);
        this.getContentPane().add(lastAttack);
        this.getContentPane().add(Textcounter);
        this.getContentPane().add(TextlastAttack);
        
        this.getContentPane().add(countertime);
        this.getContentPane().add(Textcountertime);
        this.setVisible(true);

    }

    public void ch()
    {
          back.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    back.setBackground(DestroyedSquer);
                }

                public void mouseEntered(MouseEvent e) {
                    return;
                }

                public void mouseExited(MouseEvent e) {

                    return;
                }

                public void mousePressed(MouseEvent e) {
                    return;
                }

                public void mouseReleased(MouseEvent e) {

                }
            });
          Save.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                        boolean SaveDone = ((BattleshipGame)father.currentGame).Save(SaveText.getText());           
                        if(SaveDone == false)
                            Save.setBackground(Color.RED);
                        else 
                            Save.setBackground(null);
                }

                public void mouseEntered(MouseEvent e) {
                    return;
                }

                public void mouseExited(MouseEvent e) {

                    return;
                }

                public void mousePressed(MouseEvent e) {
                    return;
                }

                public void mouseReleased(MouseEvent e) {

                }
            });
          QuickSave.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                         boolean SaveDone = ((BattleshipGame)father.currentGame).Save("Temp Game");           
                         if(SaveDone == false)
                            QuickSave.setBackground(Color.RED);
                         else 
                            QuickSave.setBackground(null);
                }

                public void mouseEntered(MouseEvent e) {
                    return;
                }

                public void mouseExited(MouseEvent e) {

                    return;
                }

                public void mousePressed(MouseEvent e) {
                    return;
                }

                public void mouseReleased(MouseEvent e) {

                }
            });
          for (int i = 0; i < Width; i++) {
                for (int j = 0; j < Height; j++) {
                    check(MyGrid[i][j], true);
                  }
            }

          for (int i = 0; i < Width; i++) {
                for (int j = 0; j < Height; j++) {
                     check(DraftGrid[i][j], false);
                }
            }


    }
    public void iniGrid1() {
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Height; j++) {
                MyGrid[i][j] = new JButton();
                MyGrid[i][j].setBounds(550 + i * sizeSquer, 50 + j * sizeSquer, sizeSquer, sizeSquer);
                MyGrid[i][j].setBackground(WaterSquer);
                this.getContentPane().add(MyGrid[i][j]);
                check(MyGrid[i][j], true);
              
              }
        }
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Height; j++) {
                DraftGrid[i][j] = new JButton();
                DraftGrid[i][j].setBounds(550 + i * sizeSquer, Height * sizeSquer + 70 + j * sizeSquer, sizeSquer, sizeSquer);
                DraftGrid[i][j].setBackground(UnknowSquer);
                this.getContentPane().add(DraftGrid[i][j]);
                check(DraftGrid[i][j], false);
            }
        }
    }

    public void iniGrid2() {
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Height; j++) {
                MyGrid[i][j] = new JButton();
                MyGrid[i][j].setBounds(50 + i * sizeSquer, 225 + j * sizeSquer, sizeSquer, sizeSquer);
                MyGrid[i][j].setBackground(WaterSquer);
                MyGrid[i][j].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\giphy.gif"));
                this.getContentPane().add(MyGrid[i][j]);
                check(MyGrid[i][j], true);
            }
        }
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Height; j++) {
                DraftGrid[i][j] = new JButton();
                DraftGrid[i][j].setBounds(200 + i * sizeSquer + Width * sizeSquer, 225 + j * sizeSquer, sizeSquer, sizeSquer);
                DraftGrid[i][j].setBackground(UnknowSquer);
                DraftGrid[i][j].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\Unknow.jpg"));
                this.getContentPane().add(DraftGrid[i][j]);
                check(DraftGrid[i][j], false);
            }
        }
    }
    public void Showstate()
    {
        this.getContentPane().remove(Textcountertime);
        this.getContentPane().remove(countertime);
       this.getContentPane().remove(counter);
       this.getContentPane().remove(lastAttack);
       this.getContentPane().remove(Textcounter);
       this.getContentPane().remove(TextlastAttack);
       this.getContentPane().remove(labelShip);
       this.getContentPane().remove(label);
       this.getContentPane().remove(SaveText);
       this.getContentPane().remove(Save);
       this.getContentPane().remove(QuickSave);
       this.getContentPane().remove(back);
      
    }
    public void setBoundsAll() {
        Textcountertime.setBounds(1080, 300, 200, 50);
        countertime.setBounds(1080, 350, 200, 50);
        counter.setBounds(1080, 50, 200, 50);
        lastAttack.setBounds(1080, 150, 200, 50);
        Textcounter.setBounds(1080, 25, 200, 50);
        TextlastAttack.setBounds(1080, 125, 200, 50);
        labelShip.setBounds(10, 50, 200, 50);
        label.setBounds(550, 10, 500, 50);
        SaveText.setBounds(1000, 400, 200, 50);
        Save.setBounds(1000, 400+70, 200, 50);
        QuickSave.setBounds(1000, 400+70*2, 200, 50);
        back.setBounds(1000, 400+70*3, 200, 50);
    }

    public void setTextAll() {
        QuickSave.setFont(new java.awt.Font("Tahoma", 3, 20));
        countertime.setFont(new java.awt.Font("Tahoma", 3, 20));
        Textcountertime.setFont(new java.awt.Font("Tahoma", 3, 20));
        countertime.setForeground(Color.RED);
        Textcountertime.setForeground(Color.yellow);
        label.setText("Build Army");
        label.setFont(new java.awt.Font("Tahoma", 3, 20));
        labelShip.setText("Ship 1 : ");
        labelShip.setFont(new java.awt.Font("Tahoma", 3, 20));
        back.setText("Back");
        back.setFont(new java.awt.Font("Tahoma", 3, 20));
        Save.setFont(new java.awt.Font("Tahoma", 3, 20));
        SaveText.setFont(new java.awt.Font("Tahoma", 3, 20));
        counter.setFont(new java.awt.Font("Tahoma", 3, 20));
        counter.setForeground(new java.awt.Color(255, 0, 0));
        lastAttack.setFont(new java.awt.Font("Tahoma", 3, 20));
        lastAttack.setForeground(new java.awt.Color(255, 0, 0));
        Textcounter.setFont(new java.awt.Font("Tahoma", 3, 20));
        Textcounter.setForeground(new java.awt.Color(255, 255, 0));
        TextlastAttack.setFont(new java.awt.Font("Tahoma", 3, 20));
        TextlastAttack.setForeground(new java.awt.Color(255, 255, 0));
    }

    public void setWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setContentPane(new JLabel(new ImageIcon("IMG\\GUI1.jpg")));
    }

    public void check(JButton button, boolean My) {
        button.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (My) {
                    setAttack(button, true);
                } else {
                    setAttackDraft(button, true);
                }
            }

            public void mouseEntered(MouseEvent e) {
                return;
            }

            public void mouseExited(MouseEvent e) {
                if (My) {
                    setAttack(button, false);
                } else {
                    setAttackDraft(button, false);
                }
                return;
            }

            public void mousePressed(MouseEvent e) {
                return;
            }

            public void mouseReleased(MouseEvent e) {
                if (My) {
                    setAttack(button, false);
                } else {
                    setAttackDraft(button, false);
                }
                return;
            }
        });
    }

    public Square getNextMove() {
        counterTime = 0;
        countertime.setText("0");
        while (true) {
            if (back.getBackground() == DestroyedSquer) {
                return null;
            }
            for (int i = 0; i < Width; i++) {
                for (int j = 0; j < Height; j++) {
                    if (attackDraft[i][j]) {
                       attackDraft[i][j] = false;
                       /*
                        DraftGrid[i][j].setBackground(DestroyedSquer);
                        if(GUIChoese == 2)
                           DraftGrid[i][j].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\DestroyedSquer.jpg"));
                     */
                        return new Square(j, i, null);
                    }
                }
            }
        }
    }

    public void setAttack(JButton button, boolean bo) {
        attack[(button.getX() - MyGrid[0][0].getX()) / sizeSquer][(button.getY() - MyGrid[0][0].getY()) / sizeSquer] = bo;
    }

    public void setAttackDraft(JButton button, boolean bo) {
        attackDraft[(button.getX() - DraftGrid[0][0].getX()) / sizeSquer][(button.getY() - DraftGrid[0][0].getY()) / sizeSquer] = bo;
    }
    
    public void setSquare1(Square s) {//الهجوم على رقعتي
        if (s.getState() == "failing" || (MyGrid[s.getY()][s.getX()].getBackground()==WaterSquer) ) {
            MyGrid[s.getY()][s.getX()].setBackground(EmptySquer);//nothing
            if(GUIChoese == 2)
                MyGrid[s.getY()][s.getX()].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\WaterSquer.png"));
        }
        else if(s.getState() == "Have Mine")
        {
             MyGrid[s.getY()][s.getX()].setBackground(Color.black);//nothing
             
        }
        else {
             MyGrid[s.getY()][s.getX()].setBackground(DestroyedSquer);
             if(GUIChoese == 2)
                   MyGrid[s.getY()][s.getX()].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\DestroyedSquer.jpg"));
        }
        
    }

    public void setSquare2(Square s) {
        if (s.getState() == "Water") {
            DraftGrid[s.getY()][s.getX()].setBackground(WaterSquer);
            if(GUIChoese == 2)
                 DraftGrid[s.getY()][s.getX()].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\WaterSquer.jpg"));
            lastAttack.setText("0");
        }
        else {
            lastAttack.setText(""+(numSquer));
            score+=numSquer;
            counter.setText(""+score);
            DraftGrid[s.getY()][s.getX()].setBackground(DestroyedSquer);
             if(GUIChoese == 2)
                 DraftGrid[s.getY()][s.getX()].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\DestroyedSquer.jpg"));
           
        }
        numSquer--;
    }

    public List<Ship> getRandomArmy(List<Ship> arm) {
        NumShip = arm.size();
        List<Ship> res = new ArrayList<Ship>();
        for (int i = 0; i < arm.size(); i++) {
            int w = arm.get(i).getWidth(), h = arm.get(i).getHeight();
            JButton s[][] = new JButton[w][h];
            for (int j = 0; j < w; j++) {
                for (int k = 0; k < h; k++) {
                    s[j][k] = new JButton();
                }
            }
            while (true) {
                if (back.getBackground() == DestroyedSquer) {
                    return null;
                }
                Random rand = new Random();
                int j = rand.nextInt(Width);
                int k = rand.nextInt(Height);
                if (Width - j >= w && Height - k >= h) {
                    boolean isgood = false;
                    for (int u = j; u < j + w; u++) {
                        for (int v = k; v < k + h; v++) {
                            if (MyGrid[u][v].getBackground() == ShipSquer) {
                                isgood = true;
                            }
                        }
                    }
                    if (isgood) {
                        continue;
                    }
                    arm.get(i);
                   // arm.get(i);
                    res.add(new Ship(k, j, arm.get(i).getHeight(), arm.get(i).getWidth()));
                    for (int u = j; u < j + w; u++) {
                        for (int v = k; v < k + h; v++) {
                            MyGrid[u][v].setBackground(ShipSquer);
                             if(GUIChoese == 2)
                               MyGrid[u][v].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\ShipSquer.jpg"));
                        }
                    }
                    attack[j][k] = false;
                    break;
                }
            }
        }
        label.setText("Battle Time");
        this.getContentPane().add(Save);
        this.getContentPane().add(SaveText);
        this.getContentPane().add(QuickSave);
        return res;
    }

    public List<Ship> getArmy(List<Ship> arm) {
        List<Ship> res = new ArrayList<Ship>();
        for (int i = 0; i < arm.size(); i++) {
            int w = arm.get(i).getWidth(), h = arm.get(i).getHeight();
            JButton s[][] = new JButton[w][h];
            for (int j = 0; j < w; j++) {
                for (int k = 0; k < h; k++) {
                    s[j][k] = new JButton();
                    s[j][k].setBounds(100 + Width * 25 + j * 25, 50 + k * 25, 25, 25);
                    s[j][k].setBackground(ShipSquer);
                    if(GUIChoese == 2)
                        s[j][k].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\ShipSquer.jpg"));
                      
                    this.getContentPane().add(s[j][k]);
                }
            }
            labelShip.setText("Ship " + (i + 1) + ":");
            this.setVisible(false);
            this.setVisible(true);
            while (true) {
                if (back.getBackground() == DestroyedSquer) {
                    return null;
                }
                boolean bool = false;
                for (int j = 0; j < Width; j++) {
                    for (int k = 0; k < Height; k++) {
                        if (!bool && attack[j][k] && Width - j >= w && Height - k >= h) {
                            boolean isGood = false;
                            for (int u = j; u < j + w; u++) {
                                for (int v = k; v < k + h; v++) {
                                    if (MyGrid[u][v].getBackground() == ShipSquer) {
                                        isGood = true;
                                    }
                                }
                            }
                            if (isGood) {
                                continue;
                            }
                            bool = true;
                            arm.get(i).setX(j);
                            arm.get(i).setY(k);
                            res.add(new Ship(k, j, arm.get(i).getHeight(), arm.get(i).getWidth()));
                            for (int u = j; u < j + w; u++) {
                                for (int v = k; v < k + h; v++) {
                                    MyGrid[u][v].setBackground(ShipSquer);
                                    if(GUIChoese == 2)
                                       MyGrid[u][v].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\ShipSquer.jpg"));
                   
                                }
                            }
                            attack[j][k] = false;
                            break;
                        }
                    }
                    if (bool) {
                        break;
                    }
                }
                if (bool) {
                    break;
                }
            }
            for (int j = 0; j < w; j++) {
                for (int k = 0; k < h; k++) {
                    this.getContentPane().remove(s[j][k]);
                }
            }
            this.getContentPane().setVisible(false);
            this.getContentPane().setVisible(true);
        }
        labelShip.setText("");
        label.setText("Battle Time");
        this.getContentPane().add(Save);
        this.getContentPane().add(SaveText);
        this.getContentPane().add(QuickSave);
        return res;
    }
    public void setMine(int x,int y)
    {
        HaveMine[x][y] = true;
        MyGrid[x][y].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\Mine.png"));
        
    }
    public void moveMine(int x,int y,int NextX,int NextY)
    {
       HaveMine[x][y] = !HaveMine[x][y];
       HaveMine[NextX][NextY] = !HaveMine[NextX][NextY];
       MyGrid[x][y].setIcon(null);
       if(GUIChoese == 2)
       {
           Color temp = MyGrid[x][y].getBackground();
           if(temp == WaterSquer)
           {
                  MyGrid[x][y].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\WaterSquer.png"));   
           }
           if(temp == ShipSquer)
           {
               MyGrid[x][y].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\ShipSquer.jpg"));
           }
           if(temp == UnknowSquer)
           {
                DraftGrid[x][y].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\Unknow.jpg"));
               
           }
           if(temp == EmptySquer)
           {
                 MyGrid[x][y].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\WaterSquer.png"));   
           }
           
       }
       MyGrid[NextX][NextY].setIcon(new javax.swing.ImageIcon("IMG\\Grid\\Mine.png"));
    }
  
}
