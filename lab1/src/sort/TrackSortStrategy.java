package sort;

import model.Track;

import java.util.List;

public interface TrackSortStrategy {
    void sort(List<Track> tracks);
}