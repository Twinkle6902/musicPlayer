package composite;

import model.Track;

import java.util.List;
import java.util.ArrayList;

public class Playlist implements MusicComponent {
    private List<MusicComponent> components = new ArrayList<>();

    public void add(MusicComponent component) {
        components.add(component);
    }

    public void play() {
        for (MusicComponent c : components) {
            c.play();
        }
    }

    public List<Track> getTracks() {
        List<Track> result = new ArrayList<>();
        for (MusicComponent c : components) {
            result.addAll(c.getTracks());
        }
        return result;
    }


}