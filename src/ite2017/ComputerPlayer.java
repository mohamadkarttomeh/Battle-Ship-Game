package ite2017;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComputerPlayer extends BattleshipPlayer {
    
    protected abstractComputerStrategy currentStrategy;
    boolean Visible = false;
    public int cnt = 0;
    public int a = 0;
    private boolean ok = false;
    private BattleshipMove H;
    public int TurnTime = 10;
    public ComputerPlayer() {
        super();
    }

    public void build_GUI(boolean bool , int GUIChoese) {
        Visible = bool;
        GUI = new GUIPlayer(Height, Width,this,GUIChoese);
        GUI.setVisible(Visible);
    }

    public void setStrategy(abstractComputerStrategy currentStrategy) {
        this.currentStrategy = currentStrategy;
    }

    @Override
    public BattleshipMove GetNextMove() {
        ok = false;
        this.GUI.counterTime = 0;
        this.GUI.countertime.setText("0"); 
         Random rand = new Random();
         a = rand.nextInt(TurnTime+1);
         a++;
         a = a*1000;
         BattleshipMove temp = this.currentStrategy.GetNextMove(Width, Height);
         ComputerPlayer my = this;
         currentMove = new BattleshipMove();
         Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                
                try {
                   
                     Thread.sleep(a);
                     ok = true;
                      while(true)
                        {
                             BattleshipMove temp = my.currentStrategy.GetNextMove(Width, Height);
                             H = temp;
                             int x1 , y1;
                             x1 = H.getAttackSquare().getX();
                             y1 = H.getAttackSquare().getY(); 
                             System.out.println("this is : "+x1+" "+y1);
                             if(my.GUI.DraftGrid[x1][y1].getBackground() == my.GUI.UnknowSquer){
                                 break;
                             }
      
                        }
                } catch (InterruptedException ex) {
                       
                    }
            }
        });     
         Thread  t2 = new Thread(new Runnable() {
            @Override
            public void run() {  
                 while(true)
                {
                     try {
                           Thread.sleep(1000);
                            cnt++;
                            my.GUI.countertime.setText(""+cnt);
                            if(cnt >= TurnTime || (cnt*1000 >= a))
                            {
                                my.GUI.countertime.setText("Pass");
                                t1.interrupt();
                                break;
                            }
                        } catch (InterruptedException ex) {
                            break;
                            }
                }
            }
        });
        t1.start();
        cnt = 0;
        t2.start();
        try {
            t1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        if(ok == false)
        {
           Square BattleshipMoveSquare = new Square(-1,-1,null);
            H = new BattleshipMove(BattleshipMoveSquare);
        }
        
        return H;
    }

    @Override
    public List<Ship> getArmy() {
        this.GUI.setVisible(Visible);
        return this.GUI.getRandomArmy(Army);
    }
}
