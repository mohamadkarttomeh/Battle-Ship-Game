package ite2017;

import java.io.Serializable;

public abstract class abstractComputerStrategy implements Serializable{
    
    public abstract BattleshipMove GetNextMove(int Width,int Height) ;
}
