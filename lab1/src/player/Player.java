package player;


import java.util.List;
import model.Track;
import strategy.PlayStrategy;
import state.PlayerState;
import sort.TrackSortStrategy;

public class Player {
    private PlayerState state;
    private PlayStrategy strategy;
    private List<Track> tracks;
    private int currentIndex;
    private TrackSortStrategy sortStrategy;

    public void play() {
        state.play(this);
    }

    public void next() {
        Track nextTrack = strategy.next(tracks, currentIndex);
        currentIndex = tracks.indexOf(nextTrack);
        System.out.println("Сейчас играет: " + nextTrack.getTitle());
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

    public void setSortStrategy(TrackSortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void pause() {
        state.pause(this);
    }

    public void stop() {
        state.stop(this);
    }

    public void previous() {

        if (tracks.isEmpty()) {
            return;
        }

        currentIndex--;

        if (currentIndex < 0) {
            currentIndex = tracks.size() - 1;
        }

        Track track = tracks.get(currentIndex);

        System.out.println("Сейчас играет: " + track.getTitle());
    }

    public void sortCurrentPlaylist() {
        if (sortStrategy == null) return;

        sortStrategy.sort(tracks);

        System.out.println("Треки отсортированны");
    }
}