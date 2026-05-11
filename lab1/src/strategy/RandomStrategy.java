package strategy;

import model.Track;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements PlayStrategy {
    private Random random = new Random();

    public Track next(List<Track> tracks, int currentIndex) {
        return tracks.get(random.nextInt(tracks.size()));
    }
}