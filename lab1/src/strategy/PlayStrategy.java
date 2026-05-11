package strategy;

import model.Track;

import java.util.List;

public interface PlayStrategy {
    Track next(List<Track> tracks, int currentIndex);
}