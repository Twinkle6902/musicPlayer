package state;

import player.Player;

public class StoppedState implements PlayerState {

    public void play(Player player) {
        System.out.println("Starting...");
        player.setState(new PlayingState());
    }

    public void pause(Player player) {
        System.out.println("Cannot pause, stopped");
    }

    public void stop(Player player) {
        System.out.println("Already stopped");
    }
}