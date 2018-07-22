package ite2017;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GameRecord implements Serializable{
    int ID;
    public String PlayerOne , PlayerTwo;
    public String WinPlayer , LosePlayer;
    public Date StartTime , EndTime;
    public int PlayerOneMark , PlayerTwoMark;
    public GUIPlayer  GUIPlayerOne , GUIPlayerTwo; 
    public List<moveRecord > Playeronemove , Playertwomove; 
    GameRecord()
    {
         EndTime = new Date();
    }
    public void saveRecord()
    { 
        try {
		FileOutputStream fos = new FileOutputStream("records//"+ID+".record");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
        
        
    }
    
    
}
