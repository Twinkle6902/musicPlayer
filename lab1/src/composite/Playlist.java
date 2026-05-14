package composite;

import model.Track;

import java.util.List;
import java.util.ArrayList;

public class Playlist implements MusicComponent {

    private String name;

    private List<MusicComponent> components =
            new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public void add(MusicComponent component) {
        components.add(component);
    }

    @Override
    public void play() {

        for (MusicComponent c : components) {
            c.play();
        }
    }

    @Override
    public List<Track> getTracks() {

        List<Track> result = new ArrayList<>();

        for (MusicComponent c : components) {
            result.addAll(c.getTracks());
        }

        return result;
    }

    @Override
    public void showStructure(String indent) {

        System.out.println(
                indent +
                        "Плейлист: " +
                        name
        );

        for (MusicComponent component : components) {

            component.showStructure(
                    indent + "   "
            );
        }
    }

    @Override
    public Playlist findPlaylist(String name) {

        if (this.name.equals(name)) {
            return this;
        }

        for (MusicComponent component : components) {

            Playlist found =
                    component.findPlaylist(name);

            if (found != null) {
                return found;
            }
        }

        return null;
    }
}