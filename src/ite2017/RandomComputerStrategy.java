package ite2017;

import java.util.Random;

public class RandomComputerStrategy extends abstractComputerStrategy {

    @Override
    public BattleshipMove GetNextMove(int Width,int Height) {
        Random rand = new Random();
        int a = rand.nextInt(Width);
        int b = rand.nextInt(Height);
        return (new BattleshipMove(new Square(a, b, null)));
    }

}
