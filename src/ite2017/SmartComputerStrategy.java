package ite2017;

import java.util.Random;

public class SmartComputerStrategy extends abstractComputerStrategy {

    @Override
    public BattleshipMove GetNextMove(int Width,int Height) {
        BattleshipMove move = null;
        Random rand = new Random();
        Random x = new Random();
        int a = 1 + Math.abs((int) (((float) (x.nextInt()) / Integer.MAX_VALUE) * 8));
        int b = 1 + Math.abs((int) (((float) (x.nextInt()) / Integer.MAX_VALUE) * 8));
        Square s = new Square(a, b, null);
        move.Attack(s);
        return move;
    }
}
