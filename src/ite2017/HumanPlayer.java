package ite2017;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class HumanPlayer extends BattleshipPlayer {
 private int cnt = 0 ; 
 private Square s = new Square(0,0,null);
    HumanPlayer() {
        super();
        
    }
    public void build_GUI(int GUIChoese)
    {
        GUI = new GUIPlayer(Height,Width,this , GUIChoese);
    }
    @Override
    public BattleshipMove GetNextMove() {
        System.out.print("\nvvvv");
        BattleshipMove move = new BattleshipMove();
        HumanPlayer my = this; 
        Thread  t1 = new Thread(new Runnable() {
            @Override
            public void run() {  
                 while(true)
                {
                     try {
                         Thread.sleep(1000);
                            cnt++;
                            my.GUI.countertime.setText(""+cnt);
                        } catch (InterruptedException ex) {
                            break;
                            }
                }
            }
        });
        
        Thread  t2 = new Thread(new Runnable() {
            @Override
            public void run() {  
               
                     try {
                          s = GUI.getNextMove();
                          t1.interrupt();
                        } catch (Exception ex) {
                           
                           }
                
            }
        });
        t1.start();
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
        cnt = 0;
        move.Attack(s);
        return move;
    }
    
    
    @Override
    public List<Ship> getArmy() {
        this.GUI.setVisible(false);
        this.GUI.setVisible(true);
        return this.GUI.getArmy(Army);
    }
}
