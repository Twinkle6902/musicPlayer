package filter;

import model.Track;

import java.util.List;

public interface TrackFilter {
    List<Track> apply(List<Track> tracks);
}