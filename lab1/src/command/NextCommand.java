package command;

import player.Player;

public class NextCommand implements Command {

    private Player player;

    public NextCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.next();
    }
}