package state;

import player.Player;

public class StoppedState implements PlayerState {

    public void play(Player player) {
        System.out.println("Начинаю играть...");
        player.setState(new PlayingState());
    }

    public void pause(Player player) {
        System.out.println("Нельзя поставить на паузу, трек остановлен");
    }

    public void stop(Player player) {
        System.out.println("Трек уже остановлен");
    }
}