package state;

import player.Player;

class PausedState implements PlayerState {

    public void play(Player player) {
        System.out.println("Возобновление...");
        player.setState(new PlayingState());
    }

    public void pause(Player player) {
        System.out.println("Трек уже на паузе");
    }

    public void stop(Player player) {
        System.out.println("Останавливаю с паузы...");
        player.setState(new StoppedState());
    }
}