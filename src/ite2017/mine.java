
package ite2017;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class mine implements Serializable{
    private int x,y;
    private int Height , Width;
    boolean state;
    BattleshipPlayer Father ; 
    int dx[] = {1,-1,0,0};
    int dy[] = {0,0,1,-1};
    private List< Square> ListSquer = new ArrayList<>();
    mine(int x , int y  ,int Width , int  Height,  BattleshipPlayer Father)
    {
        this.x = x;
        this.y = y;
        this.Height = Height;
        this.Width = Width;
        this.state = false;
        this.Father = Father;
    }
    public void setFather(BattleshipPlayer Father)
    {
        this.Father = Father;
    }
    public boolean good(int x , int y)
    {
        boolean con1 = (Father.GUI.MyGrid[x][y].getBackground() == Father.GUI.WaterSquer);
        boolean con2 = (Father.GUI.MyGrid[x][y].getBackground() == Father.GUI.EmptySquer);
        boolean con3 = !Father.GUI.HaveMine[x][y];
        return ((con1||con2)&&(con3));
    }
  
    public void movie()
    {
        if(state == true)
            return ;
        ListSquer = new ArrayList<>();
        for(int i=0;i<4;i++)
        {
            int u,v;
            u = x+dx[i];
            v = y+dy[i];
            if((u>=0 && u<Width)&&(v>=0 && v<Height)&&(good(u,v)))
            {
             ListSquer.add(new Square(u,v,"Water"));
            }
        }
        if(ListSquer.size() > 0)
        {
         Random rand = new Random();
         int SZ = ListSquer.size();
         int a = rand.nextInt(SZ);
         int NextX = ListSquer.get(a).getX();
         int NextY = ListSquer.get(a).getY();
         Father.GUI.moveMine(x,y,NextX,NextY);
         Father.MyGrid.moveMine(y,x,NextY,NextX);
         x = NextX;
         y = NextY;
        }
    }
}
