package composite;

import model.Track;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements MusicComponent {
    private String name;
    private List<MusicComponent> components = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public void add(MusicComponent component) {
        components.add(component);
    }

    @Override
    public void play() {
        System.out.println("Плейлист: " + name + " ");
        for (MusicComponent c : components) {
            c.play();
        }
    }

    public List<Track> getAllTracks() {
        List<Track> allTracks = new ArrayList<>();
        collectTracks(allTracks);
        return allTracks;
    }

    @Override
    public void collectTracks(List<Track> trackList) {
        for (MusicComponent c : components) {
            c.collectTracks(trackList);
        }
    }

    @Override
    public void showStructure(String indent) {
        System.out.println(indent + "Плейлист: " + name);
        for (MusicComponent c : components) {
            c.showStructure(indent + "   ");
        }
    }

    @Override
    public Playlist findPlaylist(String name) {
        if (this.name.equalsIgnoreCase(name)) {
            return this;
        }

        for (MusicComponent c : components) {
            Playlist found = c.findPlaylist(name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}