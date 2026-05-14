package command;

import player.Player;

public class StopCommand implements Command {

    private Player player;

    public StopCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.stop();
    }
}