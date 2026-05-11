package sort;

import model.Track;

import java.util.List;
import java.util.Comparator;

public class SortByArtist implements TrackSortStrategy {
    public void sort(List<Track> tracks) {
        tracks.sort(Comparator.comparing(Track::getArtist));
    }
}