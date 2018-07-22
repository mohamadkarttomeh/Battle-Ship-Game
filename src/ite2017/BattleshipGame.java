package ite2017;

import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BattleshipGame extends Game implements Serializable{

    boolean Turn;
    boolean Stopmove = false;
    Date startTime;
    BattleshipPlayer currentPlayer , NextPlayer , temp;
    mine MinePlayer1 , MinePlayer2;
    private int Width = 10;
    private int Height = 10;
    private String F;
    private List<Ship> Army = new ArrayList<Ship>();
    @Override
    public void Start() {
        if (players.size() == 2) {
            startTime = new Date();
            
            BattleshipPlayer one = ((BattleshipPlayer) players.get(0));
            BattleshipPlayer two = ((BattleshipPlayer) players.get(1));
            one.startTime = startTime;
            two.startTime = startTime;
            boolean Rungood1 = true, Rungood2 = true;
            
            Rungood1 = one.initArmy();
            if (Rungood1 == false) {
                Stop();
                return;
            }
            Rungood2 = two.initArmy();
            if (Rungood2 == false) {
                Stop();
                return;
            }
            /// to save GUI
            

            
            if (one instanceof ComputerPlayer) {
                ((ComputerPlayer) one).setStrategy(new RandomComputerStrategy());
            }
            if (two instanceof ComputerPlayer) {
                ((ComputerPlayer) two).setStrategy(new RandomComputerStrategy());
            }
            currentPlayer = (BattleshipPlayer) players.get(0);
            BattleshipPlayer temp;
            NextPlayer = (BattleshipPlayer) players.get(1);
            saveTempData();
            int X1=0 , X2=0 , Y1=0 , Y2=0;
            List< Square> ListSquer1   = new ArrayList<>();
            List< Square> ListSquer2   = new ArrayList<>();

            for(int i=0;i<Height ;i++)
            {
                for(int j=0;j< Width ;j++)
                {
                    if(currentPlayer.MyGrid.getSquare(j, i).getState() != "Part")
                    {
                        ListSquer1.add(new Square(i, j, null));
                    }
                    if(NextPlayer.MyGrid.getSquare(j, i).getState() != "Part")
                    {
                        
                        ListSquer2.add(new Square(i, j, null));
                    }
                }
            }
            Random rand = new Random();
            int SZ1 = ListSquer1.size();
            int SZ2 = ListSquer2.size();
            int a = rand.nextInt(SZ1);
            X1 = ListSquer1.get(a).getX();
            Y1 = ListSquer1.get(a).getY();
            a = rand.nextInt(SZ2);
            X2 = ListSquer2.get(a).getX();
            Y2 = ListSquer2.get(a).getY();
            currentPlayer.GUI.setMine(X1, Y1);
            MinePlayer1 = new mine(X1, Y1,Height ,  Width,  currentPlayer);
            Turn = true;
            NextPlayer.GUI.setMine(X2, Y2);
            MinePlayer2 = new mine(X2, Y2,Height ,  Width , NextPlayer);
            currentPlayer.setMineInSquear(Y1, X1);
            NextPlayer.setMineInSquear(Y2, X2);
            RunGame();
        }
    }
    public void LoadGame()
    {
        for(int i=0;i<currentPlayer.Height;i++)
        {
            for(int j=0;j<currentPlayer.Width;j++)
            {
                if(currentPlayer.GUI.HaveMine[i][j])
                {
                    currentPlayer.GUI.HaveMine[i][j] = !currentPlayer.GUI.HaveMine[i][j];
                    currentPlayer.GUI.MyGrid[i][j].setIcon(null);
                }
                if(NextPlayer.GUI.HaveMine[i][j])
                {
                    NextPlayer.GUI.HaveMine[i][j] = !NextPlayer.GUI.HaveMine[i][j];
                    NextPlayer.GUI.MyGrid[i][j].setIcon(null);
                }
            }
        }
        currentPlayer.GUI.ch();
        NextPlayer.GUI.ch();
        RunGame();
    }
    public void RunGame() {
        
        Stopmove = false;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                   try {
                       if(!Stopmove)
                        MinePlayer1.movie();
                    } catch (Exception e) {
                        e.printStackTrace();
                     break;
                    }
                    try {
                        Thread.sleep(1500);
                    
                    } catch (InterruptedException ex) {
                        break;  
                    }
                }

            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                   
                    try {                   
                        if(!Stopmove)
                        MinePlayer2.movie();
                     } catch (Exception e) {  
                        e.printStackTrace();
                         break;
                    }
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {   
                        break;
                    }
                }

            }
        });
        t2.start();
        while (true) {
            Turn = !Turn;
             Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                        F = currentPlayer.GUI.countertime.getText();
                        NextPlayer.GUI.countertime.setText(F);
                        
                    } catch (InterruptedException ex) {
                        break;  
                    }
                }

            }
        });
        t3.start();
       
            BattleshipMove NewBattlshipMove;
               NewBattlshipMove = currentPlayer.GetNextMove();
                if (NewBattlshipMove.getAttackSquare() == null) {
                    Stop();
                    return;
                }
            List< BattleshipMove> ListBattlshipMove = new ArrayList<>();
            ListBattlshipMove.add(NewBattlshipMove);
            int cnt = 0, finish = 1;
            while (cnt < finish) {
                NewBattlshipMove = ListBattlshipMove.get(cnt);
                if(NewBattlshipMove.getAttackSquare().getX() == -1)
                {
                    moveRecord NewRec = new moveRecord();    
                    NewRec.ok = false;
                    NewRec.timemove = new Date();
                    NewRec.res = "";
                    NewRec.x = 0;
                    NewRec.y = 0;
                    NewRec.PlayerName = currentPlayer.GUI.getTitle();
                    currentPlayer.myMove.add(NewRec);
                    cnt++;
                    continue;
                }
                currentPlayer.set_currentMove(NewBattlshipMove);
                AttackResult NewAttackResult = NextPlayer.AcceptAttack(NewBattlshipMove);
                 moveRecord NewRec = new moveRecord();    
                 NewRec.ok = true;
                 NewRec.timemove = new Date();
                 NewRec.res = NewAttackResult.result;
                 NewRec.x = NewBattlshipMove.getAttackSquare().getX();
                 NewRec.y = NewBattlshipMove.getAttackSquare().getY();
                 NewRec.PlayerName = currentPlayer.GUI.getTitle();
                 currentPlayer.myMove.add(NewRec);
                 
                if (NewAttackResult.result == "Have Mine") {
                    if(Turn == true)
                    {  
                        if(t1!=null)
                        {
                             t1.interrupt();
                             MinePlayer1.state = true;
                        }
                    }
                    else 
                    {
                        if(t2!=null)
                        {
                             t2.interrupt();
                              MinePlayer2.state = true;
                        }
                    }
                    int dx[] = {1, -1, 0};
                    int dy[] = {1, -1, 0};
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (i == 2 && j == 2) {
                                continue;
                            }
                            int u = NewBattlshipMove.getAttackSquare().getX() + dx[i];
                            int v = NewBattlshipMove.getAttackSquare().getY() + dy[j];

                            if ((u >= 0 && u < Width) && (v >= 0 && v < Height)) {
                                boolean con1;
                                System.out.println(Height + " " + Width + " " +u+" "+v);
                                con1 = (currentPlayer.GUI.DraftGrid[v][u].getBackground() == currentPlayer.GUI.UnknowSquer);
                                if (con1) {
                                    BattleshipMove tempBattlshipMove;
                                    Square tempSquare = new Square(u, v, null);
                                    tempBattlshipMove = new BattleshipMove(tempSquare);
                                    ListBattlshipMove.add(tempBattlshipMove);
                                    finish++;
                                }
                            }
                        }
                    }
                }
                currentPlayer.AcceptAttackResult(NewAttackResult);
                if ("DestryedAllShip".equals(NewAttackResult.result)) {
                    Winner win = new Winner((currentPlayer.GUI.getTitle()));
                    t1.interrupt();
                    t2.interrupt();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BattleshipGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    win.setVisible(false);
                    GameRecord NewGameRecord = new GameRecord();
                    int LastID = get_LastID();
                    NewGameRecord.ID = LastID+1;
                    add_ID(NewGameRecord.ID);
                    add_record(NewGameRecord.ID);
                    
                    Stop();
                    return ;
                }
                cnt++;
            }
            t3.interrupt();
           
            F = currentPlayer.GUI.countertime.getText();
            NextPlayer.GUI.countertime.setText(F);
            
            temp = currentPlayer;
            currentPlayer = NextPlayer;
            NextPlayer = temp;
          ///  Save();
        }
    }
    public GUIPlayer getGUIPlayer(Date start , int player)
    {
        GUIPlayer temp = null; 
        String s ;
        if(player == 1)
        {
            s = "currentPlayer";
        }
        else 
        {
            s = "NextPlayer";
        }
         try {
                        String T = start.toString();
                        String R = "";
                        for(int i=0;i<T.length();i++)
                        {
                            if(T.charAt(i) != ':')
                            {
                                R+=T.charAt(i);
                            }
                        }
                	FileInputStream fis = new FileInputStream("temp//"+R+"."+s);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        try {
                            temp = (GUIPlayer) ois.readObject();
                          
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
                        }
			ois.close();
			  } catch (FileNotFoundException e) {
                              return null;
                          } catch (IOException e) {
                                e.printStackTrace();
                        } 
        
        return temp;
    }
    public void add_record(int ID)
    {
        GUIPlayer temp1 = getGUIPlayer(startTime,1) , temp2 = getGUIPlayer(startTime,2);
        GameRecord temp = new GameRecord();
        temp.GUIPlayerOne = temp1;
        temp.GUIPlayerTwo = temp2;
        temp.StartTime = startTime;
        temp.WinPlayer = currentPlayer.GUI.getTitle();
        temp.LosePlayer = NextPlayer.GUI.getTitle();
        System.out.println("SS : "+Turn+currentPlayer.GUI.getTitle() + " "+ NextPlayer.GUI.getTitle());
        if(Turn == false)
        {
            temp.PlayerOne = currentPlayer.GUI.getTitle();
            temp.PlayerOneMark = currentPlayer.GUI.score;
            temp.Playeronemove = currentPlayer.myMove;
            temp.PlayerTwo = NextPlayer.GUI.getTitle();
            temp.PlayerTwoMark = NextPlayer.GUI.score;
            temp.Playertwomove = NextPlayer.myMove;
        }
        else 
        {
            temp.PlayerTwo = currentPlayer.GUI.getTitle();
            temp.PlayerTwoMark = currentPlayer.GUI.score;
            temp.Playertwomove = currentPlayer.myMove;
            temp.PlayerOne = NextPlayer.GUI.getTitle();
            temp.PlayerOneMark = NextPlayer.GUI.score;
            temp.Playeronemove = NextPlayer.myMove;
        }
       temp.ID = ID;
       temp.saveRecord();
        
    }
    public boolean Save(String saveName)
    {
       Stopmove = true;
        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(BattleshipGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Object> tempSave = new ArrayList<Object>();
        tempSave.add(this.currentPlayer);
        tempSave.add(this.NextPlayer);
        tempSave.add(MinePlayer1);
        tempSave.add(MinePlayer2);
        tempSave.add(Turn);
        Stopmove = false;
        if(saveName.length() == 0)
        {
            return false;
        }
               try {
			FileOutputStream fos = new FileOutputStream("save//"+saveName+".data");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tempSave);
			oos.close();
                        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}     
               List<String> ListSavedGame = new ArrayList<>();
               ListSavedGame = getSavedGame();
               boolean isHere = false;
               System.out.println(ListSavedGame.size());
               for(int i=0 ;i<ListSavedGame.size();i++)
               {
                    System.out.println(saveName+":"+ListSavedGame.get(i)+":");
                      
                   if(ListSavedGame.get(i).equals(saveName)  )
                   {
                       isHere = true;
                   }
               }
               System.out.println(isHere);
               if(isHere == false)
                  ListSavedGame.add(saveName);
               setSavedGame(ListSavedGame);
               return true;
    }
    @Override
    public void Stop() {
        BattleshipPlayer one = ((BattleshipPlayer) players.get(0));
        BattleshipPlayer two = ((BattleshipPlayer) players.get(1));

        one.LeaveGame();
        two.LeaveGame();
         try{
             one.GUI.dispose();
        }catch (Exception e) {
			
        }
        try{
        two.GUI.dispose();
        }catch (Exception e) {	
        }
        
    }

    public int getWidth() {
        return this.Width;
    }

    public int getHeight() {
        return this.Height;
    }

    public List<Ship> getArmy() {
        return this.Army;
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public void setArmy(List<Ship> Army) {
        this.Army = Army;
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
    
    public int get_LastID()
    {
        int LastID = 0;
        
        List<Integer> IdGames = new ArrayList<>();
         try {
			FileInputStream fis = new FileInputStream("ID.data");
			ObjectInputStream ois = new ObjectInputStream(fis);
                        try {
                            IdGames = (List<Integer> ) ois.readObject();
                            int temp = IdGames.size();
                            LastID = LastID+temp;
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
                        }
			ois.close();
			  } catch (FileNotFoundException e) {                            
                              System.out.println("NOT FOUND");
                               // e.printStackTrace();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }  
        return LastID;
    }
    
    public void add_ID(int NewID)
    {
        List<Integer> IdGames = new ArrayList<>();
         try {
			FileInputStream fis = new FileInputStream("ID.data");
			ObjectInputStream ois = new ObjectInputStream(fis);
                        try {
                            IdGames = (List<Integer> ) ois.readObject();
                            IdGames.add(NewID);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
                        }
			ois.close();
			  } catch (FileNotFoundException e) {
                              System.out.println("NOT FOUND");
                               // e.printStackTrace();
                        } catch (IOException e) {
                                e.printStackTrace();
                        } 
        if(IdGames.size() == 0)
        {
         IdGames.add(NewID);   
        }
         try {
             System.out.println("CCC");
			FileOutputStream fos = new FileOutputStream("ID.data");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(IdGames);
			oos.close();
                        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        
        return ;
    }
    
    
    public void saveTempData()
    {
        
        try {
                        String T = startTime.toString();
                        String R = "";
                        for(int i=0;i<T.length();i++)
                        {
                            if(T.charAt(i) != ':')
                            {
                                R+=T.charAt(i);
                            }
                        }
                        System.out.println(R);
			FileOutputStream fos = new FileOutputStream("temp\\"+R+".currentPlayer");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(currentPlayer.GUI);
			oos.close();
                        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        try {
                        String T = startTime.toString();
                        String R = "";
                        for(int i=0;i<T.length();i++)
                        {
                            if(T.charAt(i) != ':')
                            {
                                R+=T.charAt(i);
                            }
                        }
			FileOutputStream fos = new FileOutputStream("temp//"+R+".NextPlayer");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(NextPlayer.GUI);
			oos.close();
                        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
         return ;
        
    }
}