package strategy;

import model.Track;

import java.util.List;

public class RepeatAllStrategy implements PlayStrategy {
    public Track next(List<Track> tracks, int currentIndex) {
        return tracks.get((currentIndex + 1) % tracks.size());
    }
}