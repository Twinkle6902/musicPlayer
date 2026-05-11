package player;


import java.util.List;
import model.Track;
import strategy.PlayStrategy;
import state.PlayerState;

public class Player {
    private PlayerState state;
    private PlayStrategy strategy;
    private List<Track> tracks;
    private int currentIndex;

    public void play() {
        state.play(this);
    }

    public void next() {
        Track nextTrack = strategy.next(tracks, currentIndex);
        currentIndex = tracks.indexOf(nextTrack);
        System.out.println("Now playing: " + nextTrack.getTitle());
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
        this.currentIndex = 0;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public void setStrategy(PlayStrategy strategy) {
        this.strategy = strategy;
    }

    public void pause() {
        state.pause(this);
    }

    public void stop() {
        state.stop(this);
    }

    public void previous() {
        currentIndex = (currentIndex - 1 + tracks.size()) % tracks.size();
        System.out.println("Now playing: " + tracks.get(currentIndex).getTitle());
    }
}