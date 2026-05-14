package command;

import player.Player;

public class PreviousCommand implements Command {

    private Player player;

    public PreviousCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.previous();
    }
}