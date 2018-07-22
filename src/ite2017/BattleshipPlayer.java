package ite2017;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BattleshipPlayer extends Player {

    public Date startTime;
    protected int Width, Height;
    protected Grid MyGrid;
    protected Grid DraftGrid;
    protected BattleshipMove currentMove;
    List<Ship> Army = new ArrayList<Ship>();
    GUIPlayer GUI;
    List<moveRecord > myMove = new ArrayList<moveRecord>();
    BattleshipPlayer() {}

    public void bulid_Grid() {
        Width = ((BattleshipGame) currentGame).getWidth();
        Height = ((BattleshipGame) currentGame).getHeight();
        Army = ((BattleshipGame) currentGame).getArmy();
        MyGrid = new Grid(Width, Height, "My");
        DraftGrid = new Grid(Width, Height, "Draft");

    }
    public void setMineInSquear(int x,int y)
    {
        MyGrid.setMineInSquear(x, y);
    }
    public String getStateSquare(Square AttackSquare) {
        return this.DraftGrid.getSquare(AttackSquare.getX(), AttackSquare.getY()).getState();
    }

    public void set_currentMove(BattleshipMove currentMove) {
        this.currentMove = currentMove;
    }

    public void draw() {
        System.out.println("MyGrid");
        MyGrid.draw();
        System.out.println("DraftGrid");
        DraftGrid.draw();
    }

    public void BattleshipPlayerAddShip(Ship NewShip) {
        MyGrid.addShip(NewShip);
    }

    public boolean CheckAddShip(Ship NewShip) {
        return MyGrid.CheckGrid(NewShip);
    }

    public BattleshipMove GetNextMove() {
        currentMove = new BattleshipMove();
        return currentMove;
    }

    public AttackResult AcceptAttack(BattleshipMove NewBattleshipMove) {
        String state = this.MyGrid.AttackSequare(NewBattleshipMove.getAttackSquare());
        NewBattleshipMove.getAttackSquare().setState(state);
        Square s = NewBattleshipMove.getAttackSquare();
        GUI.setSquare1(s);
        return new AttackResult(state);
    }

    public void AcceptAttackResult(AttackResult NewAttackResult) 
    {
        Square temp = currentMove.getAttackSquare();
        System.out.print(NewAttackResult.result + "\n");
        if("Have Mine".equals(NewAttackResult.result))
        {
            this.DraftGrid.setSquare(new Square(temp.getX(), temp.getY(), "Water"));
            GUI.setSquare2(new Square(temp.getX(), temp.getY(), "Water"));
        }
        else if ("failing".equals(NewAttackResult.result)) {
            if (this.DraftGrid.getSquare(temp.getX(), temp.getY()).getState() == "Water" || this.DraftGrid.getSquare(temp.getX(), temp.getY()).getState() == "Destryed") {
                return;
            }
            this.DraftGrid.setSquare(new Square(temp.getX(), temp.getY(), "Water"));
            GUI.setSquare2(new Square(temp.getX(), temp.getY(), "Water"));
        } else {
            System.out.println("GG : "+NewAttackResult.result);
            this.DraftGrid.setSquare(new Square(temp.getX(), temp.getY(), "Destryed"));
            GUI.setSquare2(new Square(temp.getX(), temp.getY(), "Destryed"));
        }

    }

    public abstract List<Ship> getArmy();

    public boolean initArmy() {
        List<Ship> temp = this.getArmy();
        if(temp == null)
            return false;
        SequenceShip arm =new SequenceShip(temp);
        Iteratorinterface it = arm.getIterator();
        while(it.hasnext()){
            Ship NewShip1 = (Ship)it.getnext();
            if (this.CheckAddShip(NewShip1)) {
                this.BattleshipPlayerAddShip(NewShip1);
            }
        }
        return true;
    }
}
