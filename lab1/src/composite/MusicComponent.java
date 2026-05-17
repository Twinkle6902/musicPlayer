package composite;

import model.Track;
import java.util.List;

public interface MusicComponent {
    void play();
    void showStructure(String indent);

    default void collectTracks(List<Track> trackList) {}


    default Playlist findPlaylist(String name) {
        return null;
    }
}