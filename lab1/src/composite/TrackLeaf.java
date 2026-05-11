package composite;

import model.Track;

import java.util.List;

public class TrackLeaf implements MusicComponent {
    private Track track;

    public TrackLeaf(Track track) {
        this.track = track;
    }

    public void play() {
        System.out.println("Playing: " + track);
    }

    public List<Track> getTracks() {
        return List.of(track);
    }

}