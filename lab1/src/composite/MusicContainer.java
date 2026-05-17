package composite;


public interface MusicContainer extends MusicComponent {
    Playlist findPlaylist(String name);
}