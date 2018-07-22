package ite2017;

import java.awt.Color;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class bashar__mohamad {

    public static void main(String[] args) { 
        AppMainMenu newGUI = new AppMainMenu();
     
        while (true) {
            newGUI.setTitle("Battleship Game");
            boolean bool2 = newGUI.getExitbool();
            while (true) {
                boolean bool1 = newGUI.getStartbool();
                boolean Loadbool = newGUI.getLoadbool();
                String loadstring = "1";
                System.out.print("");
                
                if(newGUI.RestorGame)
                {
                    newGUI.RestorGame = false;
                    newGUI.Restor();
                    break;
                }
                if(Loadbool)
                {
                    loadstring = newGUI.LoadName.getText();
                    if(loadstring.length() != 0)
                       Load_Game(newGUI,loadstring);
                    Loadbool = false;
                    newGUI.setLoadbool(false);
                    break;
                }
                if (bool1) {
                    New_Game(newGUI);
                    newGUI.setStartbool(false);
                    newGUI.firstShow();
                    newGUI.Visable(true);
                    break;
                }
                if (bool2) {
                    break;
                }
            }
            if (bool2) {
                break;
            }
        }
    }
    static public void New_Game(AppMainMenu newGUI)
    {
                    String NameOne = "Computer Player" , NameTwo = "Computer Player";
                    int GUIChoese = newGUI.showSettings.get_GUIChoese();
                    BattleshipGame NewBattleshipGame   = new BattleshipGame();
                    NewBattleshipGame.setWidth(newGUI.get_Width());
                    NewBattleshipGame.setHeight(newGUI.get_Height());
                    NewBattleshipGame.setArmy(newGUI.shipSettings.getListShip());
                    Player one = new HumanPlayer();  
                    try {
                        one.SubscribeTo(NewBattleshipGame);
                    } catch (PlayerNotCompatableException e) {
                        System.out.println("Player Not Compatable Exception ..... ");
                    }
                    try {
                        NewBattleshipGame.Subscribe(one);
                    } catch (PlayerNotCompatableException e) {
                        System.out.println("the player not Battle Ship Player");
                    }
                    
                    ((HumanPlayer) one).bulid_Grid();
                    ((HumanPlayer) one).build_GUI(GUIChoese);
                    ((HumanPlayer) one).GUI.setTitle("First Player");
                    Player two ; 
                    if (newGUI.GetNumberPlayer() == 2) {
                        two = new HumanPlayer();
                        try {
                            two.SubscribeTo(NewBattleshipGame);
                        } catch (PlayerNotCompatableException e) {
                            System.out.println("Player Not Compatable Exception ..... ");
                        }
                        try {
                            NewBattleshipGame.Subscribe(two);
                        } catch (PlayerNotCompatableException e) {
                            System.out.println("the player not Battle Ship Player");
                        }
                        ((HumanPlayer) two).bulid_Grid();
                        ((HumanPlayer) two).build_GUI(GUIChoese);
                        ((HumanPlayer) two).GUI.setTitle("Secound Player");
                    } else {
                        two = new ComputerPlayer();
                        int TurnTimeEnter  = 10; 
                        ((ComputerPlayer)two).TurnTime = TurnTimeEnter;
                        try {
                            two.SubscribeTo(NewBattleshipGame);
                        } catch (PlayerNotCompatableException e) {
                            System.out.println("Player Not Compatable Exception ..... ");
                        }
                        try {
                            NewBattleshipGame.Subscribe(two);
                        } catch (PlayerNotCompatableException e) {
                            System.out.println("the player not Battle Ship Player");
                        }
                        ((ComputerPlayer) two).bulid_Grid();
                        ((ComputerPlayer) two).build_GUI(newGUI.showSettings.getVisible(),GUIChoese);
                        ((ComputerPlayer) two).GUI.setTitle("Computer Player");
                    }
                    if(one instanceof HumanPlayer)
                    {
                        Login First = new Login("First Player");
                        NameOne = First.getPlayerName();
                        First.setVisible(false);
                        First = null;
                    }
                    if(two instanceof HumanPlayer)
                    {
                        Login Secound = new Login("Secound Player");
                        NameTwo = Secound.getPlayerName();
                        Secound.setVisible(false);

                        Secound = null;
                    }
                    ((BattleshipPlayer) one).GUI.setTitle(NameOne);
                    ((BattleshipPlayer) two).GUI.setTitle(NameTwo);
                    if(two instanceof ComputerPlayer)
                    {
                        LimteTime NewLimteTime = new LimteTime((ComputerPlayer) two); 
                        while(NewLimteTime.ok == true)
                        {
                            break;
                        }
                    }
                    NewBattleshipGame.Start();
                    
    }
    static public void toRed(AppMainMenu newGUI)
    {
        newGUI.LoadName.setBackground(Color.RED);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
                        }
         newGUI.LoadName.setBackground(null);
    }
    static public void togreen(AppMainMenu newGUI)
    {
        newGUI.LoadName.setBackground(Color.green);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
                        }
        newGUI.LoadName.setBackground(null);
    }
    
    static public void Load_Game(AppMainMenu newGUI,String loadstring)
    {
                    BattleshipGame NewBattleshipGame   = new BattleshipGame();
                    int numPlayer1 = 1 , numPlayer2 = 2;
                    int Turn = get_Turn(loadstring);
                    if(Turn == 3)
                    {
                         toRed(newGUI);
                         return ;
                    }
                    togreen(newGUI);
                    Player one =  get_Player(loadstring,numPlayer1);
                    if(one == null)
                    {
                        toRed(newGUI);
                        return ;
                    }
                    try {
                        one.SubscribeTo(NewBattleshipGame);
                    } catch (PlayerNotCompatableException e) {
                        System.out.println("Player Not Compatable Exception ..... ");
                    }
                    try {
                        NewBattleshipGame.Subscribe(one);
                    } catch (PlayerNotCompatableException e) {
                        System.out.println("the player not Battle Ship Player");
                    }
                   Player two = get_Player(loadstring,numPlayer2); 
                   if(two == null)
                   {
                        toRed(newGUI);
                        return ;
                   }
                        
                   try {
                            two.SubscribeTo(NewBattleshipGame);
                        } catch (PlayerNotCompatableException e) {
                            System.out.println("Player Not Compatable Exception ..... ");
                        }
                        try {
                            NewBattleshipGame.Subscribe(two);
                        } catch (PlayerNotCompatableException e) {
                            System.out.println("the player not Battle Ship Player");
                        }
                    mine T = get_MinePlayer(loadstring,1);
                    if(T == null)
                    {
                        toRed(newGUI);
                        return ;
                    }
                    T.setFather((BattleshipPlayer)one) ;
                    if(Turn == 1)
                       NewBattleshipGame.MinePlayer1 = T;
                    else 
                        NewBattleshipGame.MinePlayer2 = T;
                    T = get_MinePlayer(loadstring,2);
                     if(T == null)
                    {
                        toRed(newGUI);
                        return ;
                    }
                    T.setFather((BattleshipPlayer)two) ;
                    if(Turn == 1)
                    {
                        NewBattleshipGame.Turn = true;
                    }
                    else 
                        NewBattleshipGame.Turn = false;
                    if(Turn == 1)
                       NewBattleshipGame.MinePlayer2 = T;
                    else 
                        NewBattleshipGame.MinePlayer1 = T;
                    
                    NewBattleshipGame.currentPlayer = (BattleshipPlayer) one;
                    NewBattleshipGame.NextPlayer = (BattleshipPlayer) two;
                    
                    NewBattleshipGame.startTime = NewBattleshipGame.currentPlayer.startTime;
                    if(((BattleshipPlayer) NewBattleshipGame.currentPlayer) instanceof HumanPlayer)
                    {
                        ((BattleshipPlayer) NewBattleshipGame.currentPlayer).GUI.setVisible(true);
                    }
                    if(((BattleshipPlayer) NewBattleshipGame.NextPlayer) instanceof HumanPlayer)
                    {
                        ((BattleshipPlayer) NewBattleshipGame.NextPlayer).GUI.setVisible(true);
                    }
                    NewBattleshipGame.setWidth(NewBattleshipGame.currentPlayer.Width);
                    NewBattleshipGame.setHeight(NewBattleshipGame.currentPlayer.Height);
                    newGUI.G.setVisible(false);
                    newGUI.Visable(false);
                    NewBattleshipGame.LoadGame();
                    
                    newGUI.setStartbool(false);
                    newGUI.firstShow();
                    newGUI.Visable(true);
               
}
     static public int get_Turn(String loadstring )
    {
        List < Object >  temp  = new ArrayList<Object>() ;
            try {
                	FileInputStream fis = new FileInputStream("save//"+loadstring+".data");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        try {
                            temp = (List < Object >) ois.readObject();
                            boolean f =  (boolean)temp.get(4);
                            if(f == false)
                                return 1;
                            else 
                                return 2;
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
                        }
			ois.close();
			  } catch (FileNotFoundException e) {
                              return 3;
                        } catch (IOException e) {
                                e.printStackTrace();
                        } 
            return 3;
    }
    static public Player get_Player(String loadstring , int x)
    {
        List < Object >  temp  = new ArrayList<Object>() ;
            try {
                	FileInputStream fis = new FileInputStream("save//"+loadstring+".data");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        try {
                            temp = (List < Object >) ois.readObject();
                           if(x == 1)
                                return (Player)temp.get(0);
                            else 
                                return (Player)temp.get(1);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
                        }
			ois.close();
			  } catch (FileNotFoundException e) {
                              System.out.println("YY");
                              return null;
                               // e.printStackTrace();
                        } catch (IOException e) {
                                e.printStackTrace();
                        } 
            return null;
    }
     static public mine get_MinePlayer(String loadstring , int x)
    {
        List < Object >  temp  = new ArrayList<Object>() ;
            try {
			FileInputStream fis = new FileInputStream("save//"+loadstring+".data");
			ObjectInputStream ois = new ObjectInputStream(fis);
                        try {
                            temp = (List < Object >) ois.readObject();
                            if(x == 1)
                                return (mine)temp.get(2);
                            else 
                                return (mine)temp.get(3);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
                        }
			ois.close();
			  } catch (FileNotFoundException e) {
                               return null;
                                //e.printStackTrace();
                        } catch (IOException e) {
                                e.printStackTrace();
                        } 
            return null;
    }
    
}