package ite2017;

public class RandomComputerPlayer extends ComputerPlayer{
    
     public RandomComputerPlayer() {
        super();
        this.currentStrategy=new RandomComputerStrategy();
    }
}
