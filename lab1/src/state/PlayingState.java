package state;

import player.Player;

public class PlayingState implements PlayerState {

    public void play(Player player) {
        System.out.println("Already playing");
    }

    public void pause(Player player) {
        System.out.println("Pausing...");
        player.setState(new PausedState());
    }

    public void stop(Player player) {
        System.out.println("Stopping...");
        player.setState(new StoppedState());
    }
}