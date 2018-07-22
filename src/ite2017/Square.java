package ite2017;

import java.io.Serializable;

public class Square implements Serializable
{

    private int X, Y;
    private String State;
    private boolean HaveMine = false;
    public Square(int X, int Y, String State)
    {
        this.X = X;
        this.Y = Y;
        this.State = State;
    }
   public void setHaveMine(boolean ok)
   {
       HaveMine = ok;
   }
   public boolean getHaveMine()
   {
       return HaveMine;
   }
    public void setX(int X)
    {
        this.X = X;
    }

    public void setY(int Y)
    {
        this.Y = Y;
    }

    public void setState(String State)
    {
        this.State = State;
    }

    public int getX()
    {
        return this.X;
    }

    public int getY()
    {
        return this.Y;
    }

    public String getState()
    {
        return this.State;
    }

    public Square getSquare()
    {
        return new Square(this.X, this.Y, this.State);
    }

}
