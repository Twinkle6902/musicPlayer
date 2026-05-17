package composite;

import model.Track;

import java.util.List;

public class TrackLeaf implements MusicComponent {
    private final Track track;

    public TrackLeaf(Track track) {
        this.track = track;
    }

    @Override
    public void play() {
        System.out.println("Играет: " + track.getTitle());
    }

    @Override
    public void showStructure(String indent) {
        System.out.println(indent + "Трек: " + track.getTitle());
    }

    @Override
    public void collectTracks(List<Track> trackList) {
        trackList.add(this.track);
    }
}

