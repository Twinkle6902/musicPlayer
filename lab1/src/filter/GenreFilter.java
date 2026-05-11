package filter;

import model.Track;

import java.util.List;

public class GenreFilter implements TrackFilter {
    private String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    public List<Track> apply(List<Track> tracks) {
        return tracks.stream()
                .filter(t -> t.getGenre().equals(genre))
                .toList();
    }
}