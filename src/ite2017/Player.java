package ite2017;

import java.io.Serializable;

public abstract class Player implements Serializable{

    transient protected IPlayable currentGame;

    public void SubscribeTo(IPlayable game) throws PlayerNotCompatableException {
        if (!((game instanceof BattleshipGame) && (this instanceof BattleshipPlayer))) {
            throw new PlayerNotCompatableException();
        }
        currentGame = game;
    }

    public void LeaveGame() {
        currentGame = null;
    }

}
