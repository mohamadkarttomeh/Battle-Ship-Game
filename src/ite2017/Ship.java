package ite2017;

import java.io.Serializable;

public class Ship implements Serializable{

    private int X, Y, Width, Height;
    private int GoodSquare;

    public Ship(int X, int Y, int Width, int Height) {
        this.X = X;
        this.Y = Y;
        this.Width = Width;
        this.Height = Height;
        this.GoodSquare = this.Height * this.Width;
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getWidth() {
        return this.Width;
    }

    public int getHeight() {
        return this.Height;
    }

    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    public boolean inShip(int x, int y) {

        int Xstart = this.X;
        int Xend = this.X + this.Width - 1;
        int Ystart = this.Y;
        int Yend = this.Y + this.Height - 1;
        return (x >= Xstart && x <= Xend && y >= Ystart && y <= Yend);
    }

    public void ShipAttack() {
        GoodSquare--;
    }

    public boolean ShipIsDestroyed() {
        return GoodSquare == 0;
    }

    public int getGoodSquare() {
        return GoodSquare;
    }
}
