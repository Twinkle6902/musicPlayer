package command;

import player.Player;

public class PlayCommand implements Command {

    private Player player;

    public PlayCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.play();
    }
}