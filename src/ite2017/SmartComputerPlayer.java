package ite2017;

public class SmartComputerPlayer extends ComputerPlayer {

    public SmartComputerPlayer() {
        super();
        this.currentStrategy = new SmartComputerStrategy();
    }
}
