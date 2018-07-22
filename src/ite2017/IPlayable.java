package ite2017;

public interface IPlayable {
    
    public void Subscribe(Player player)throws PlayerNotCompatableException;

    public void Leave(Player player);
}
