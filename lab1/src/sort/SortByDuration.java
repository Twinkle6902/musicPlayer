package sort;

import model.Track;

import java.util.List;
import java.util.Comparator;

public class SortByDuration implements TrackSortStrategy {
    public void sort(List<Track> tracks) {
        tracks.sort(Comparator.comparingInt(Track::getDuration));
    }
}