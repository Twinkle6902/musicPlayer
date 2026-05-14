package composite;

import model.Track;

import java.util.List;

public class TrackLeaf implements MusicComponent {

    private Track track;

    public TrackLeaf(Track track) {
        this.track = track;
    }

    @Override
    public void play() {
        System.out.println("Играет: " + track.getTitle());
    }

    @Override
    public List<Track> getTracks() {
        return List.of(track);
    }

    @Override
    public void showStructure(String indent) {

        System.out.println(
                indent +
                        "Трек: " +
                        track.getTitle()
        );
    }

    @Override
    public Playlist findPlaylist(String name) {
        return null;
    }
}