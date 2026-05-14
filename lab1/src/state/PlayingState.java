package state;

import player.Player;

public class PlayingState implements PlayerState {

    public void play(Player player) {
        System.out.println("Уже играет");
    }

    public void pause(Player player) {
        System.out.println("Пауза...");
        player.setState(new PausedState());
    }

    public void stop(Player player) {
        System.out.println("Остановка...");
        player.setState(new StoppedState());
    }
}