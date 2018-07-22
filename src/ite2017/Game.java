package ite2017;


import java.util.ArrayList;
import java.util.List;

public abstract class Game implements IPlayable {

    protected List< Player> players = new ArrayList<Player>();

    @Override
    public void Subscribe(Player player) throws PlayerNotCompatableException {
        if (!(player instanceof BattleshipPlayer)) {
            throw new PlayerNotCompatableException();
        }
        this.players.add(player);
    }

    @Override
    public void Leave(Player player) {
        this.players.remove(player);
        player.LeaveGame();
    }

    public abstract void Start();

    public abstract void Stop();
}
