package ite2017;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class AppMainMenu extends JFrame {
    LoadList G = new LoadList();
    ShipSettings shipSettings = new ShipSettings(this);
    GridSettings gridSettings = new GridSettings(this);
    ShowSettings showSettings = new ShowSettings(this);
    JButton back;
    JLabel TextLoadName = new JLabel("Enter Game Name");
    JTextField LoadName = new JTextField("");
    JButton ScorePlyers = new JButton("Score Board");
    JButton ShowLastGames = new JButton("Show Last Games");
    JButton Load = new JButton("Load Game");
    JButton deleteGame = new JButton("Delete Game");
    JButton LoadNow = new JButton("Load this Game");
    JButton QuickLoad = new JButton("Quick Load");
    JButton Start_game, Ship_Settings, Grid_Settings, Show_Settings, Exit, OnePlayer, TwoPlayer;
    boolean Startbool = false, Exitbool = false , Loadbool = false , RestorGame = false;
    int width = 10, height = 10;
    int NumberPlayer = 1;
    String idRestorGame;
    AppMainMenu() {
        
        G.setVisible(true);
        G.setTitle("Save");
        G.setFont(new java.awt.Font("Tahoma",3, 18));
        setWindow();
        shipSettings.setTitle("Ship Settings");
        gridSettings.setTitle("Grid Settings");
        showSettings.setTitle("Show Settings");
        iniAll();
        setForegroundALL();
        setTextAll();
        setBoundsAll();
        firstShow();
        allCheck();
        Visable(true);
    }
    public void iniAll()
    {
        Start_game = new JButton();
        back = new JButton();
        Ship_Settings = new JButton();
        Grid_Settings = new JButton();
        Show_Settings = new JButton();
        Exit = new JButton();
        OnePlayer = new JButton();
        TwoPlayer = new JButton();
    }
    public void setForegroundALL()
    {
        ScorePlyers.setForeground(Color.BLACK);
        ShowLastGames.setForeground(Color.BLACK);
        QuickLoad.setForeground(Color.BLACK);
        Start_game.setForeground(Color.BLACK);
        deleteGame.setForeground(Color.BLACK);
        back.setForeground(Color.BLACK);
        TextLoadName.setForeground(Color.BLACK);
        Ship_Settings.setForeground(Color.BLACK);
        Grid_Settings.setForeground(Color.BLACK);
        OnePlayer.setForeground(Color.BLACK);
        TwoPlayer.setForeground(Color.BLACK);
        Exit.setForeground(Color.BLACK);
        Show_Settings.setForeground(Color.BLACK);
    }
    public void setTextAll()
    {
        ScorePlyers.setFont(new java.awt.Font("Tahoma",3, 18));
        ShowLastGames.setFont(new java.awt.Font("Tahoma",3, 18));
        QuickLoad.setFont(new java.awt.Font("Tahoma",3, 18));
        deleteGame.setFont(new java.awt.Font("Tahoma",3, 18));
        TextLoadName.setFont(new java.awt.Font("Tahoma",3, 18));
        LoadName.setFont(new java.awt.Font("Tahoma",3, 18));
        LoadNow.setFont(new java.awt.Font("Tahoma",3, 18));
        Load.setFont(new java.awt.Font("Tahoma",3, 18));
        TwoPlayer.setText("Two Player");
        TwoPlayer.setFont(new java.awt.Font("Tahoma",3, 18));
        Start_game.setText("Start Game");
        Start_game.setFont(new java.awt.Font("Tahoma", 3, 18));
        back.setText("Back");
        back.setFont(new java.awt.Font("Tahoma", 3, 18));
        Ship_Settings.setText("Ship settings");
        Ship_Settings.setFont(new java.awt.Font("Tahoma", 3, 18));
        Grid_Settings.setText("Grid settings");
        Grid_Settings.setFont(new java.awt.Font("Tahoma", 3, 18));
        Show_Settings.setText("Show settings");
        Show_Settings.setFont(new java.awt.Font("Tahoma", 3, 18));
        Exit.setText("Exit");
        Exit.setFont(new java.awt.Font("Tahoma", 3, 18));
        OnePlayer.setText("One Player");
        OnePlayer.setFont(new java.awt.Font("Tahoma", 3, 18));
        
    }
    public void setBoundsAll()
    {
        
        QuickLoad.setBounds(450, 100 + 70 * 5 , 200, 50);
        deleteGame.setBounds(450, 100 + 70 * 4 , 200, 50);
        Start_game.setBounds(450, 100, 200, 50);
        back.setBounds(850, 600, 200, 50);
        Grid_Settings.setBounds(450, 100 + 70 * 2, 200, 50);
        Ship_Settings.setBounds(450, 100 + 70, 200, 50);
        Show_Settings.setBounds(450, 100 + 70 * 3, 200, 50);
        Load.setBounds(450, 100 + 70 * 4, 200, 50);
        TextLoadName.setBounds(450, 100+70  , 200, 50);
        LoadName.setBounds(450, 100 + 70 *2 , 200, 50);
        LoadNow.setBounds(450, 100 + 70 *3 , 200, 50);
        ShowLastGames.setBounds(450, 100 + 70 * 5, 200, 50);
        ScorePlyers.setBounds(450, 100 + 70 * 6, 200, 50);
        Exit.setBounds(450, 100 + 70 * 7, 200, 50);
        OnePlayer.setBounds(450, 100, 200, 50);
        TwoPlayer.setBounds(450, 100 + 70, 200, 50);
        
    }
    public void setWindow()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1150, 720);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        this.setContentPane(new JLabel(new ImageIcon("IMG\\MainMenu.gif")));
    }
    public void allCheck()
    {
        check(ScorePlyers);
        check(ShowLastGames);
        check(QuickLoad);
        check(Start_game);
        check(Ship_Settings);
        check(Grid_Settings);
        check(Show_Settings);
        check(Exit);
        check(OnePlayer);
        check(TwoPlayer);
        check(back);
        check(Load);
        check(LoadNow);
        check(deleteGame);
       
    }
    public void firstShow() {
        G.setVisible(false);
        this.getContentPane().removeAll();
        this.setVisible(false);
        this.setVisible(true);
        
        this.getContentPane().add(ScorePlyers);
        this.getContentPane().add(ShowLastGames);
        this.getContentPane().add(Start_game);
        this.getContentPane().add(Ship_Settings);
        this.getContentPane().add(Grid_Settings);
        this.getContentPane().add(Show_Settings);
        this.getContentPane().add(Load);
        this.getContentPane().add(Exit);
        
    }
    
    public void LoadShow() {
        List<String> ListSavedGame = new ArrayList<>();
        ListSavedGame = getSavedGame();
        G.LoadList_update(ListSavedGame);
        G.setVisible(false);
        G.setVisible(true);
        
        this.setBounds(200, 0, 1150, 720);
        this.setLocationRelativeTo(null);

        this.getContentPane().removeAll();
        this.setVisible(false);
        this.setVisible(true);
        this.getContentPane().add(QuickLoad);
        this.getContentPane().add(LoadNow);
        this.getContentPane().add(LoadName);
        this.getContentPane().add(TextLoadName);
        this.getContentPane().add(deleteGame);
        this.getContentPane().add(back);
       
        
    }
    
    public void dataShow()
    {
        this.setVisible(false);
        Look c = new Look(this);
        c.setVisible(true);
      
    }
    public int get_Width() {
        return gridSettings.get_Width();
    }

    public int get_Height() {
        return gridSettings.get_Height();
    }

    public void check(JButton button) {
        button.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (button.getText() == "Start Game") {
                    Start();
                } else if (button.getText() == "Ship settings") {
                    shipSettings.Visible(true, true);
                } else if (button.getText() == "Grid settings") {
                    gridSettings.Visible(true, true);
                } else if (button.getText() == "Show settings") {
                    showSettings.Visible(true, true);
                } else if (button.getText() == "Exit") {
                    Exit();
                } else if (button.getText() == "One Player") {
                    NumberPlayer = 1;
                    Startbool = true;
                    Visable(false);

                } else if (button.getText() == "Two Player") {
                    NumberPlayer = 2;
                    Startbool = true;
                    Visable(false);

                } else if (button.getText() == "Back") {
                    firstShow();
                }
                else if (button.getText() == "Load Game") {
                    LoadShow();
                }
                else if(button.getText() == "Load this Game")
                {
                    Loadbool = true;
                }
                else if(button.getText() == "Quick Load")
                {
                    Loadbool = true;
                    LoadName.setText("Temp Game");
                    
                }
                else if(button.getText() == "Delete Game")
                {
                    boolean ok = DeleteGame();
                    
                    System.out.println(ok);
                    if(ok)
                    {
                        LoadShow();
                    }
                }
                else if(button.getText() == "Show Last Games")
                {
                    dataShow();
                    
                }
                else if(button.getText() == "Score Board")
                {
                    getScoreBoard();
                    
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

    public void Visable(boolean bo) {
        this.setVisible(bo);
    }

    private void typeGame() {
        this.getContentPane().add(OnePlayer);
        this.getContentPane().add(TwoPlayer);
        this.getContentPane().add(back);

    }

    public void Exit() {
        this.dispose();
        this.shipSettings.dispose();
        Exitbool = true;
    }

    public void Start() {
        this.getContentPane().removeAll();
        this.setVisible(false);
        this.setVisible(true);
        typeGame();
     }

    public boolean getStartbool() {

        return Startbool;
    }

    public void setStartbool(boolean ok) {

        Startbool = ok;
    }

    public boolean getExitbool() {
        return Exitbool;
    }
    public boolean getLoadbool()
    {
        return Loadbool;
    }
    public void setLoadbool(boolean ok)
    {
        Loadbool = ok;
    }
    public int GetNumberPlayer() {
        return NumberPlayer;
    }
     public List<String> getSavedGame()
     {
         List<String> ListSavedGame = new ArrayList<>();
         try {
			FileInputStream fis = new FileInputStream("info.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
                        try {
                            ListSavedGame = (List<String> ) ois.readObject();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
                        }
			ois.close();
			  } catch (FileNotFoundException e) {
                               // e.printStackTrace();
                        } catch (IOException e) {
                                e.printStackTrace();
                        } 
         return ListSavedGame;
     }
     
     public void setSavedGame(List<String> ListSavedGame)
     {
            
        try {
			FileOutputStream fos = new FileOutputStream("info.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(ListSavedGame);
			oos.close();
                        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
         return ;
     }

    public boolean DeleteGame()
            {
                boolean ok = true;
                String temp =LoadName.getText();
                File file = new File("save//"+temp+".data");

                if(file.delete())
                {
                    ok =  true;
                }
                else
                {
                    ok = false;
                }
                if(ok)
                {
                     List<String> ListSavedGame = new ArrayList<>();
                     ListSavedGame = getSavedGame();
                     ListSavedGame.remove(temp);
                     setSavedGame(ListSavedGame);
                }
                return ok;
            }
    public void Restor()
    {
        GameRecord temp = null;
        try {
			FileInputStream fis = new FileInputStream("records//"+idRestorGame+".record");
			ObjectInputStream ois = new ObjectInputStream(fis);
                        try {
                            temp = (GameRecord)ois.readObject();
                            this.setVisible(false);
                            temp.GUIPlayerOne.Showstate();
                            temp.GUIPlayerOne.setVisible(true);
                            temp.GUIPlayerTwo.Showstate();
                            temp.GUIPlayerTwo.setVisible(true);
                            MovePlyersShow NewMovePlyersShow = new MovePlyersShow(idRestorGame , this , temp);
                            
                            
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(AppMainMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
			ois.close();
			  } catch (FileNotFoundException e) {
                               e.printStackTrace();
                               return ;
                        } catch (IOException e) {
                                e.printStackTrace();
                                return ;
                        }
        
    }
    public void getScoreBoard()
    {
        ScoreBoard score = new ScoreBoard(this);
        this.setVisible(false);
    }
}