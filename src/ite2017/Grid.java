package ite2017;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Grid implements Serializable{

    private int Width, Height, NumberShip, NumberDestroyedShip;
    private String State;
    private Square ArraySquare[][];
    private List<Ship> ListShip = new ArrayList<>();
    /// for console
    public void setMineInSquear(int x,int y)
    {
        ArraySquare[x][y].setHaveMine(true);
    }
    public void moveMine(int x,int y,int x2,int y2)
    {
        ArraySquare[x2][y2].setHaveMine(true);
        ArraySquare[x][y].setHaveMine(false);
    }
  
    public void draw() {
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Height; j++) {
                System.out.print(ArraySquare[i][j].getState() + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < ListShip.size(); i++) {
            System.out.println(ListShip.get(i).getX() + "  " + ListShip.get(i).getY());
        }
    }

    public Grid(int Width, int Height, String State) {
        this.NumberShip = 0;
        this.NumberDestroyedShip = 0;
        this.Width = Width;
        this.Height = Height;
        this.State = State;
        ListShip.clear();
        this.ArraySquare = new Square[Width][Height];
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Height; j++) {
                if ("Draft".equals(State)) {
                    ArraySquare[i][j] = new Square(i, j, "None");
                } else {
                    ArraySquare[i][j] = new Square(i, j, "Water");
                }
            }
        }
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public void setState(String State) {
        this.State = State;
    }

    public int getWidth() {
        return this.Width;
    }

    public int getHeight() {
        return this.Height;
    }

    public String getState() {
        return this.State;
    }

    public Square getSquare(int X, int Y) {
        return ArraySquare[X][Y];
    }

    public void setSquare(Square square) {
        ArraySquare[square.getX()][square.getY()] = square;
    }

    public Ship getShip(int id) {
        if (id > NumberShip) {
            return null;
        }
        return ListShip.get(id);
    }

    public void addShip(Ship ship) {
        
        for (int i = ship.getX(); i < ship.getX() + ship.getWidth(); i++) {
            for (int j = ship.getY(); j < ship.getY() + ship.getHeight(); j++) {
                ArraySquare[i][j].setState("Part");
            }
        }
        NumberShip++;
        this.ListShip.add(ship);
    }

    public boolean CheckGrid(Ship ship) {
        if(ship.getX()<0 || ship.getY()<0 || ship.getX()+ship.getWidth()>Width || ship.getY()+ship.getHeight()>Height)
        {
            return false;
        }
        for (int i = ship.getX(); i < ship.getX() + ship.getWidth(); i++) {
            for (int j = ship.getY(); j < ship.getY() + ship.getHeight(); j++) {
                if ("Part"==(ArraySquare[i][j].getState())) {
                    return false;
                }
            }
        }
        return true;
    }

    public String AttackSequare(Square NewSquare) {
        String state = "failing";
        if(ArraySquare[NewSquare.getX()][NewSquare.getY()].getHaveMine() == true)
        {
            return "Have Mine";
        }
        if (!"Destryed".equals(ArraySquare[NewSquare.getX()][NewSquare.getY()].getState()) && !"Water".equals(ArraySquare[NewSquare.getX()][NewSquare.getY()].getState())) {
            for (int i = 0; i < NumberShip; i++) {
                Ship temp = this.ListShip.get(i);
                if (temp.inShip(NewSquare.getX(), NewSquare.getY())) {
                    state = "Destryedpart";
                    temp.ShipAttack();
                    ArraySquare[NewSquare.getX()][NewSquare.getY()].setState("Destryed");
                    if (temp.ShipIsDestroyed()) {
                        NumberDestroyedShip++;
                        state = "Destryedship";
                        if (NumberShip == NumberDestroyedShip) {
                            state = "DestryedAllShip";
                        }
                    }
                }
            }
        }
        return state;
    }
}
