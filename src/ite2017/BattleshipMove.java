package ite2017;

import java.io.Serializable;

public class BattleshipMove implements Serializable{

    private Square AttackSquare;

    public BattleshipMove()
    {
        
    }
        
    public BattleshipMove(Square AttSquare)
    {
        this.AttackSquare = AttSquare;
    }
    public void Attack(Square square) {
        AttackSquare = square;
    }
   public Square getAttackSquare()
   {
       return AttackSquare;
   }
}
