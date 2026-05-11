package state;

import player.Player;

class PausedState implements PlayerState {

    public void play(Player player) {
        System.out.println("Resuming...");
        player.setState(new PlayingState());
    }

    public void pause(Player player) {
        System.out.println("Already paused");
    }

    public void stop(Player player) {
        System.out.println("Stopping from pause...");
        player.setState(new StoppedState());
    }
}