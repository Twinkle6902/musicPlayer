package composite;

import model.Track;

import java.util.List;

public interface MusicComponent {
    void play();
    List<Track> getTracks();
    void showStructure(String indent);
    Playlist findPlaylist(String name);
}